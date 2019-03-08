package de.slowloris.community.v2.utils.rank;

import de.slowloris.community.v2.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class RankUtils {

    private static HashMap<String, String> chatprefixes = new HashMap<String, String>();
    private static HashMap<String, String> tabprefixes = new HashMap<String, String>();
    private static HashMap<String, Team> scoreboardTeams = new HashMap<String, Team>();

    public static String getChatPrefix(Player player){

        return getPrefix(player, chatprefixes);
    }

    public static String getTabPrefix(Player player){
        return getPrefix(player, tabprefixes);
    }

    private static String getPrefix(Player player, HashMap<String, String> tabprefixes) {
        String prefix = "";

        for(Map.Entry<String, String> entry : tabprefixes.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if(player.hasPermission(key)){
                prefix = value;
            }
        }
        return prefix;
    }

    public static Team getScoreboardTeam(Player player){
        Team team = null;
        for(Map.Entry<String, Team> entry : scoreboardTeams.entrySet()) {
            String key = entry.getKey();
            Team value = entry.getValue();
            if(player.hasPermission(key)){
                team = value;
            }
        }
        return team;
    }

    public static void setTabPrefix(Player player){
        Team team = getScoreboardTeam(player);
        if(!team.hasPlayer(player)){
            team.addPlayer(player);
        }
    }

    public static HashMap<String, String> getChatprefixes() {
        return chatprefixes;
    }

    public static HashMap<String, String> getTabprefixes() {
        return tabprefixes;
    }

    public static HashMap<String, Team> getScoreboardTeams() {
        return scoreboardTeams;
    }

    public static void init(){
        FileConfiguration ranksConfiguration = YamlConfiguration.loadConfiguration(new File(Main.getInstance().getDataFolder(), "ranks.yml"));
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        for (int i = 0; i < 20; i++){
            if(ranksConfiguration.isConfigurationSection("Rank." + i)){
                RankUtils.getChatprefixes().put(ranksConfiguration.getString("Rank." + i + ".permission"), ranksConfiguration.getString("Rank." + i + ".chat").replaceAll("&", "§"));
                RankUtils.getTabprefixes().put(ranksConfiguration.getString("Rank." + i + ".permission"), ranksConfiguration.getString("Rank." + i + ".tab").replaceAll("&", "§"));
                if(sb.getTeam("0" + i) == null){
                    sb.registerNewTeam("0" + i);
                }
                sb.getTeam("0" + i).setPrefix(ranksConfiguration.getString("Rank." + i + ".tab").replaceAll("&", "§"));
                RankUtils.getScoreboardTeams().put(ranksConfiguration.getString("Rank." + i + ".permission"), sb.getTeam("0" + i));
            }
        }
    }
}
