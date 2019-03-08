package de.slowloris.community.v2.utils.events;

import de.slowloris.community.v2.utils.inventory.InventoryUtils;
import de.slowloris.community.v2.utils.scoreboard.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PvPEvent implements CommunityEvent {

    public void start() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            InventoryUtils.pvpOn(all);
        }
        ScoreboardUtils.buildScoreboardDefault();
    }

    public void stop() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            InventoryUtils.pvpOff(all);
        }
        ScoreboardUtils.buildScoreboardDefault();
    }

    public void playerJoined(Player player) {
        InventoryUtils.pvpOn(player);
    }

    public void playerLeaved(Player player) {
    }
}
