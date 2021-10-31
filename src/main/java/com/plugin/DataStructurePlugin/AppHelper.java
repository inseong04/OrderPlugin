package com.plugin.DataStructurePlugin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AppHelper {
    public static Boolean isPlayer(CommandSender sender) {
        if (sender instanceof Player)
            return true;
        else
            return false;
    }
}