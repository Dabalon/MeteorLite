/*
 * Copyright (c) 2018, Tomas Slusny <slusnucky@gmail.com>
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
package meteor.plugins.defaultworld;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import meteor.config.ConfigManager;
import meteor.eventbus.Subscribe;
import meteor.game.WorldService;
import meteor.plugins.Plugin;
import meteor.plugins.PluginDescriptor;
import meteor.util.WorldUtil;
import net.runelite.http.api.worlds.World;
import net.runelite.http.api.worlds.WorldResult;

import javax.inject.Inject;

@PluginDescriptor(
	name = "Default World",
	description = "Enable a default world to be selected when launching the client",
	tags = {"home"}
)
public class DefaultWorldPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private DefaultWorldConfig config;

	@Inject
	private WorldService worldService;

	private int worldCache;
	private boolean worldChangeRequired;

	@Override
	public void startup()
	{
		worldChangeRequired = true;
		applyWorld();
	}

	@Override
	public void shutdown()
	{
		worldChangeRequired = true;
		changeWorld(worldCache);
	}

	@Provides
	public DefaultWorldConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(DefaultWorldConfig.class);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOGGED_IN)
		{
			config.lastWorld(client.getWorld());
		}

		applyWorld();
	}

	private void changeWorld(int newWorld)
	{
		if (!worldChangeRequired || client.getGameState() != GameState.LOGIN_SCREEN)
		{
			return;
		}

		worldChangeRequired = false;
		int correctedWorld = newWorld < 300 ? newWorld + 300 : newWorld;

		// Old School RuneScape worlds start on 301 so don't even bother trying to find lower id ones
		// and also do not try to set world if we are already on it
		if (correctedWorld <= 300 || client.getWorld() == correctedWorld)
		{
			return;
		}

		final WorldResult worldResult = worldService.getWorlds();

		if (worldResult == null)
		{
			return;
		}

		final World world = worldResult.findWorld(correctedWorld);

		if (world != null)
		{
			final net.runelite.api.World rsWorld = client.createWorld();
			rsWorld.setActivity(world.getActivity());
			rsWorld.setAddress(world.getAddress());
			rsWorld.setId(world.getId());
			rsWorld.setPlayerCount(world.getPlayers());
			rsWorld.setLocation(world.getLocation());
			rsWorld.setTypes(WorldUtil.toWorldTypes(world.getTypes()));

			client.changeWorld(rsWorld);
		}
	}

	private void applyWorld()
	{
		if (worldCache == 0)
		{
			worldCache = client.getWorld();
		}

		if (System.getProperty("cli.world") != null)
		{
			return;
		}

		final int newWorld = !config.useLastWorld() ? config.getWorld() : config.lastWorld();
		changeWorld(newWorld);
	}
}
