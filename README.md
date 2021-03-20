## CustomItems-API


------------------------------------

- Name : CustomItems-API
- Version : Alpha
- Developpers : TwiDev
- 

Create your minecraft items easily and assign actions thanks to the creation of customized items

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
