package net.twidev.CustomItems.inventory;

import net.twidev.CustomItems.ItemsBuilder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * @author TwiDev
 */
public class InteractInventory implements InventoryHolder {

    private final Map<Integer, ItemsBuilder> items = new HashMap<>();

    /**
     * Create a custom InteractInventory
     *
     * @param size number of slots
     * @param name displayName of the inventory
     * @return new InteractInventory
     */
    static InteractInventory createInventory(int size, String name) {
        return new InteractInventory(size, name);
    }

    private final int size;
    private final String title;

    /**
     * Constructor custom InteractInventory
     *
     * @param size number of slots
     * @param title displayName of the Inventory
     */
    public InteractInventory(int size, String title) {
        this.size = size;
        this.title = title;
    }

    /**
     * Set itemstack to the inventory content
     *
     * @param slot number of the slot
     * @param item a itemstack
     */
    public void setItem(int slot, ItemStack item) {
        items.put(slot, new ItemsBuilder(item));
    }

    /**
     * Set custom items builder to the inventory content
     *
     * @param slot number of the slot
     * @param item a custom itemBuilder
     */
    public void setItem(int slot, ItemsBuilder item) {
        items.put(slot, item);
    }

    /**
     * Remove all the items in the inventory
     */
    public void clearItems() {
        items.clear();
    }

    /**
     * get custom item by slot
     *
     * @param slot number of the slot
     * @return a itemsBuilder
     */
    public ItemsBuilder getItem(int slot) {
        return items.get(slot);
    }

    /**
     * @return number of slots
     */
    public int getSize() {
        return this.size;
    }

    /**
     * @return displayName of the inventory
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Set the content of a bukkit Inventory
     *
     * @param inventory Bukkit inventory
     */
    public void setInventory(Inventory inventory) {
        for(int i = 0; i < inventory.getSize(); i++) {
            this.setItem(i, new ItemsBuilder(inventory.getItem(i)));
        }
    }

    /**
     * Get the bukkit inventory with all the items
     *
     * @return Bukkit inventory
     */
    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, this.size, this.title);

        for (Map.Entry<Integer, ItemsBuilder> entry : this.items.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue());
        }

        return inventory;
    }


}
