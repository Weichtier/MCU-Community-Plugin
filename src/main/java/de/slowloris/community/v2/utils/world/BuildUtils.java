package de.slowloris.community.v2.utils.world;

import de.slowloris.community.v2.utils.inventory.InventoryUtils;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BuildUtils {

    private static ArrayList<Player> inBuildMode = new ArrayList<Player>();

    public static boolean canBuild(Player player){
        return inBuildMode.contains(player);
    }

    public static void buildOn(Player player){
        if(!inBuildMode.contains(player)){
            inBuildMode.add(player);
            player.getInventory().clear();
        }
    }

    public static void buildOff(Player player){
        if (inBuildMode.contains(player)) {
            inBuildMode.remove(player);
            InventoryUtils.setInventory(player);
        }
    }
}
