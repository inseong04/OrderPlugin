package com.plugin.DataStructurePlugin.command;

import com.plugin.DataStructurePlugin.AppHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (AppHelper.isPlayer(sender)) {

            return true;
        }
        else {
            sender.sendMessage("플레이어가 아닙니다.");
        }

        return false;
    }
}
