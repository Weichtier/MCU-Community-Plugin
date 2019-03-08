package de.slowloris.community.v2.utils.location;

import de.slowloris.community.v2.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationUtils {

    private static File configFile;
    private static FileConfiguration config;

    public static void setLocation(Location loc, String s){
        s = s.toLowerCase();
        config.set("Location." + s + ".World", loc.getWorld().getName());
        config.set("Location." + s + ".X", loc.getX());
        config.set("Location." + s + ".Y", loc.getY());
        config.set("Location." + s + ".Z", loc.getZ());
        config.set("Location." + s + ".Yaw", loc.getYaw());
        config.set("Location." + s + ".Pitch", loc.getPitch());
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Location getLocation(String s){
        s = s.toLowerCase();
        if(config.isConfigurationSection("Location." + s)){
            World world = Bukkit.getWorld(config.getString("Location." + s + ".World"));
            double x = config.getDouble("Location." + s + ".X");
            double y = config.getDouble("Location." + s + ".Y");
            double z = config.getDouble("Location." + s + ".Z");
            float yaw = (float) config.getDouble("Location." + s + ".Yaw");
            float pitch = (float) config.getDouble("Location." + s + ".Pitch");
            return new Location(world, x, y, z, yaw, pitch);
        }else {
            return Bukkit.getWorld("world").getSpawnLocation();
        }
    }


    public static void init(){
        configFile = new File(Main.getInstance().getDataFolder(), "locations.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
    }
}
