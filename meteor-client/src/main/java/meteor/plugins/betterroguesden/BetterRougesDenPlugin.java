/*
 * Copyright (c) 2018-2019, Shaun Dreclin <https://github.com/ShaunDreclin>
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
package meteor.plugins.betterroguesden;

import java.util.HashMap;
import javax.inject.Inject;

import com.google.inject.Provides;
import lombok.AccessLevel;
import lombok.Getter;
import meteor.config.ConfigManager;
import meteor.eventbus.Subscribe;
import meteor.plugins.Plugin;
import meteor.plugins.PluginDescriptor;
import meteor.ui.overlay.OverlayManager;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.InventoryID;
import net.runelite.api.Item;
import net.runelite.api.ItemID;
import net.runelite.api.Tile;
import net.runelite.api.TileObject;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.DecorativeObjectChanged;
import net.runelite.api.events.DecorativeObjectDespawned;
import net.runelite.api.events.DecorativeObjectSpawned;
import net.runelite.api.events.GameObjectChanged;
import net.runelite.api.events.GameObjectDespawned;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GroundObjectChanged;
import net.runelite.api.events.GroundObjectDespawned;
import net.runelite.api.events.GroundObjectSpawned;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.WallObjectChanged;
import net.runelite.api.events.WallObjectDespawned;
import net.runelite.api.events.WallObjectSpawned;

@PluginDescriptor(
	name = "Better Rogues' Den",
	description = "Mark tiles and clickboxes to help traverse the maze",
	tags = {"agility", "maze", "minigame", "overlay", "thieving"}
)
public class BetterRougesDenPlugin extends Plugin
{
	@Getter(AccessLevel.PACKAGE)
	private final HashMap<TileObject, Tile> obstaclesHull = new HashMap<>();

	@Getter(AccessLevel.PACKAGE)
	private final HashMap<TileObject, Tile> obstaclesTile = new HashMap<>();

	@Getter(AccessLevel.PACKAGE)
	private boolean hasGem;

	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private RoguesDenOverlay overlay;

	@Inject
	private RoguesDenMinimapOverlay minimapOverlay;

	@Provides
	public BetterRoguesDenConfig getConfig(ConfigManager configManager) {
		return configManager.getConfig(BetterRoguesDenConfig.class);
	}

	@Override
	public void startup()
	{
		overlayManager.add(overlay);
		overlayManager.add(minimapOverlay);
	}

	@Override
	public void shutdown()
	{
		overlayManager.remove(overlay);
		overlayManager.remove(minimapOverlay);
		obstaclesHull.clear();
		obstaclesTile.clear();
		hasGem = false;
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		if (event.getItemContainer() != client.getItemContainer(InventoryID.INVENTORY))
		{
			return;
		}

		for (Item item : event.getItemContainer().getItems())
		{
			if (item.getId() == ItemID.MYSTIC_JEWEL)
			{
				hasGem = true;
				return;
			}
		}

		hasGem = false;
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOADING)
		{
			obstaclesHull.clear();
			obstaclesTile.clear();
		}
	}

	@Subscribe
	public void onGameObjectSpawned(GameObjectSpawned event)
	{
		onTileObject(event.getTile(), null, event.getGameObject());
	}

	@Subscribe
	public void onGameObjectChanged(GameObjectChanged event)
	{
		onTileObject(event.getTile(), event.getOldObject(), event.getNewObject());
	}

	@Subscribe
	public void onGameObjectDespawned(GameObjectDespawned event)
	{
		onTileObject(event.getTile(), event.getGameObject(), null);
	}

	@Subscribe
	public void onGroundObjectSpawned(GroundObjectSpawned event)
	{
		onTileObject(event.getTile(), null, event.getGroundObject());
	}

	@Subscribe
	public void onGroundObjectChanged(GroundObjectChanged event)
	{
		onTileObject(event.getTile(), event.getPrevious(), event.getGroundObject());
	}

	@Subscribe
	public void onGroundObjectDespawned(GroundObjectDespawned event)
	{
		onTileObject(event.getTile(), event.getGroundObject(), null);
	}

	@Subscribe
	public void onWallObjectSpawned(WallObjectSpawned event)
	{
		onTileObject(event.getTile(), null, event.getWallObject());
	}

	@Subscribe
	public void onWallObjectChanged(WallObjectChanged event)
	{
		onTileObject(event.getTile(), event.getPrevious(), event.getWallObject());
	}

	@Subscribe
	public void onWallObjectDespawned(WallObjectDespawned event)
	{
		onTileObject(event.getTile(), event.getWallObject(), null);
	}

	@Subscribe
	public void onDecorativeObjectSpawned(DecorativeObjectSpawned event)
	{
		onTileObject(event.getTile(), null, event.getDecorativeObject());
	}

	@Subscribe
	public void onDecorativeObjectChanged(DecorativeObjectChanged event)
	{
		onTileObject(event.getTile(), event.getPrevious(), event.getDecorativeObject());
	}

	@Subscribe
	public void onDecorativeObjectDespawned(DecorativeObjectDespawned event)
	{
		onTileObject(event.getTile(), event.getDecorativeObject(), null);
	}

	private void onTileObject(final Tile tile, final TileObject oldObject, final TileObject newObject)
	{
		obstaclesHull.remove(oldObject);
		if (newObject != null)
		{
			WorldPoint point = tile.getWorldLocation();

			Obstacles.Obstacle obstacle = Obstacles.TILE_MAP.get(point);
			if (obstacle != null && obstacle.getObjectId() == newObject.getId())
			{
				obstaclesHull.put(newObject, tile);
			}
		}
	}
}