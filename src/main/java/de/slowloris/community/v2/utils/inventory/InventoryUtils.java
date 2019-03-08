package de.slowloris.community.v2.utils.inventory;

import de.slowloris.community.v2.core.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;

public class InventoryUtils {

    private static ArrayList<Player> pvpmode = new ArrayList<Player>();
    private static FileConfiguration config;

    public static void setInventory(Player player){
        player.getInventory().clear();
        if(pvpmode.contains(player)){
            player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET).addEnchantment(Enchantment.DURABILITY, 10).build());
            player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE).addEnchantment(Enchantment.DURABILITY, 10).build());
            player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS).addEnchantment(Enchantment.DURABILITY, 10).build());
            player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS).addEnchantment(Enchantment.DURABILITY, 10).build());

            player.getInventory().setItem(0, new ItemBuilder(Material.IRON_SWORD)
                    .setTitle(config.getString("Inventories.PvP.Knife").replaceAll("&", "§"))
                    .addEnchantment(Enchantment.DAMAGE_ALL, 3)
                    .build());
            player.getInventory().setItem(1, new ItemBuilder(Material.GOLDEN_APPLE)
                    .setTitle(config.getString("Inventories.PvP.GoldenApple").replaceAll("&", "§"))
                    .setAmout(5)
                    .build());
            player.getInventory().setItem(8, new ItemBuilder(Material.BED)
                    .setTitle(config.getString("Inventories.PvP.Leave").replaceAll("&", "§"))
                    .build());
        }else {

            player.getInventory().setHelmet(null);
            player.getInventory().setChestplate(null);
            player.getInventory().setLeggings(null);
            player.getInventory().setBoots(null);

            player.getInventory().setItem(4, new ItemBuilder(Material.COMPASS)
                    .setTitle(config.getString("Inventories.Default.Compass").replaceAll("&", "§"))
                    .build());
        }
    }

    public static void pvpOn(Player player){
        if(!pvpmode.contains(player)) pvpmode.add(player);
        setInventory(player);
    }

    public static void pvpOff(Player player){
        if(pvpmode.contains(player)) pvpmode.remove(player);
        player.setHealth(20);
        setInventory(player);
    }

    public static boolean isPvP(Player player){
        return pvpmode.contains(player);
    }

    public static FileConfiguration getConfig() {
        return config;
    }

    public static void init(){
        File configFile = new File(Main.getInstance().getDataFolder(), "inventories.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}
