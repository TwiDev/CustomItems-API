package net.twidev.CustomItems.actions;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

/**
 * @author TwiDev
 */
public interface InteractActions {

    void onInteract(Player player, Action action);

}
