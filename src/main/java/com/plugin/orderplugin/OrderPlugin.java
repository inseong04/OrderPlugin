package com.plugin.orderplugin;
import com.plugin.orderplugin.command.MerchantCommand;
import com.plugin.orderplugin.command.StartCommand;
import com.plugin.orderplugin.model.ClientRequestModel;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class OrderPlugin extends JavaPlugin {

    public static List<ClientRequestModel> requestList = new ArrayList<>();


    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().info("Order Plugin 활성화");
        getCommand("상점").setExecutor(new MerchantCommand());
        getCommand("setplayer").setExecutor(new StartCommand());
    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info("Order Plugin 비활성화");


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
