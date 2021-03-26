package net.twidev.CustomItems;

import net.twidev.CustomItems.listeners.InventoryEvent;
import net.twidev.CustomItems.recipe.RecipeCustom;
import net.twidev.CustomItems.recipe.RecipeInjector;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author TwiDev
 */
public class Items extends JavaPlugin {

    private static Items instance;

    public void log(String message) {
        this.getLogger().info(message);
    }

    @Override
    public void onEnable() {

        instance = this;

        log("#==========[WELCOME TO CUSTOM ITEMS API]===========#");
        log("# CustomItemsAPI by TwiDev is now loading ...      #");
        log("# Thank for using custom items api !               #");
        log("#==================================================#");

        getServer().getPluginManager().registerEvents(new InventoryEvent(), this);
        getServer().getPluginManager().registerEvents(new RecipeInjector(), this);

        super.onEnable();
    }

    @Override
    public void onDisable() {
        this.setEnabled(false);
    }

    public static Items getInstance() {
        return instance;
    }
}
