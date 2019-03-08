package de.slowloris.community.v2.core;

import de.slowloris.community.v2.commands.*;
import de.slowloris.community.v2.listeners.*;
import de.slowloris.community.v2.utils.events.EventManager;
import de.slowloris.community.v2.utils.inventory.InventoryUtils;
import de.slowloris.community.v2.utils.location.LocationUtils;
import de.slowloris.community.v2.utils.rank.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    private static Main instance;
    private static PluginManager pluginManager;
    private static EventManager eventManager;
    private static File messageFile;
    private static FileConfiguration messageConfiguration;
    private static String prefix;

    @Override
    public void onEnable() {
        getLogger().info("Booting up CommunityV2...");

        init();

        getLogger().info("Booted up CommunityV2!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Shutting down CommunityV2...");
    }

    public static Main getInstance() {
        return instance;
    }

    private static PluginManager getPluginManager() {
        return pluginManager;
    }

    public static EventManager getEventManager() {
        return eventManager;
    }

    public static FileConfiguration getMessageConfiguration() {
        return messageConfiguration;
    }

    public static String getPrefix() {
        return prefix;
    }

    private void init(){

        saveResource("config.yml", false);
        saveResource("messages.yml", false);
        saveResource("ranks.yml", false);
        saveResource("inventories.yml", false);

        instance = this;
        pluginManager = Bukkit.getPluginManager();
        eventManager = new EventManager();
        prefix = getConfig().getString("Config.Prefix").replaceAll("&", "§");

        getPluginManager().registerEvents(new JoinListener(), instance);
        getPluginManager().registerEvents(new QuitListener(), instance);
        getPluginManager().registerEvents(new AsyncChatListener(), instance);
        getPluginManager().registerEvents(new InteractListener(), instance);
        getPluginManager().registerEvents(new InventoryClickListener(), instance);
        getPluginManager().registerEvents(new LittleListeners(), instance);
        getCommand("community").setExecutor(new CommunityCommand());
        getCommand("set").setExecutor(new SetCommand());
        getCommand("edit").setExecutor(new EditCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("event").setExecutor(new EventCommand());

        RankUtils.init();
        LocationUtils.init();
        InventoryUtils.init();
        messageFile = new File(getDataFolder(), "messages.yml");
        messageConfiguration = YamlConfiguration.loadConfiguration(messageFile);

        for (World world : Bukkit.getWorlds()) {
            world.setGameRuleValue("doDaylightCycle", "false");
            world.setTime(6000);
        }
    }

    public static void initExternal(){
        getInstance().init();
    }
}
