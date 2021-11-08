package com.plugin.orderplugin.command;

import com.plugin.orderplugin.AppHelper;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomerCommand implements CommandExecutor {
    public static String merchantName;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(AppHelper.isPlayer(sender)){
            Player player = (Player)sender;
            if(args[0] != null){
                if(player.getDisplayName().startsWith("상인")){
                    player.sendMessage("상인은 이 명령어를 사용할 수 없습니다.");
                }
                else{
                    merchantName = args[0];
                    switch(merchantName){
                        case "아이템수령":{
                            PlayerInventory playerInventory= player.getInventory();
                            break;
                        }
                        default:{
                            ItemStack BackItem = new ItemStack(Material.RED_BED);
                            ItemMeta meta = BackItem.getItemMeta();
                            meta.setDisplayName("뒤로가기");
                            BackItem.setItemMeta(meta);

                            ItemStack OrderItem = new ItemStack(Material.DARK_OAK_DOOR);
                            ItemMeta meta1 = BackItem.getItemMeta();
                            meta1.setDisplayName("주문하기");
                            OrderItem.setItemMeta(meta1);

                            ItemStack[] itemStack = {new ItemStack(Material.BREAD), new ItemStack(Material.COOKED_CHICKEN),
                                    new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.MILK_BUCKET),
                                    new ItemStack(Material.TROPICAL_FISH)};

                            Inventory i = Bukkit.createInventory(player, 27, "주문 메뉴");
                            for(int j=10;j<15;j++){
                                i.setItem(j+1, itemStack[j-10]);
                            }
                            i.setItem(18, BackItem);
                            i.setItem(26, OrderItem);
                            player.openInventory(i);
                        }
                    }
                }
            }
            else{
                sender.sendMessage("메세지를 올바르게 입력하세요.");
            }
            return true;
        }
        else{
            sender.sendMessage("플레이어가 아닙니다.");
            return false;
        }
    }
}
