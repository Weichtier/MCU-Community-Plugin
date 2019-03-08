package de.slowloris.community.v2.utils.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public class ScoreboardBuilder {

    private Scoreboard scoreboard;
    private Objective objective;
    private Integer addLineCount = 0;

    ScoreboardBuilder(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
        if(scoreboard.getObjective("sidebar") != null){
            scoreboard.getObjective("sidebar").unregister();
        }
        scoreboard.registerNewObjective("sidebar", "dummy");
        this.objective = scoreboard.getObjective("sidebar");
    }

    public void setTitle(String s){
        objective.setDisplayName(s);
    }

    public void setLine(String s, Integer line){
        s = s.replaceAll("%ONLINE%", String.valueOf(Bukkit.getOnlinePlayers().size())).replaceAll("%MAX%", String.valueOf(Bukkit.getMaxPlayers()));

        objective.getScore(s).setScore(line);
    }

    public void addLine(String s){
        setLine(s, addLineCount);
        addLineCount--;
    }

    public void addLines(List<String> list){
        for (String s : list) {
            addLine(ChatColor.translateAlternateColorCodes('&', s));
        }
    }

    public void setForAllPlayers(){
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(scoreboard);
        }
    }
}
