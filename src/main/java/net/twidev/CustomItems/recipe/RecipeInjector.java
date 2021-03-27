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

        System.out.println(possibleRecipeGroups.toString());

        System.out.println(RecipeGroup.getRecipes().toString());

        if(possibleRecipeGroups.size() == 0) return;

        for(CustomRecipe recipe : possibleRecipeGroups){

            boolean isRecipe = true;

            for(int i = 1; i < 9; i++) {
                int index = i;

                if(index == 0)
                    index = 1;

                if(recipe.getItems(index++) != null && recipe.getItems(index++).hasItemMeta() && recipe.getItems(index++).getItemMeta().hasDisplayName()) {

                    System.out.println(recipe.getItems(index++).getItemMeta().getDisplayName());
                    System.out.println(inv.getItem(index).getItemMeta().getDisplayName());

                    if(inv.getItem(i) != null && inv.getItem(i).hasItemMeta() && inv.getItem(i).getItemMeta().hasDisplayName()) {
                        if (!recipe.getItems(index++).getItemMeta().getDisplayName().equals(inv.getItem(i).getItemMeta().getDisplayName())) {
                            isRecipe = false;
                        }
                    }
                }else continue;
            }

            if(isRecipe) {
                inv.setResult(recipe.getResult());

                return;
            }else continue;
        }

        inv.setResult(null);
    }


}
