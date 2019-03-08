package de.slowloris.community.v2.utils.world;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class VanishUtils {

    private static ArrayList<Player> vanished = new ArrayList<Player>();

    public static boolean isVanish(Player player){
        return vanished.contains(player);
    }

    public static void vanishOn(Player player){
        if(!vanished.contains(player)){
            vanished.add(player);
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.hidePlayer(player);
            }
        }
    }

    public static void vanishOff(Player player){
        if(vanished.contains(player)){
            vanished.remove(player);
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.showPlayer(player);
            }
        }
    }

    public static List<Player> getVanished(){
        return vanished;
    }
}
