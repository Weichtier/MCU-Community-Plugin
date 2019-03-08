package de.slowloris.community.v2.listeners;

import de.slowloris.community.v2.utils.inventory.InventoryUtils;
import de.slowloris.community.v2.utils.location.LocationUtils;
import de.slowloris.community.v2.utils.world.BuildUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class LittleListeners implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        if(!BuildUtils.canBuild(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        if(!BuildUtils.canBuild(event.getPlayer())) event.setCancelled(true);
    }

    @EventHandler
    public void onSign(SignChangeEvent event){
        String line0 = event.getLine(0);
        String line1 = event.getLine(1);
        String line2 = event.getLine(2);
        String line3 = event.getLine(3);
        event.setLine(0, ChatColor.translateAlternateColorCodes('&', line0));
        event.setLine(1, ChatColor.translateAlternateColorCodes('&', line1));
        event.setLine(2, ChatColor.translateAlternateColorCodes('&', line2));
        event.setLine(3, ChatColor.translateAlternateColorCodes('&', line3));
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player){
            if(!BuildUtils.canBuild((Player) event.getEntity()) && !InventoryUtils.isPvP((Player) event.getEntity())){
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event){
        if(!event.getEntityType().equals(EntityType.PLAYER)) event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        event.setKeepInventory(true);
        event.getEntity().spigot().respawn();
        event.getEntity().teleport(LocationUtils.getLocation("Spawn"));
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }
}
