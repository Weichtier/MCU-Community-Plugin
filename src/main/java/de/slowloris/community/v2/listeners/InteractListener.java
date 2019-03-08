package de.slowloris.community.v2.listeners;

import de.slowloris.community.v2.utils.inventory.Inventories;
import de.slowloris.community.v2.utils.inventory.InventoryUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractListener implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event){

        if(event.getItem() == null) return;

        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        ItemMeta meta = item.getItemMeta();

        if(item.getType().equals(Material.COMPASS) && meta.getDisplayName().equals(InventoryUtils.getConfig().getString("Inventories.Default.Compass").replaceAll("&", "§"))){
            player.openInventory(Inventories.compassInventory());
        }else if(item.getType().equals(Material.BED) && meta.getDisplayName().equals(InventoryUtils.getConfig().getString("Inventories.PvP.Leave").replaceAll("&", "§"))){
            InventoryUtils.pvpOff(player);
        }

    }
}
