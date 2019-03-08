package de.slowloris.community.v2.utils.inventory;

import de.slowloris.community.v2.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;

public class Inventories {
    public static Inventory compassInventory(){
        Inventory inventory = Bukkit.createInventory(null, 6*9, Main.getInstance().getConfig().getString("Config.Inventory.Compass.Name").replaceAll("&", "§"));
        inventory.setItem(calculateInventoryCoordinate(5, 3), new ItemBuilder(Material.ENDER_PEARL).setTitle(Main.getInstance().getConfig().getString("Config.Inventory.Compass.Item.Spawn").replaceAll("&", "§")).build());
        inventory.setItem(calculateInventoryCoordinate(5, 5), new ItemBuilder(Material.IRON_SWORD).setTitle(Main.getInstance().getConfig().getString("Config.Inventory.Compass.Item.PvP").replaceAll("&", "§")).build());
        return inventory;
    }

    public static Inventory eventInventory(){
        Inventory inventory = Bukkit.createInventory(null, 6*9, Main.getInstance().getConfig().getString("Config.Inventory.Event.Name").replaceAll("&", "§"));
        inventory.setItem(calculateInventoryCoordinate(1, 1), new ItemBuilder(Material.IRON_SWORD).setTitle(Main.getInstance().getConfig().getString("Config.Inventory.Event.Item.Select").replaceAll("%EVENT%", "PvP").replaceAll("&", "§")).build());
        inventory.setItem(calculateInventoryCoordinate(2, 1), new ItemBuilder(Material.IRON_PICKAXE).setTitle(Main.getInstance().getConfig().getString("Config.Inventory.Event.Item.Select").replaceAll("%EVENT%", "Grief").replaceAll("&", "§")).build());
        if(Main.getEventManager().isRunning()){
            inventory.setItem(calculateInventoryCoordinate(3, 6), new ItemBuilder(Material.getMaterial(351), (byte) 1)
                    .setTitle(Main.getInstance().getConfig().getString("Config.Inventory.Event.Item.Stop").replaceAll("&", "§"))
                    .addEnchantment(Enchantment.DURABILITY, 10)
                    .build());

            inventory.setItem(calculateInventoryCoordinate(5, 6), new ItemBuilder(Material.EYE_OF_ENDER, (byte) 1)
                    .setTitle(Main.getInstance().getConfig().getString("Config.Inventory.Event.Item.Actual")
                            .replaceAll("%EVENT%", Main.getEventManager().getEvent().getClass().getSimpleName())
                            .replaceAll("&", "§"))
                    .addEnchantment(Enchantment.DURABILITY, 10)
                    .build());

        }else {
            inventory.setItem(calculateInventoryCoordinate(3, 6), new ItemBuilder(Material.getMaterial(351), (byte) 1).setTitle(Main.getInstance().getConfig().getString("Config.Inventory.Event.Item.Stop").replaceAll("&", "§")).build());
            inventory.setItem(calculateInventoryCoordinate(5, 6), new ItemBuilder(Material.EYE_OF_ENDER, (byte) 1)
                    .setTitle(Main.getInstance().getConfig().getString("Config.Inventory.Event.Item.Actual").replaceAll("%EVENT%", "NONE").replaceAll("&", "§"))
                    .addEnchantment(Enchantment.DURABILITY, 10)
                    .build());
        }
        return inventory;
    }

    public static Inventory musicInventory(){
        Inventory inventory = Bukkit.createInventory(null, 6*9, Main.getInstance().getConfig().getString("Config.Inventory.Event.Name").replaceAll("&", "§"));


        return inventory;
    }

    private static Integer calculateInventoryCoordinate(Integer x, Integer y){
        x--;
        y = (y - 1)*9;
        int out = x + y;
        if(out < 0){
            out = 0;
        }
        return out;
    }
}
