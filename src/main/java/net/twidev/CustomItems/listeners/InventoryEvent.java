package net.twidev.CustomItems.listeners;

import net.twidev.CustomItems.ItemsBuilder;
import net.twidev.CustomItems.inventory.InteractInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getView().getTopInventory().getHolder() instanceof InteractInventory) {
            event.setCancelled(true);

            if (event.getWhoClicked() instanceof Player) {
                Player player = (Player) event.getWhoClicked();

                ItemStack itemStack = event.getCurrentItem();
                if (itemStack == null || itemStack.getType() == Material.AIR) return;

                InteractInventory customHolder = (InteractInventory) event.getView().getTopInventory().getHolder();

                //Check if the clicked slot is any icon
                ItemsBuilder itemsBuilder = customHolder.getItem(event.getRawSlot());
                if (itemsBuilder == null) return;

                //Execute all the actions
                if(itemsBuilder.getSimpleAction() != null) {
                    itemsBuilder.getSimpleAction().onClick(player);
                }else if(itemsBuilder.getAction() != null) {
                    itemsBuilder.getAction().onClick(player, event.getClick());
                }
            }
        }
    }


}
