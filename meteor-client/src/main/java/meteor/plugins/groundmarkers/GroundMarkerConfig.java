/*
 * Copyright (c) 2018, TheLonelyDev <https://github.com/TheLonelyDev>
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
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
package meteor.plugins.groundmarkers;

import java.awt.Color;
import meteor.config.Alpha;
import meteor.config.Config;
import meteor.config.ConfigGroup;
import meteor.config.ConfigItem;

@ConfigGroup(GroundMarkerConfig.GROUND_MARKER_CONFIG_GROUP)
public interface GroundMarkerConfig extends Config {

  String GROUND_MARKER_CONFIG_GROUP = "groundMarker";
  String SHOW_IMPORT_EXPORT_KEY_NAME = "showImportExport";
  String SHOW_CLEAR_KEY_NAME = "showClear";

  @Alpha
  @ConfigItem(
      keyName = "markerColor",
      name = "Tile color",
      description = "Configures the color of marked tile"
  )
  default Color markerColor() {
    return Color.CYAN;
  }

  @ConfigItem(
      keyName = "rememberTileColors",
      name = "Remember color per tile",
      description = "Color tiles using the color from time of placement"
  )
  default boolean rememberTileColors() {
    return false;
  }

  @ConfigItem(
      keyName = "drawOnMinimap",
      name = "Draw tiles on minimap",
      description = "Configures whether marked tiles should be drawn on minimap"
  )
  default boolean drawTileOnMinimmap() {
    return false;
  }

  @ConfigItem(
      keyName = SHOW_IMPORT_EXPORT_KEY_NAME,
      name = "Show Import/Export options",
      description = "Show the Import/Export options on the minimap right-click menu"
  )
  default boolean showImportExport() {
    return true;
  }

  @ConfigItem(
      keyName = SHOW_CLEAR_KEY_NAME,
      name = "Show Clear option",
      description = "Show the Clear option on the minimap right-click menu, which deletes all currently loaded markers"
  )
  default boolean showClear() {
    return false;
  }

  @ConfigItem(
      keyName = "borderWidth",
      name = "Border Width",
      description = "Width of the marked tile border"
  )
  default double borderWidth() {
    return 2;
  }
}
