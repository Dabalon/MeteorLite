/*
 * Copyright (c) 2019, Hydrox6 <ikada@protonmail.ch>
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
package meteor.plugins.itemidentification;

import meteor.config.Config;
import meteor.config.ConfigGroup;
import meteor.config.ConfigItem;
import meteor.config.ConfigSection;

import java.awt.*;

@ConfigGroup("itemidentification")
public interface ItemIdentificationConfig extends Config
{
	@ConfigSection(
		name = "Categories",
		description = "The categories of items to identify",
		position = 99
	)
	String identificationSection = "identification";

	@ConfigItem(
		keyName = "identificationType",
		name = "Identification Type",
		position = -4,
		description = "How much to show of the item name"
	)
	default ItemIdentificationMode identificationType()
	{
		return ItemIdentificationMode.SHORT;
	}

	@ConfigItem(
		keyName = "textColor",
		name = "Color",
		position = -3,
		description = "The colour of the identification text"
	)
	default Color textColor()
	{
		return Color.WHITE;
	}

	@ConfigItem(
		keyName = "showSeeds",
		name = "Seeds",
		description = "Show identification on Seeds",
		section = identificationSection
	)
	default boolean showSeeds()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showSacks",
		name = "Sacks",
		description = "Show identification on Sacks"
	)
	default boolean showSacks()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showHerbs",
		name = "Herbs",
		description = "Show identification on Herbs",
		section = identificationSection
	)
	default boolean showHerbs()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showLogs",
		name = "Logs",
		description = "Show identification on Logs",
		section = identificationSection
	)
	default boolean showLogs()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showPlanks",
		name = "Planks",
		description = "Show identification on Planks",
		section = identificationSection
	)
	default boolean showPlanks()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showSaplings",
		name = "Saplings",
		description = "Show identification on Saplings and Seedlings",
		section = identificationSection
	)
	default boolean showSaplings()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showComposts",
		name = "Composts",
		description = "Show identification on Composts",
		section = identificationSection
	)
	default boolean showComposts()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showOres",
		name = "Ores",
		description = "Show identification on Ores",
		section = identificationSection
	)
	default boolean showOres()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showBars",
		name = "Bars",
		description = "Show identification on Bars",
		section = identificationSection
	)
	default boolean showBars()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showGems",
		name = "Gems",
		description = "Show identification on Gems",
		section = identificationSection
	)
	default boolean showGems()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showPotions",
		name = "Potions",
		description = "Show identification on Potions",
		section = identificationSection
	)
	default boolean showPotions()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showImplingJars",
		name = "Impling jars",
		description = "Show identification on Impling jars",
		section = identificationSection
	)
	default boolean showImplingJars()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showTablets",
		name = "Tablets",
		description = "Show identification on Tablets",
		section = identificationSection
	)
	default boolean showTablets()
	{
		return false;
	}

	@ConfigItem(
		keyName = "showTeleportScrolls",
		name = "Teleport Scrolls",
		description = "Show identification on teleport scrolls",
		section = identificationSection
	)
	default boolean showTeleportScrolls()
	{
		return false;
	}
}
