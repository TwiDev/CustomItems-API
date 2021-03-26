package net.twidev.CustomItems.recipe;

import net.twidev.CustomItems.ItemsBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

/**
 * @author TwiDev
 */
public class RecipeCustom {

    private final ItemsBuilder result;

    private List<ItemStack> items;

    /**
     * Create a new recipe
     *
     * @param result of the recipe
     */
    public RecipeCustom(ItemsBuilder result) {
        this.result = result;
    }

    /**
     * Create a new recipe
     *
     * @param result of the recipe
     * @param itemStacks ingredients
     */
    public RecipeCustom(ItemsBuilder result, ItemStack... itemStacks) {
        this.result = result;
        this.items = Arrays.asList(itemStacks.clone());
    }

    /**
     * set ingredients with slots for the recipe
     *
     * @param index slot
     * @param itemStack ingredient
     * @return recipe custom
     */
    public RecipeCustom setItem(int index, ItemStack itemStack) {
        items.set(index, itemStack);

        return this;
    }

    /**
     * set ingredients with slots for the recipe
     *
     * @param index slot
     * @param material ingredient
     * @return recipe custom
     */
    public RecipeCustom setItem(int index, Material material){
        setItem(index, new ItemsBuilder(material));

        return this;
    }

    /**
     * Get ingredient by slot
     *
     * @param index slot
     * @return ingredient
     */
    public ItemStack getItems(int index) {
        return items.get(index);
    }

    /**
     * get the result of the recipe
     *
     * @return result
     */
    public ItemsBuilder getResult() {
        return result;
    }

}
