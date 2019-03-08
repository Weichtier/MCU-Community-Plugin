package de.slowloris.community.v2.commands;

import de.slowloris.community.v2.utils.enums.CommunityMessage;
import de.slowloris.community.v2.utils.world.BuildUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EditCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0){
            if(sender instanceof Player){
                Player player = (Player) sender;

                if(player.hasPermission("community.command.edit")){
                    if(BuildUtils.canBuild(player)){
                        BuildUtils.buildOff(player);
                        player.sendMessage(CommunityMessage.EDITMODE_OFF.toString());
                    }else {
                        BuildUtils.buildOn(player);
                        player.sendMessage(CommunityMessage.EDITMODE_ON.toString());
                    }
                }
            }else {
                sender.sendMessage(CommunityMessage.ONLY_PLAYERS.toString());
            }
        }else if(args.length == 1){

            if(sender.hasPermission("community.command.edit.other")){
                if(Bukkit.getPlayer(args[0]) == null){
                    sender.sendMessage(CommunityMessage.PLAYER_OFFLINE.toString());
                    return false;
                }
                Player target = Bukkit.getPlayer(args[0]);
                if(BuildUtils.canBuild(target)){
                    BuildUtils.buildOff(target);
                    target.sendMessage(CommunityMessage.EDITMODE_OFF.toString());
                    sender.sendMessage(CommunityMessage.EDITMODE_OFF_OTHER.toString().replaceAll("%PLAYER%", target.getName()));
                }else {
                    BuildUtils.buildOn(target);
                    target.sendMessage(CommunityMessage.EDITMODE_ON.toString());
                    sender.sendMessage(CommunityMessage.EDITMODE_ON_OTHER.toString().replaceAll("%PLAYER%", target.getName()));
                }
            }
        }
        return false;
    }
}
