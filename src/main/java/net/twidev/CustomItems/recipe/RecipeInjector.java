package net.twidev.CustomItems.recipe;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Recipe;

import java.util.List;

/**
 * @author TwiDev
 */
public class RecipeInjector implements Listener {

    @EventHandler
    public void handleCrafting(PrepareItemCraftEvent e){

        if(e.getRecipe() == null || e.getRecipe().getResult() == null) return;

        if(!(e.getInventory() instanceof CraftingInventory)) return;

        CraftingInventory inv = e.getInventory();

        Recipe serverRecipe = e.getRecipe();

        List<RecipeCustom> possibleRecipeGroups = RecipeGroup.findGroupsByResult(serverRecipe.getResult());

        if(possibleRecipeGroups.size() == 0) return;

        for(RecipeCustom recipe : possibleRecipeGroups){

            boolean isRecipe = true;

            for(int i = 1; i < 10; i++) {
                if(recipe.getItems(i) != null) {
                    if (recipe.getItems(i) != inv.getItem(i)) {
                        isRecipe = false;
                    }
                }
            }

            if(isRecipe) {
                inv.setResult(recipe.getResult());

                return;
            }else continue;
        }

        inv.setResult(null);
    }


}
