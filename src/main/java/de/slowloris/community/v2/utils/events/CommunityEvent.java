package de.slowloris.community.v2.utils.events;

import org.bukkit.entity.Player;

public interface CommunityEvent {
    public void start();
    public void stop();
    public void playerJoined(Player player);
    public void playerLeaved(Player player);
}
