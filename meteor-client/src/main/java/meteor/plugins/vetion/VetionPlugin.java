/*
 * Copyright (c) 2019, Frosty Fridge <https://github.com/frostyfridge>
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
package meteor.plugins.vetion;

import com.google.inject.Provides;
import lombok.AccessLevel;
import lombok.Getter;
import meteor.config.ConfigManager;
import meteor.plugins.agility.AgilityConfig;
import net.runelite.api.Actor;
import net.runelite.api.events.AnimationChanged;
import meteor.eventbus.Subscribe;
import meteor.plugins.Plugin;
import meteor.plugins.PluginDescriptor;
import meteor.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@PluginDescriptor(
	name = "Vetion Helper",
	enabledByDefault = false,
	description = "Tracks Vet'ion's special attacks",
	tags = {"bosses", "combat", "pve", "overlay"}
)
public class VetionPlugin extends Plugin
{

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private VetionOverlay overlay;

	@Getter(AccessLevel.PACKAGE)
	private Map<Actor, Instant> vetions;

	public static final int VETION_EARTHQUAKE = 5507;

	@Provides
	public VetionConfig getConfig(ConfigManager configManager) {
		return configManager.getConfig(VetionConfig.class);
	}

	@Override
	public void startup()
	{

		vetions = new HashMap<>();
		overlayManager.add(overlay);
	}

	@Override
	public void shutdown()
	{
		overlayManager.remove(overlay);
		vetions = null;
	}

	@Subscribe
	private void onAnimationChanged(AnimationChanged event)
	{
		if (event.getActor().getAnimation() == VETION_EARTHQUAKE)
		{
			Actor vet = event.getActor();
			vetions.remove(vet, Instant.now());
			vetions.put(vet, Instant.now());
		}
	}
}