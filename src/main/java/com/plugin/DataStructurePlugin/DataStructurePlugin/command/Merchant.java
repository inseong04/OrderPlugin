package com.plugin.DataStructurePlugin.DataStructurePlugin.command;

import com.plugin.DataStructurePlugin.DataStructurePlugin.AppHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Merchant implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (AppHelper.isPlayer(sender)) {

        }

        return false;
    }
}
