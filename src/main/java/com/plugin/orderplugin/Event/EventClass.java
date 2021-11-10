package com.plugin.orderplugin.Event;

import com.plugin.orderplugin.OrderPlugin;
import com.plugin.orderplugin.command.MerchantCommand;
import com.plugin.orderplugin.model.ClientRequestModel;
import com.plugin.orderplugin.model.OrderModel;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.logging.Logger;

import static com.plugin.orderplugin.OrderPlugin.orderList;
import static com.plugin.orderplugin.OrderPlugin.requestList;
import static com.plugin.orderplugin.command.CustomerCommand.merchantName;
public class EventClass implements Listener {

    OrderModel orderModel = new OrderModel(0, 0, 0, 0, 0);

    @EventHandler
    public void openInventory(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        orderModel.userName = player.getName();
        if(e.getView().getTitle().equals("주문 메뉴") && e.getClick() != ClickType.DOUBLE_CLICK){
            e.setCancelled(true);
            switch (e.getRawSlot()){
                case 11->{
                    orderModel.bread++;
                }
                case 12->{
                    orderModel.chicken++;
                }
                case 13->{
                    orderModel.water++;
                }
                case 14->{
                    orderModel.milk++;
                }
                case 15->{
                    orderModel.fish++;
                }
                case 18->{
                    e.getView().close();
                }
                case 26->{
                    //requestList.add(new ClientRequestModel(player.getDisplayName(), merchantName, orderModel));
                    player.sendMessage(merchantName+"님에게"+" 주문을 보냈습니다. 기다려주세요");
                    Player targetPlayer = player.getServer().getPlayer(merchantName);

                    PersistentDataContainer targetPlayerData = targetPlayer.getPersistentDataContainer();

                    sendDataToMerChant(targetPlayerData);


                    targetPlayerData.set(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "clientName")
                            , PersistentDataType.STRING, player.getName());

                    targetPlayer.sendMessage("[상점] "+player.getName()+" 님이 "+orderModel.setString()+ "주문을 요청하였습니다." +
                        "\n"+"요청을 수락하시려면 /상점 수락" +
                            "\n"+"요청을 거절하시려면 /상점 거절");

                    orderList.add(orderModel);

                    e.getView().close();
                    orderModel.setZero();
                }
            }
        }
    }

    private void sendDataToMerChant(PersistentDataContainer targetPlayer) {
        targetPlayer.set(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "bread")
                , PersistentDataType.INTEGER, orderModel.bread);

        targetPlayer.set(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "chicken")
                , PersistentDataType.INTEGER, orderModel.chicken);

        targetPlayer.set(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "water")
                , PersistentDataType.INTEGER, orderModel.water);

        targetPlayer.set(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "milk")
                , PersistentDataType.INTEGER, orderModel.milk);

        targetPlayer.set(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "fish")
                , PersistentDataType.INTEGER, orderModel.fish);
    }


}
