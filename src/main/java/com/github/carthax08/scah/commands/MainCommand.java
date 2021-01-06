package com.github.carthax08.scah.commands;

import com.github.carthax08.scah.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("You MUST be a player to use that command!");
        }else{
            Player player = (Player) sender;
            if(args.length == 0){
                Main.openAuctionHouse(player);
            }
        }
        return true;
    }
}
