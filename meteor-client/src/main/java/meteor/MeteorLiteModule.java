/*
 * Copyright (c) 2016-2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package meteor;

import static meteor.MeteorLite.DEFAULT_CONFIG_FILE;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import java.applet.Applet;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.inject.Singleton;
import meteor.callback.Hooks;
import meteor.chat.ChatMessageManager;
import meteor.config.ChatColorConfig;
import meteor.config.ConfigManager;
import meteor.config.RuneLiteConfig;
import meteor.eventbus.DeferredEventBus;
import meteor.eventbus.EventBus;
import meteor.plugins.botutils.BotUtilsConfig;
import meteor.plugins.itemstats.ItemStatChangesService;
import meteor.plugins.itemstats.ItemStatChangesServiceImpl;
import meteor.plugins.iutils.iUtilsConfig;
import meteor.util.ExecutorServiceExceptionLogger;
import meteor.util.NonScheduledExecutorServiceExceptionLogger;
import net.runelite.api.Client;
import net.runelite.api.hooks.Callbacks;
import net.runelite.http.api.chat.ChatClient;
import okhttp3.OkHttpClient;
import org.sponge.util.Logger;

public class MeteorLiteModule extends AbstractModule {

  public Logger logger = new Logger("MeteorLite");

  @Override
  protected void configure() {
    bind(Callbacks.class).to(Hooks.class);
    bind(ChatMessageManager.class);
    bind(ScheduledExecutorService.class).toInstance(
        new ExecutorServiceExceptionLogger(Executors.newSingleThreadScheduledExecutor()));

    bind(EventBus.class)
        .toInstance(new EventBus());

    bind(EventBus.class)
        .annotatedWith(Names.named("Deferred EventBus"))
        .to(DeferredEventBus.class);

    bind(ItemStatChangesService.class).to(ItemStatChangesServiceImpl.class);
  }

  @com.google.inject.name.Named("config")
  @Provides
  @javax.inject.Singleton
  File provideMeteorLiteConfig() {
    return DEFAULT_CONFIG_FILE;
  }


  @Provides
  @Singleton
  Applet provideApplet() {
    try {
      return (Applet) ClassLoader.getSystemClassLoader().loadClass("Client").newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Provides
  @Singleton
  Logger provideLogger() {
    return logger;
  }

  @Provides
  @Singleton
  Client provideClient(@Nullable Applet applet) {
    return applet instanceof Client ? (Client) applet : null;
  }

  @Provides
  @Singleton
  RuneLiteConfig provideConfig(ConfigManager configManager) {
    return configManager.getConfig(RuneLiteConfig.class);
  }

  @Provides
  @Singleton
  ExecutorService provideExecutorService() {
    int poolSize = 2 * Runtime.getRuntime().availableProcessors();

    // Will start up to poolSize threads (because of allowCoreThreadTimeOut) as necessary, and times out
    // unused threads after 1 minute
    ThreadPoolExecutor executor = new ThreadPoolExecutor(poolSize, poolSize,
        60L, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(),
        new ThreadFactoryBuilder().setNameFormat("worker-%d").build());
    executor.allowCoreThreadTimeOut(true);

    return new NonScheduledExecutorServiceExceptionLogger(executor);
  }

  @Provides
  @Singleton
  ChatColorConfig provideChatColorConfig(ConfigManager configManager)
  {
    return configManager.getConfig(ChatColorConfig.class);
  }

  @Provides
  @Singleton
  ChatClient provideChatClient(OkHttpClient okHttpClient)
  {
    return new ChatClient(okHttpClient);
  }
}
