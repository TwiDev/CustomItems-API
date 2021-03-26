package net.twidev.CustomItems.recipe;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TwiDev
 */
public class RecipeGroup {

    private static final List<RecipeCustom> recipes = new ArrayList<>();

    /**
     * Add custom recipe to list
     *
     * @param itemRecipe recipe
     */
    public static void loadRecipe(RecipeCustom itemRecipe) {
        recipes.add(itemRecipe);
    }

    /**
     * Get all the recipe
     *
     * @return recipes
     */
    public static List<RecipeCustom> getRecipes() {
        return recipes;
    }

    /**
     * Get recipe by result
     *
     * @param itemStack result of the recipe
     * @return list of recipes with the result
     */
    public static List<RecipeCustom> findGroupsByResult(final ItemStack itemStack) {

        List<RecipeCustom> result = new ArrayList<>();

        recipes.forEach(itemRecipe -> {
            if(itemRecipe.getResult() == itemStack) {
                result.add(itemRecipe);
            }
        });

        return result;
    }

}
