package de.slowloris.community.v2.listeners;

import de.slowloris.community.v2.utils.rank.RankUtils;
import de.slowloris.community.v2.utils.enums.CommunityMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        final Player player = event.getPlayer();
        String prefix = RankUtils.getChatPrefix(player);
        String message = CommunityMessage.CHAT_FORMAT.toString().replaceAll("%PLAYER%", prefix + player.getName()).replaceAll("%MESSAGE%", event.getMessage());
        if(player.hasPermission("community.coloredwriting")){
            message = ChatColor.translateAlternateColorCodes('&', message);
        }
        event.setFormat(message);
    }
}
