package de.slowloris.community.v2.commands;

import de.slowloris.community.v2.utils.enums.CommunityMessage;
import de.slowloris.community.v2.utils.world.FlyUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0){
            if(sender instanceof Player){
                Player player = (Player) sender;

                if(player.hasPermission("community.command.fly")){
                    if(FlyUtils.isFlying(player)){
                        FlyUtils.flyOff(player);
                        player.sendMessage(CommunityMessage.FLY_OFF.toString());
                    }else {
                        FlyUtils.flyOn(player);
                        player.sendMessage(CommunityMessage.FLY_ON.toString());
                    }
                }
            }else {
                sender.sendMessage(CommunityMessage.ONLY_PLAYERS.toString());
            }
        }else if(args.length == 1){

            if(args[0].equals("list")){
                for (Player player : FlyUtils.getFlying()) {
                    sender.sendMessage(player.getName());
                }
            }else {
                if(sender.hasPermission("community.command.fly.other")){
                    if(Bukkit.getPlayer(args[0]) == null){
                        sender.sendMessage(CommunityMessage.PLAYER_OFFLINE.toString());
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if(FlyUtils.isFlying(target)){
                        FlyUtils.flyOff(target);
                        target.sendMessage(CommunityMessage.FLY_OFF.toString());
                        sender.sendMessage(CommunityMessage.FLY_OFF_OTHER.toString().replaceAll("%PLAYER%", target.getName()));
                    }else {
                        FlyUtils.flyOn(target);
                        target.sendMessage(CommunityMessage.FLY_ON.toString());
                        sender.sendMessage(CommunityMessage.FLY_ON_OTHER.toString().replaceAll("%PLAYER%", target.getName()));
                    }
                }
            }
        }
        return false;
    }
}
