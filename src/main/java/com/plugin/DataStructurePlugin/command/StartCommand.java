package com.plugin.DataStructurePlugin.command;

import com.plugin.DataStructurePlugin.AppHelper;
import com.plugin.DataStructurePlugin.DataStructurePlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (AppHelper.isPlayer(sender)) {
            if(args[0] != null){
                Player p = (Player) sender;
                p.setCustomNameVisible(true);
                switch (args[0]){
                    case "상인" ->{
                        sender.sendMessage("당신은 상인이 되었습니다.");
                        p.setCustomName("상인");;
                    }
                    case "손님" -> {
                        sender.sendMessage("당신은 손님이 되었습니다.");
                        p.setCustomName("손님");
                    }
                }
            }
            return true;
        }
        else {
            sender.sendMessage("플레이어가 아닙니다.");
        }

        return false;
    }
}
