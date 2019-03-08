package de.slowloris.community.v2.commands;

import de.slowloris.community.v2.utils.enums.CommunityMessage;
import de.slowloris.community.v2.utils.location.LocationUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("community.command.set")){
                if(args.length == 1){
                    LocationUtils.setLocation(player.getLocation(), args[0]);
                    player.sendMessage(CommunityMessage.LOCATION_SET.toString());
                }else {
                    player.sendMessage(CommunityMessage.WRONG_SYNTAX.toString());
                }
            }else {
                player.sendMessage(CommunityMessage.NO_PERMISSION.toString());
            }
        }else {
            sender.sendMessage(CommunityMessage.ONLY_PLAYERS.toString());
        }
        return false;
    }
}
