package de.slowloris.community.v2.listeners;

import de.slowloris.community.v2.utils.enums.CommunityMessage;
import de.slowloris.community.v2.utils.inventory.InventoryUtils;
import de.slowloris.community.v2.utils.location.LocationUtils;
import de.slowloris.community.v2.utils.rank.RankUtils;
import de.slowloris.community.v2.utils.scoreboard.ScoreboardUtils;
import de.slowloris.community.v2.utils.world.VanishUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        event.setJoinMessage(CommunityMessage.USER_JOIN.toString().replaceAll("%PLAYER%", event.getPlayer().getName()));
        RankUtils.setTabPrefix(event.getPlayer());
        event.getPlayer().teleport(LocationUtils.getLocation("spawn"));

        event.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
        for (Player vanished : VanishUtils.getVanished()) {
            event.getPlayer().hidePlayer(vanished);
        }
        ScoreboardUtils.buildScoreboardDefault();
        InventoryUtils.setInventory(event.getPlayer());
    }
}
