package com.plugin.orderplugin;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AppHelper {
    public static Boolean isPlayer(CommandSender sender) {
        if (sender instanceof Player)
            return true;
        else
            return false;
    }

    public static Boolean isNotSatisfyItem(ItemStack[] itemStacks, Material targetMaterial, int targetAmount) {
        // if item is satisfied -> false, else true.

        for (ItemStack item : itemStacks) {
            if (item == null) {
                continue;
            }
            if (item.getType() == targetMaterial && item.getAmount() >= targetAmount) {
                return false;
            }
        }

        return true;
    }
}