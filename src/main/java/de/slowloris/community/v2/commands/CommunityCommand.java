package de.slowloris.community.v2.commands;

import de.slowloris.community.v2.core.Main;
import de.slowloris.community.v2.utils.enums.CommunityMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommunityCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender.hasPermission("community.command.community")){
            if(args.length == 1){
                if(args[0].equals("reload")){
                    sender.sendMessage(Main.getPrefix() + " §cReloading...");
                    Main.initExternal();
                    sender.sendMessage(Main.getPrefix() + " §cReloaded!");
                }else {
                    sender.sendMessage(CommunityMessage.WRONG_SYNTAX.toString());
                }
            }else {
                sender.sendMessage(CommunityMessage.WRONG_SYNTAX.toString());
            }
        }else {
            sender.sendMessage(CommunityMessage.NO_PERMISSION.toString());
        }
        return false;
    }
}
