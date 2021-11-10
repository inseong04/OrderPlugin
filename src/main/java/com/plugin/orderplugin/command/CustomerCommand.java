package com.plugin.orderplugin.command;

import com.plugin.orderplugin.AppHelper;
import com.plugin.orderplugin.OrderPlugin;
import com.plugin.orderplugin.model.OrderModel;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.Objects;

import static com.plugin.orderplugin.OrderPlugin.itemStack;
import static com.plugin.orderplugin.OrderPlugin.orderList;

public class CustomerCommand implements CommandExecutor {
    public static String merchantName;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(AppHelper.isPlayer(sender)){
            Player player = (Player)sender;
            PersistentDataContainer playerData = player.getPersistentDataContainer();
            if(args[0] != null){
                if(player.getDisplayName().startsWith("상인")){
                    player.sendMessage("상인은 이 명령어를 사용할 수 없습니다.");
                }
                else{
                    merchantName = args[0];
                    switch(merchantName){
                        case "아이템수령":{

                            if(Objects.equals(playerData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "sendItem"), PersistentDataType.STRING), "ok")){
                                String orderString = playerData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "orderString"),PersistentDataType.STRING);
                                OrderModel order = setOrderModel(playerData);

                                Objects.equals(playerData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "sendItem"), PersistentDataType.STRING), "no");

                                PlayerInventory playerInventory = player.getInventory();
                                insertItemToCustomer(playerInventory, order);
                                orderList.remove(order);
                                sender.sendMessage("주문하신 아이템이 수령되었습니다.");
                            }
                            else{
                                player.sendMessage("상인이 아이템을 준비중이거나 주문한 아이템이 없습니다.");
                            }
                            break;
                        }
                        default:{

                            ItemStack[] itemStack = {new ItemStack(Material.BREAD), new ItemStack(Material.COOKED_CHICKEN),
                                    new ItemStack(Material.WATER_BUCKET), new ItemStack(Material.MILK_BUCKET),
                                    new ItemStack(Material.TROPICAL_FISH)};
//                            ItemStack BackItem = new ItemStack(Material.RED_BED);
//                            ItemMeta meta = BackItem.getItemMeta();
//                            meta.setDisplayName("뒤로가기");
//                            BackItem.setItemMeta(meta);
//
//                            ItemStack OrderItem = new ItemStack(Material.DARK_OAK_DOOR);
//                            ItemMeta meta1 = BackItem.getItemMeta();
//                            meta1.setDisplayName("주문하기");
//                            OrderItem.setItemMeta(meta1);



                            Inventory i = Bukkit.createInventory(player, 27, "주문 메뉴");
                            for(int j=10;j<15;j++){
                                i.setItem(j+1, itemStack[j-10]);
                            }
                            i.setItem(18, setItemStack(new ItemStack(Material.RED_BED), "뒤로가기"));
                            i.setItem(26, setItemStack(new ItemStack(Material.DARK_OAK_DOOR), "주문하기"));
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

    private void insertItemToCustomer(PlayerInventory playerInventory, OrderModel order) {
        for(int i = 1; i<=itemStack.length;i++){
            int amountItem;
            switch (i){
                case 1 :{
                    amountItem = order.bread;
                    break;
                }
                case 2 : {
                    amountItem = order.chicken;
                    break;
                }
                case 3 : {
                    amountItem = order.water;
                    break;
                }
                case 4 : {
                    amountItem = order.milk;
                    break;
                }
                case 5 : {
                    amountItem = order.fish;
                    break;
                }
                default:
                    throw new IllegalStateException("Unexpected value: " + i);
            }
            List<ItemStack> itemStackList = List.of(itemStack);
            itemStackList.get(i - 1).setAmount(amountItem);
            playerInventory.setItem(i, itemStackList.get(i-1));
        }
    }

    private ItemStack setItemStack(ItemStack itemStack,String displayName){
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName("주문하기");
        itemStack.setItemMeta(meta);

        return itemStack;
    }

    public OrderModel setOrderModel(PersistentDataContainer playerData){

        final int bread = playerData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "bread"), PersistentDataType.INTEGER);
        final int milk = playerData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "milk"), PersistentDataType.INTEGER);
        final int water = playerData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "water"), PersistentDataType.INTEGER);
        final int chicken = playerData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "chicken"), PersistentDataType.INTEGER);
        final int fish = playerData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "fish"), PersistentDataType.INTEGER);

        return new OrderModel(bread, milk, water,chicken ,fish);
    }
}
