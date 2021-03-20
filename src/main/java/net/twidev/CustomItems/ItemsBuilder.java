package net.twidev.CustomItems;

import net.twidev.CustomItems.actions.Actions;
import net.twidev.CustomItems.actions.InteractActions;
import net.twidev.CustomItems.actions.SimpleAction;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author TwiDev
 */
public class ItemsBuilder extends ItemStack implements Listener {

    private Actions action = null;

    private SimpleAction simpleAction = null;

    private InteractActions interactActions = null;

    /**
     * Create your custom items with
     *
     * @param item default itemstack
     * @param displayName
     * @param lore description
     */
    public ItemsBuilder(ItemStack item, String displayName, String lore) {
        super(item);

        this.registerListener();
    }

    /**
     *
     * Create your custom items with
     *
     * @param item default itemstack
     * @param displayName custom display Name
     */
    public ItemsBuilder(ItemStack item, String displayName) {
        super(item);

        this.registerListener();
    }

    /**
     *
     * Create your custom items with
     *
     * @param item default itemstack
     */
    public ItemsBuilder(ItemStack item) {
        super(item);

        this.registerListener();
    }

    /**
     *
     * Create your custom items with
     *
     * @param material of the item
     * @param amount number of item
     * @param data the id of the material
     */
    public ItemsBuilder(Material material, int amount, byte data) {
        super(material, amount, data);

        this.registerListener();
    }

    /**
     *
     * Create your custom items with
     *
     * @param material of the item
     * @param amount number of item
     */
    public ItemsBuilder(Material material, int amount) {
        super(material, amount);

        this.registerListener();
    }

    /**
     * Create your custom items with
     *
     * @param material of the item
     */
    public ItemsBuilder(Material material) {
        super(material);

        this.registerListener();
    }

    private void registerListener() {
        Items.getInstance().getServer().getPluginManager().registerEvents(this, Items.getInstance());
    }

    /**
     *
     * Set a description to the item
     *
     * @param lore new line with \n
     * @return ItemsBuilder
     */
    public ItemsBuilder setLore(String lore) {
        if (lore == null || lore.equals("")) {
            return this;
        }

        ItemMeta meta = this.getItemMeta();

        // Remove attributes
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        // Set Lore on ItemMeta
        lore = ChatColor.translateAlternateColorCodes('&', lore);
        String[] loreString = lore.split("\n");
        meta.setLore(new ArrayList<>(Arrays.asList(loreString)));
        this.setItemMeta(meta);

        return this;
    }

    /**
     * Set the displayName of the item
     *
     * @param displayName of the item
     * @return ItemsBuilder
     */
    public ItemsBuilder setName(String displayName) {
        if (displayName == null || displayName.equals("")) {
            return this;
        }

        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        this.setItemMeta(meta);

        return this;
    }

    /**
     * Set a leather color
     *
     * @param color of the leather
     * @return itemsBuilder
     */
    public ItemsBuilder setLeatherColor(Color color) {
        LeatherArmorMeta lch = (LeatherArmorMeta) this.getItemMeta();
        lch.setColor(color);
        this.setItemMeta(lch);
        return this;
    }

    /**
     * Update the amount of the item
     *
     * @param amount number of items
     * @return itemsBuilder
     */
    public ItemsBuilder setCustomAmount(int amount) {
        this.setAmount(amount);
        return this;
    }

    /**
     * Set a material data to the item
     *
     * @param data material id
     * @return ItemsBuilder
     */
    public ItemsBuilder setData(byte data) {
        MaterialData materialData = this.getData();
        materialData.setData(data);
        this.setData(materialData);
        return this;
    }

    /**
     * Set glow to the item
     *
     * @param enabled yes/no
     * @return ItemsBuilder
     */
    public ItemsBuilder setGlow(boolean enabled) {
        if (enabled) {
            this.setGlow();
        }
        return this;
    }

    /**
     * Set glow to the item
     *
     * @return ItemsBuilder
     */
    public ItemsBuilder setGlow() {
        this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 0);
        ItemMeta meta = this.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        this.setItemMeta(meta);
        return this;
    }

    /**
     * Get a pane decoration item
     *
     * @param data id
     * @return ItemsBuilder
     */
    public static ItemsBuilder getPane(int data) {
        return new ItemsBuilder(Material.STAINED_GLASS_PANE).setData((byte) data);
    }

    /**
     * Add a custom actions to the item
     *
     * @param action many actions
     * @return ItemsBuilder
     */
    public ItemsBuilder setAction(Actions action) {
        this.action = action;
        return this;
    }

    /**
     * Add a simple action to the item
     *
     * @param simpleAction a simple action
     * @return
     */
    public ItemsBuilder setAction(SimpleAction simpleAction) {
        this.simpleAction = simpleAction;
        return this;
    }

    /**
     * Add custom enchantment to the items with flags
     *
     * @param enchantment type of enchantment
     * @param level level of the enchantment
     * @return ItemsBuilder
     */
    public ItemsBuilder addCustomEnchantment(Enchantment enchantment, int level) {
        super.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    /**
     * Add new line to lore of the item
     *
     * @param lines new line
     */
    private void addLineToLore(String... lines) {
        ItemMeta meta = this.getItemMeta();
        List<String> lore = meta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }
        int i = -1;
        for (String line : lines) {
            ++i;
            if (i == 0 && line == null && !hasAlreadyLore()) {
                continue;
            }
            lore.add(line == null ? "" : line);
        }
        meta.setLore(lore);
        this.setItemMeta(meta);
    }

    /**
     * Check if has already lore
     *
     * @return yes/no
     */
    private boolean hasAlreadyLore() {
        ItemMeta meta = this.getItemMeta();
        if (meta == null) {
            return false;
        }
        List<String> lore = meta.getLore();
        return lore != null && lore.size() > 0;
    }

    /**
     * Get the actions of the item
     *
     * @return action callback
     */
    public Actions getAction() {
        return action;
    }

    /**
     * Get the simple action of the item
     *
     * @return simple action callback
     */
    public SimpleAction getSimpleAction() {
        return simpleAction;
    }

    /**
     * Set a simple action
     *
     * @param simpleAction simple action callback
     */
    public void setSimpleAction(SimpleAction simpleAction) {
        this.simpleAction = simpleAction;
    }

    /**
     * Get the interact event action
     *
     * @return interact action callback
     */
    public InteractActions getInteractActions() {
        return interactActions;
    }

    /**
     * Set the interact action event
     *
     * @param interactActions interact action callback
     */
    public void setInteractActions(InteractActions interactActions) {
        this.interactActions = interactActions;
    }

    @EventHandler
    private void onInteract(PlayerInteractEvent e) {
        if(e.getItem() == null) return;

        if(e.getItem().hasItemMeta()
                && e.getItem().getItemMeta().hasDisplayName()
                && e.getItem().getItemMeta().getDisplayName().equals(this.getItemMeta().getDisplayName())) {

            interactActions.onInteract(e.getPlayer(), e.getAction());
        }
    }
}
