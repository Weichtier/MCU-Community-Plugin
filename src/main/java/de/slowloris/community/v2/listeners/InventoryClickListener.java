package de.slowloris.community.v2.listeners;

import de.slowloris.community.v2.core.Main;
import de.slowloris.community.v2.utils.events.GriefEvent;
import de.slowloris.community.v2.utils.events.PvPEvent;
import de.slowloris.community.v2.utils.inventory.InventoryUtils;
import de.slowloris.community.v2.utils.location.LocationUtils;
import de.slowloris.community.v2.utils.world.BuildUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event){

        if(event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR)) return;

        Player player = (Player) event.getWhoClicked();
        if(!BuildUtils.canBuild(player) && !InventoryUtils.isPvP(player)) event.setCancelled(true);

        Inventory inventory = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();
        ItemMeta meta = item.getItemMeta();

        if(inventory.getName().equals(Main.getInstance().getConfig().getString("Config.Inventory.Compass.Name").replace("&", "§"))){
            if(meta.getDisplayName().equals(Main.getInstance().getConfig().getString("Config.Inventory.Compass.Item.Spawn").replace("&", "§")) && item.getType().equals(Material.ENDER_PEARL)){
                player.teleport(LocationUtils.getLocation("Spawn"));
                player.closeInventory();
            }else if(meta.getDisplayName().equals(Main.getInstance().getConfig().getString("Config.Inventory.Compass.Item.PvP").replace("&", "§")) && item.getType().equals(Material.IRON_SWORD)){
                InventoryUtils.pvpOn(player);
                player.closeInventory();
            }
        }else if(inventory.getName().equals(Main.getInstance().getConfig().getString("Config.Inventory.Event.Name").replaceAll("&", "§"))){
            if(meta.getDisplayName().equals(Main.getInstance().getConfig().getString("Config.Inventory.Event.Item.Select").replaceAll("%EVENT%", "PvP").replaceAll("&", "§"))){
                Main.getEventManager().setEvent(new PvPEvent());
                Main.getEventManager().startEvent();
                player.closeInventory();
            }else if(meta.getDisplayName().equals(Main.getInstance().getConfig().getString("Config.Inventory.Event.Item.Select").replaceAll("%EVENT%", "Grief").replaceAll("&", "§"))){
                Main.getEventManager().setEvent(new GriefEvent());
                Main.getEventManager().startEvent();
                player.closeInventory();
            }else if(meta.getDisplayName().equals(Main.getInstance().getConfig().getString("Config.Inventory.Event.Item.Stop").replaceAll("&", "§"))){
                if (Main.getEventManager().isRunning()){
                    Main.getEventManager().stopEvent();
                    player.closeInventory();
                }
            }
        }

    }
}
