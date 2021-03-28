package net.twidev.CustomItems.recipe;

import net.twidev.CustomItems.Items;
import net.twidev.CustomItems.ItemsBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author TwiDev
 */
public class CustomRecipe {

    private final ItemsBuilder result;

    public List<ItemStack> items = new ArrayList<>();

    /**
     * Create a new recipe
     *
     * @param result of the recipe
     */
    public CustomRecipe(ItemsBuilder result) {
        this.result = result;

        initItems();

    }

    public void loadRecipe() {
        ItemStack[] content = items.toArray(new ItemStack[0]);

        ShapedRecipe shapedRecipe = new ShapedRecipe(this.getResult());

        shapedRecipe.shape(
                "ABC",
                "DEF",
                "GHI"
        );

        String shape = "A B C D E F G H I";

        String[] shapes = shape.split(" ");

        for(int i = 0; i < shapes.length; i++) {
            int index = i;

            if(index == 0)
                index = 1;


            if(getItems(index).getType() != Material.AIR) {
                shapedRecipe.setIngredient(shapes[i].charAt(0), getItems(index).getType());
            }else{

            }

        }

        Items.getInstance().getServer().addRecipe(shapedRecipe);

    }


    /**
     * Create a new recipe
     *
     * @param result of the recipe
     * @param itemStacks ingredients
     */
    public CustomRecipe(ItemsBuilder result, ItemStack... itemStacks) {
        this.result = result;

        initItems();

        this.items = Arrays.asList(itemStacks.clone());
    }

    public void initItems() {
        for(int index = 0; index < 10; index++) {
            items.add(index, new ItemStack(Material.AIR));
        }

    }

    /**
     * set ingredients with slots for the recipe
     *
     * @param index slot
     * @param itemStack ingredient
     * @return recipe custom
     */
    public CustomRecipe setItem(int index, ItemStack itemStack) {
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
    public CustomRecipe setItem(int index, Material material){
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
