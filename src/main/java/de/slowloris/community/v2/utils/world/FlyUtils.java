package de.slowloris.community.v2.utils.world;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class FlyUtils {

    private static ArrayList<Player> flying = new ArrayList<Player>();

    public static boolean isFlying(Player player){
        return flying.contains(player);
    }

    public static void flyOn(Player player){
        flying.add(player);
        player.setAllowFlight(true);
        player.setFlying(true);
    }

    public static void flyOff(Player player){
        flying.remove(player);
        player.setAllowFlight(false);
        player.setFlying(false);
    }

    public static ArrayList<Player> getFlying(){
        return flying;
    }
}
