package com.plugin.DataStructurePlugin.DataStructurePlugin;

import org.bukkit.plugin.java.JavaPlugin;

public class DataStructurePlugin extends JavaPlugin {
    @Override
    public void onDisable() {
        super.onDisable();
        getLogger().info("DataStructure Plugin 활성화");
    }

    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().info("DataStructure Plugin 비활성화");
    }
}
