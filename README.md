## CustomItems-API

- Name : CustomItems-API
- Version : Alpha
- Developpers : TwiDev

Create your personalized items with customized actions and crafting recipe quickly and easily with the custom

## Installation

Download the Jar file https://www.spigotmc.org/resources/customitems-api-alpha.90363/

## Usage

Exemple of a custom Items

```Java
        ItemsBuilder myItemStack = new ItemsBuilder(Material.APPLE, 20)
                .setName("§cA very good Apple")
                .setLore("§7The best\napple of the world")
                .addCustomEnchantment(Enchantment.DAMAGE_ALL, 1)
                
                .setInteractActions((player, action) -> {
                    if(action == Action.LEFT_CLICK_AIR) {
                        player.sendMessage("Hello");
                    }
                })
                .setAction(player -> player.sendMessage("Hi"));
         
         Bukkit.getPlayer("TwiDev").getInventory().setItem(1, myItemStack);

```

⚠ If a customized item contains an action and you want to put it in an inventory you must create an InteractInventory for the item's action to work correctly

Exemple of a custom Items interact Inventory

```Java
        InteractInventory interactInventory = new InteractInventory(4*9, "Test");

        interactInventory.setItem(1, myItemStack);

        Bukkit.getPlayer("TwiDev").openInventory(interactInventory.getInventory());

```

You can create different type of action


One click in an inventory

```java
  ItemsBuilder myItemStack = new ItemsBuilder(Material.APPLE, 20).setAction(player -> {
                player.sendMessage("Hello");       
        });
```

a specific click in an inventory

```java
  ItemsBuilder myItemStack = new ItemsBuilder(Material.APPLE, 20).setActions((player, clickType) -> {
                    if(clickType == ClickType.LEFT) {
                        player.sendMessage("Hello");
                    }
        });
```


An interaction action in the player's inventory bar


```java
  ItemsBuilder myItemStack = new ItemsBuilder(Material.APPLE, 20).setInteractActions((player, action) -> {
                    if(action == Action.LEFT_CLICK_AIR) {
                        player.sendMessage("Hello");
                    }
        });
```

Add a custom recipe to the custom item

```java
   ItemsBuilder itemsBuilder = new ItemsBuilder(Material.APPLE).setName("§cVery good apple").setInteractActions((player, action) -> {
            player.sendMessage("test");
        });

        itemsBuilder.setAction(player -> player.sendMessage("Hello"));

        CustomRecipe customRecipe = new CustomRecipe(itemsBuilder)
                .setItem(4, new ItemStack(Material.SPONGE))
                .setItem(5, new ItemsBuilder(Material.DIRT).setName("§8Hello"));

        customRecipe.loadRecipe();

        itemsBuilder.setCustomRecipe(customRecipe);
```

### Learn to use Callback

https://docs.oracle.com/javase/10/docs/api/javafx/util/Callback.html
