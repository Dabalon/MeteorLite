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
package net.runelite.api;

import net.runelite.api.coords.WorldPoint;

/**
 * Represents the entire 3D scene
 */
public interface Scene {

  /**
   * Gets the tiles in the scene
   *
   * @return the tiles in [plane][x][y]
   */
  Tile[][][] getTiles();

  /**
   * Adds an item to the scene
   */
  void addItem(int id, int quantity, WorldPoint point);

  /**
   * Removes an item from the scene
   */
  void removeItem(int id, int quantity, WorldPoint point);

  int getDrawDistance();

  void setDrawDistance(int drawDistance);

  /**
   * Get the minimum scene level which will be rendered
   *
   * @return the plane of the minimum level
   */
  int getMinLevel();

  /**
   * Set the minimum scene level which will be rendered
   *
   * @param minLevel the plane of the minimum level
   */
  void setMinLevel(int minLevel);

  /**
   * Remove a game object from the scene
   *
   * @param gameObject
   */
  void removeGameObject(GameObject gameObject);
}
