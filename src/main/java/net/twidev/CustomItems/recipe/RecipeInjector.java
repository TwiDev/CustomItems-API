package net.twidev.CustomItems.recipe;

import org.bukkit.Material;
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

        List<CustomRecipe> possibleRecipeGroups = RecipeGroup.findGroupsByResult(serverRecipe.getResult());

        if(possibleRecipeGroups.size() == 0) return;

       CustomRecipe customRecipe = null;

       for(CustomRecipe recipe : possibleRecipeGroups){

           boolean isRecipe = true;

            for(int i = 1; i < inv.getSize(); i++) {

                int index = i;

                index -= 1;

                if(recipe.getItems(index) != null && inv.getItem(i) != null) {
                    if(recipe.getItems(index).hasItemMeta()) {
                        if(inv.getItem(i).hasItemMeta()) {
                            if(recipe.getItems(index).getItemMeta().hasDisplayName()) {
                                if(inv.getItem(i).getItemMeta().hasDisplayName()) {
                                    if(!inv.getItem(i).getItemMeta().getDisplayName().equals(recipe.getItems(index).getItemMeta().getDisplayName())) {
                                        isRecipe = false;
                                    }
                                }else{
                                    isRecipe = false;
                                }
                            }
                        }else{
                            isRecipe = false;
                        }
                    }

                }

            }

            if(isRecipe) {
                customRecipe = recipe;
            }

            continue;
       }

       if(customRecipe != null) {
           inv.setResult(customRecipe.getResult());

           return;
       }else{
           inv.setResult(null);
       }

    }


}
