package de.slowloris.community.v2.listeners;

import de.slowloris.community.v2.utils.enums.CommunityMessage;
import de.slowloris.community.v2.utils.scoreboard.ScoreboardUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        event.setQuitMessage(CommunityMessage.USER_QUIT.toString().replaceAll("%PLAYER%", event.getPlayer().getName()));
        ScoreboardUtils.buildScoreboardDefault();
    }
}
