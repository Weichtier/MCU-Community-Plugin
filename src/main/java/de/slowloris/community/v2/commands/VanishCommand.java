package de.slowloris.community.v2.commands;

import de.slowloris.community.v2.utils.enums.CommunityMessage;
import de.slowloris.community.v2.utils.world.BuildUtils;
import de.slowloris.community.v2.utils.world.VanishUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length == 0){
            if(sender instanceof Player){
                Player player = (Player) sender;

                if(player.hasPermission("community.command.vanish")){
                    if(VanishUtils.isVanish(player)){
                        VanishUtils.vanishOff(player);
                        player.sendMessage(CommunityMessage.VANISH_OFF.toString());
                    }else {
                        VanishUtils.vanishOn(player);
                        player.sendMessage(CommunityMessage.VANISH_ON.toString());
                    }
                }
            }else {
                sender.sendMessage(CommunityMessage.ONLY_PLAYERS.toString());
            }
        }else if(args.length == 1){

            if(args[0].equals("list")){
                for (Player player : VanishUtils.getVanished()) {
                    sender.sendMessage(player.getName());
                }
            }else {
                if(sender.hasPermission("community.command.vanish.other")){
                    if(Bukkit.getPlayer(args[0]) == null){
                        sender.sendMessage(CommunityMessage.PLAYER_OFFLINE.toString());
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if(VanishUtils.isVanish(target)){
                        VanishUtils.vanishOff(target);
                        target.sendMessage(CommunityMessage.VANISH_OFF.toString());
                        sender.sendMessage(CommunityMessage.VANISH_OFF_OTHER.toString().replaceAll("%PLAYER%", target.getName()));
                    }else {
                        VanishUtils.vanishOn(target);
                        target.sendMessage(CommunityMessage.VANISH_ON.toString());
                        sender.sendMessage(CommunityMessage.VANISH_ON_OTHER.toString().replaceAll("%PLAYER%", target.getName()));
                    }
                }
            }
        }
        return false;
    }
}
