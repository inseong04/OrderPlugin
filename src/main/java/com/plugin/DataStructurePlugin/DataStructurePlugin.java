package com.plugin.DataStructurePlugin;
import com.plugin.DataStructurePlugin.Event.EventClass;
import com.plugin.DataStructurePlugin.command.CustomerCommand;
import com.plugin.DataStructurePlugin.command.MerchantCommand;
import com.plugin.DataStructurePlugin.command.StartCommand;
import com.plugin.DataStructurePlugin.model.ClientRequestModel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class DataStructurePlugin extends JavaPlugin {

    public static List<ClientRequestModel> requestList = new ArrayList<>();


    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().info("DataStructure Plugin 활성화");
        getServer().getPluginManager().registerEvents(new EventClass(), this);
        setCommend();
    }

    private void setCommend() {
        getCommand("상점").setExecutor(new MerchantCommand());
        getCommand("setplayer").setExecutor(new StartCommand());
        getCommand("주문").setExecutor(new CustomerCommand());
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info("DataStructure Plugin 비활성화");


    }

//    public void registerMerchantTag(){
//        Player p = null;
//        p.getPlayer();
//        p.setCustomName("상인");
//        Team t = s.registerNewTeam("상인");
//        t.setPrefix(ChatColor.BLUE + "");
//
//    }
//
//    public void registerCustomerTag(){
//        Player p = null;
//        p.setCustomName("손님");
//        Team t = s.registerNewTeam("손님");
//        t.setPrefix(ChatColor.RED + "");
//    }


}
