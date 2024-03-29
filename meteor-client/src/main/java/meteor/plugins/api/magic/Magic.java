package meteor.plugins.api.magic;

import meteor.plugins.api.commons.Time;
import meteor.plugins.api.game.Vars;
import meteor.plugins.api.widgets.Widgets;
import net.runelite.api.*;
import net.runelite.api.widgets.Widget;

import java.util.Arrays;

public class Magic {
    public enum SpellBook {
        REGULAR(0),
        ANCIENT(1),
        LUNAR(2),
        NECROMANCY(3);

        private final int varbitValue;

        SpellBook(int varbitValue) {
            this.varbitValue = varbitValue;
        }

        public static SpellBook getCurrent() {
            return Arrays.stream(values()).filter(x -> Vars.getBit(4070) == x.varbitValue)
                    .findFirst().orElse(null);
        }
    }

    public static boolean isAutoCasting() {
        return Vars.getVarp(108) != 0;
    }

    public static boolean isSpellSelected(Spell spell) {
        Widget widget = Widgets.get(spell.getWidget());
        if (widget != null) {
            return widget.getBorderType() == 2;
        }

        return false;
    }

    public static void cast(Spell spell, Item target) {
        if (!isSpellSelected(spell)) {
            cast(spell);
            Time.sleepUntil(() -> isSpellSelected(spell), 2000);
        }

        target.interact(0, MenuAction.ITEM_USE_ON_WIDGET.getId());
    }

    public static void cast(Spell spell, NPC target) {
        if (!isSpellSelected(spell)) {
            cast(spell);
            Time.sleepUntil(() -> isSpellSelected(spell), 2000);
        }

        target.interact(0, MenuAction.SPELL_CAST_ON_NPC.getId());
    }

    public static void cast(Spell spell, Player target) {
        if (!isSpellSelected(spell)) {
            cast(spell);
            Time.sleepUntil(() -> isSpellSelected(spell), 2000);
        }

        target.interact(0, MenuAction.SPELL_CAST_ON_PLAYER.getId());
    }

    public static void cast(Spell spell, TileItem target) {
        if (!isSpellSelected(spell)) {
            cast(spell);
            Time.sleepUntil(() -> isSpellSelected(spell), 2000);
        }

        target.interact(0, MenuAction.SPELL_CAST_ON_GROUND_ITEM.getId());
    }

    public static void cast(Spell spell, TileObject target) {
        if (!isSpellSelected(spell)) {
            cast(spell);
            Time.sleepUntil(() -> isSpellSelected(spell), 2000);
        }

        target.interact(0, MenuAction.SPELL_CAST_ON_GAME_OBJECT.getId());
    }

    public static void cast(Spell spell) {
        Widget widget = Widgets.get(spell.getWidget());
        if (widget != null) {
            widget.interact(0);
        }
    }
}
