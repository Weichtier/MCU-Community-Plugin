package de.slowloris.community.v2.commands;

import de.slowloris.community.v2.utils.enums.CommunityMessage;
import de.slowloris.community.v2.utils.location.LocationUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            ((Player) sender).teleport(LocationUtils.getLocation("spawn"));
        }else {
            sender.sendMessage(CommunityMessage.ONLY_PLAYERS.toString());
        }
        return false;
    }
}
