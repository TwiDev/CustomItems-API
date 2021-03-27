package net.twidev.CustomItems.recipe;

import net.twidev.CustomItems.ItemsBuilder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TwiDev
 */
public class RecipeGroup {

    private static final List<CustomRecipe> recipes = new ArrayList<>();

    /**
     * Add custom recipe to list
     *
     * @param itemRecipe recipe
     */
    public static void loadRecipe(CustomRecipe itemRecipe) {
        recipes.add(itemRecipe);
    }

    /**
     * Get all the recipe
     *
     * @return recipes
     */
    public static List<CustomRecipe> getRecipes() {
        return recipes;
    }

    /**
     * Get recipe by result
     *
     * @param itemStack result of the recipe
     * @return list of recipes with the result
     */
    public static List<CustomRecipe> findGroupsByResult(final ItemStack itemStack) {

        List<CustomRecipe> result = new ArrayList<>();

        recipes.forEach(itemRecipe -> {
            if(itemRecipe.getResult().getType() == itemStack.getType()) {
                result.add(itemRecipe);
            }
        });

        return result;
    }

}
