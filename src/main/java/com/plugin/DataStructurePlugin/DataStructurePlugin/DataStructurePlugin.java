package com.plugin.DataStructurePlugin.DataStructurePlugin;

import com.plugin.DataStructurePlugin.DataStructurePlugin.command.StartCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class DataStructurePlugin extends JavaPlugin {
    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info("DataStructure Plugin 활성화");
        getCommand("start").setExecutor(new StartCommand());

    }

    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().info("DataStructure Plugin 비활성화");
    }
}
