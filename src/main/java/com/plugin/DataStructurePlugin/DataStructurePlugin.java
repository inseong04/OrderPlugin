package com.plugin.DataStructurePlugin;
import com.plugin.DataStructurePlugin.command.MerchantCommand;
import com.plugin.DataStructurePlugin.model.ClientRequestModel;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class DataStructurePlugin extends JavaPlugin {

    public static List<ClientRequestModel> requestList = new ArrayList<>();


    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().info("DataStructure Plugin 활성화");
        getCommand("상점").setExecutor(new MerchantCommand());

    }

    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info("DataStructure Plugin 비활성화");


    }


}
