package de.slowloris.community.v2.utils.events;

import de.slowloris.community.v2.utils.inventory.InventoryUtils;
import de.slowloris.community.v2.utils.inventory.ItemBuilder;
import de.slowloris.community.v2.utils.world.BuildUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class GriefEvent implements CommunityEvent {
    public void start() {
        doMechanics();
    }

    public void stop() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            BuildUtils.buildOff(all);
            InventoryUtils.setInventory(all);
        }
    }

    public void playerJoined(Player player) {
        doMechanics();
    }

    public void playerLeaved(Player player) {

    }

    private void doMechanics() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            BuildUtils.buildOn(all);
            all.getInventory().clear();
            all.getInventory().setItem(0, new ItemBuilder(Material.DIAMOND_SPADE)
                    .setTitle(InventoryUtils.getConfig().getString("Inventories.Grief.Shovel"))
                    .addEnchantment(Enchantment.DIG_SPEED, 10)
                    .build()
            );
            all.getInventory().setItem(4, new ItemBuilder(Material.DIAMOND_PICKAXE)
                    .setTitle(InventoryUtils.getConfig().getString("Inventories.Grief.Pickaxe"))
                    .addEnchantment(Enchantment.DIG_SPEED, 10)
                    .build()
            );
            all.getInventory().setItem(8, new ItemBuilder(Material.DIAMOND_AXE)
                    .setTitle(InventoryUtils.getConfig().getString("Inventories.Grief.Axe"))
                    .addEnchantment(Enchantment.DIG_SPEED, 10)
                    .build()
            );
        }
    }
}
