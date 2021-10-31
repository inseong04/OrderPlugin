package com.plugin.orderplugin.command;

import com.plugin.orderplugin.AppHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (AppHelper.isPlayer(sender)) {
            if(args[0] != null){
                Player p = (Player) sender;
                p.setCustomNameVisible(true);
                String playerName = p.getDisplayName();

                if(playerName.startsWith("손님 ") ||
                        playerName.startsWith("상인 ")){
                    playerName = playerName.substring(3);
                }
                switch (args[0]){
                    case "상인" ->{
                        sender.sendMessage("당신은 상인이 되었습니다.");
                        p.setDisplayName("상인 "+playerName);
                    }
                    case "손님" -> {
                        sender.sendMessage("당신은 손님이 되었습니다.");
                        p.setDisplayName("손님 "+playerName);
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
