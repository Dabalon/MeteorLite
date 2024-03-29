package meteor.plugins.voidtempoross.tasks.high;

import meteor.eventbus.Subscribe;
import meteor.plugins.voidtempoross.VoidTemporossPlugin;
import meteor.plugins.voidutils.OSRSUtils;
import meteor.plugins.voidutils.tasks.PriorityTask;
import meteor.plugins.voidutils.tasks.Task;
import net.runelite.api.ChatMessageType;
import net.runelite.api.GameObject;
import net.runelite.api.events.ChatMessage;

import javax.inject.Inject;

public class Tether extends PriorityTask {

    @Inject
    OSRSUtils osrs;

    @Inject
    VoidTemporossPlugin plugin;

    public boolean clickedTether;

    public boolean movedToTether = false;

    public Tether(VoidTemporossPlugin plugin) {
        super();
        this.plugin = plugin;
    }

    @Override
    public String name() {
        return "Tether";
    }

    @Override
    public boolean shouldExecute() {
        return plugin.shouldTether;
    }

    @Override
    public void execute() {
        if (!movedToTether)
            if (getNearestTether() != null) {
                getNearestTether().interact(0);
                clickedTether = true;
            }

    }

    public GameObject getNearestTether() {
        GameObject islandTether = getTotem();
        GameObject shipTether = getMast();
        if (islandTether != null && shipTether != null) {
            if (islandTether.getDistanceFromLocalPlayer() > shipTether.getDistanceFromLocalPlayer())
                return shipTether;
            else
                return islandTether;
        }
        if (islandTether == null)
            return shipTether;
        return islandTether;
    }

    public GameObject getTotem() {
        if (plugin.side == null)
            return null;
        if (plugin.side.equals("EAST"))
            return osrs.nearestObject(41355);
        else
            return osrs.nearestObject(41354);
    }

    public GameObject getMast() {
        if (plugin.side == null)
            return null;
        if (plugin.side.equals("EAST"))
            return osrs.nearestObject(41353);
        else
            return osrs.nearestObject(41352);
    }

    @Subscribe
    public void onChatMessage(ChatMessage event) {
        if (event.getType() != ChatMessageType.GAMEMESSAGE)
            return;

        if (event.getMessage().contains("colossal wave closes in")) {
            plugin.shouldTether = true;
            movedToTether = false;
            clickedTether = false;
            plugin.canInterrupt = true;
        } else if (event.getMessage().contains("slams into you")) {
            plugin.shouldTether = false;
        } else if (event.getMessage().contains("the rope keeps you")) {
            execute();
            plugin.shouldTether = false;
        }
    }
}
