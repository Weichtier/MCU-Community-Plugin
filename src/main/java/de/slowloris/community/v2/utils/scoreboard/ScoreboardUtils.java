package de.slowloris.community.v2.utils.scoreboard;

import de.slowloris.community.v2.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardUtils {

    public static void buildScoreboard(Scoreboard scoreboard, String title, List<String> list){
        ScoreboardBuilder builder = new ScoreboardBuilder(scoreboard);
        builder.setTitle(title);
        builder.addLines(list);
        builder.setForAllPlayers();
    }

    public static void buildScoreboardDefault(){
        List<String> list = new ArrayList<String>();
        for (String s : Main.getInstance().getConfig().getStringList("Config.Scoreboard.Lines")) {
            if(Main.getEventManager().getEvent() != null){
                s = s.replaceAll("%EVENT%", Main.getEventManager().getEvent().getClass().getSimpleName());
            }else {
                s = s.replaceAll("%EVENT%", "None");
            }

            list.add(s.replaceAll("&", "§"));
        }
        buildScoreboard(
                Bukkit.getScoreboardManager().getMainScoreboard(),
                Main.getInstance().getConfig().getString("Config.Scoreboard.Title").replaceAll("&", "§"),
                list);
    }
}
