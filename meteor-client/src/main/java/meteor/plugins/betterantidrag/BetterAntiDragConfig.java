/*
 * Copyright (c) 2018, DennisDeV <https://github.com/DevDennis>
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
package meteor.plugins.betterantidrag;

import java.awt.Color;

import meteor.config.*;
import net.runelite.api.Constants;

@ConfigGroup("betterAntiDrag")
public interface BetterAntiDragConfig extends Config
{
	@ConfigItem(
		position = 0,
		keyName = "alwaysOn",
		name = "Always On",
		description = "Makes the anti-drag always active and disables the hotkey toggle",
		disabledBy = "toggleKeyBind || holdKeyBind",
		hide = "toggleKeyBind || holdKeyBind"
	)
	default boolean alwaysOn()
	{
		return false;
	}

	@ConfigItem(
		position = 1,
		keyName = "toggleKeyBind",
		name = "Toggle with Keybind",
		description = "Toggle anti drag on and off, rather than always on.",
		disabledBy = "alwaysOn || holdKeyBind",
		hide = "alwaysOn || holdKeyBind"
	)
	default boolean toggleKeyBind()
	{
		return false;
	}

	@ConfigItem(
		position = 2,
		keyName = "holdKeyBind",
		name = "Hold with Keybind",
		description = "Hold anti drag key to turn it on, rather than toggle it on or off.",
		disabledBy = "alwaysOn || toggleKeyBind",
		hide = "alwaysOn || toggleKeyBind"
	)
	default boolean holdKeyBind()
	{
		return false;
	}

	@ConfigItem(
		keyName = "key",
		name = "Keybind",
		description = "The keybind you want to use for antidrag",
		position = 3,
		hidden = true,
		unhide = "toggleKeyBind || holdKeyBind"
	)
	default Keybind key()
	{
		return Keybind.SHIFT;
	}

	@Range(
			max = 500
	)
	@ConfigItem(
		keyName = "dragDelay",
		name = "Drag Delay",
		description = "Configures the inventory drag delay in client ticks (20ms)",
		position = 4
	)
	default int dragDelay()
	{
		return Constants.GAME_TICK_LENGTH / Constants.CLIENT_TICK_LENGTH; // one game tick
	}

	@Range(
			max = 500
	)
	@ConfigItem(
		keyName = "bankDragDelay",
		name = "Bank Drag Delay",
		description = "Configures the bank drag delay in client ticks (20ms)",
		position = 5
	)
	default int bankDragDelay()
	{
		return Constants.GAME_TICK_LENGTH / Constants.CLIENT_TICK_LENGTH; // one game tick
	}

	@ConfigItem(
		keyName = "reqFocus",
		name = "Reset on focus loss",
		description = "Disable antidrag when losing focus (like alt tabbing)",
		position = 6,
		hidden = true,
		unhide = "toggleKeyBind || holdKeyBind"
	)
	default boolean reqFocus()
	{
		return false;
	}

	@ConfigItem(
		keyName = "overlay",
		name = "Enable overlay",
		description = "Do you really need a description?",
		position = 7,
		hidden = true,
		unhide = "toggleKeyBind || holdKeyBind"
	)
	default boolean overlay()
	{
		return false;
	}

	@Alpha
	@ConfigItem(
		keyName = "color",
		name = "Overlay color",
		description = "Change the overlay color, duh",
		hidden = true,
		unhide = "toggleKeyBind || holdKeyBind",
		position = 8
	)
	default Color color()
	{
		return new Color(255, 0, 0, 30);
	}
}
