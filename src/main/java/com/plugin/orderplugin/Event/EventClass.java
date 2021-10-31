package com.plugin.orderplugin.Event;

import com.plugin.orderplugin.model.ClientRequestModel;
import com.plugin.orderplugin.model.OrderModel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static com.plugin.orderplugin.OrderPlugin.requestList;
import static com.plugin.orderplugin.command.CustomerCommand.merchantName;

public class EventClass implements Listener {

    @EventHandler
    public void openInventory(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        OrderModel orderModel = new OrderModel(player.getName());
        if(e.getView().getTitle().equals("주문 메뉴")){
            e.setCancelled(true);
            switch (e.getRawSlot()){
                case 11->{
                    orderModel.bread++;
                }
                case 12->{
                    orderModel.milk++;
                }
                case 13->{
                    orderModel.water++;
                }
                case 14->{
                    orderModel.chicken++;
                }
                case 15->{
                    orderModel.fish++;
                }
                case 18->{
                    e.getView().close();
                }
                case 26->{
                    e.getView().close();
                    requestList.add(new ClientRequestModel(player.getDisplayName(), merchantName, orderModel));
                    player.sendMessage(merchantName+"님에게"+" 주문을 보냈습니다. 기다려주세요");
                }
            }
        }
    }
}
