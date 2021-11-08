package com.plugin.orderplugin.command;

import com.plugin.orderplugin.AppHelper;
import com.plugin.orderplugin.OrderPlugin;
import com.plugin.orderplugin.model.ClientRequestModel;
import com.plugin.orderplugin.model.MerChantModel;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import java.util.LinkedList;
import java.util.Queue;

public class MerchantCommand implements CommandExecutor {


    PersistentDataContainer MerchantData;

    public static String clientName;
    public static String requestItem;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        MerchantData = p.getPersistentDataContainer();
        if (AppHelper.isPlayer(sender)) {



            sender.sendMessage(String.valueOf(args[0]));
            if (args[0] != null) {
                switch (args[0]) {
                    case "수락":
                    case "거절": {

                        order(args, sender);

                        break;
                    }

                    case "완료": {
                        orderComplete(sender);
                        break;
                    }

                    case "주문확인": {
                        orderLookup(sender);
                        break;
                    }

                    case "대기열": {
                        orderQueue(sender);
                        break;
                }

                    case "도움말": {
                        sender.sendMessage("§l/상점 수락 :"+" 들어온 주문을 수락합니다. \n"+
                                "§l/상점 거절 :"+" 들어온 주문을 거절합니다. \n"+
                                "§l/상점 주문확인 :"+" 상점의 주문을 확인합니다.");

                        break;
                    }
                }


            }
            return true;
        }
        else {
            //콘솔창에서 사용한 경우
            sender.sendMessage("플레이어가 아닙니다.");
            return false;
        }
    }

    private void order(String[] args, CommandSender sender) {

        if (!MerchantData.has(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "sendToCustomData"), PersistentDataType.STRING)) {
            sender.sendMessage(sender.getName() + " 님에게 들어온 주문이 없습니다.");
        }

        else {
            // if request exist

            if(args[0].equals("수락")) {

                sender.sendMessage("before : "+String.valueOf(OrderPlugin.merchantQueue.size()));


                MerChantModel merChantModel = new MerChantModel(MerchantData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "clientName"), PersistentDataType.STRING),
                        MerchantData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "sendToCustomData"), PersistentDataType.STRING));
                OrderPlugin.merchantQueue.add(merChantModel);
                sender.sendMessage(merChantModel.getClientName() + " 님의 주문을 수락합니다.");

                Player targetPlayer = sender.getServer().getPlayer(merChantModel.getClientName());
                targetPlayer.sendMessage(sender.getName() + " 님이 주문을 수락하였습니다!");
                sender.sendMessage("after"+String.valueOf(OrderPlugin.merchantQueue.size()));

            }

            else if (args[0].equals("거절")) {


                Player targetPlayer = sender.getServer().getPlayer(clientName);
                targetPlayer.sendMessage(sender.getName() + "님이 주문을 거절하였습니다.");
            }

            // if request exist

        }
    }

    private void orderComplete(CommandSender sender) {
        if (OrderPlugin.merchantQueue.peek() != null) {
            MerChantModel request = OrderPlugin.merchantQueue.poll();
            Player targetPlayer = sender.getServer().getPlayer(request.getClientName());
            targetPlayer.sendMessage(sender.getName()+"에서"+targetPlayer.getName()+" 님이 주문한 아이템이 완성되었습니다!\n아이템을 받으려면 /주문 아이템수령 을 입력해주세요!");

            PersistentDataContainer targetPlayerData = targetPlayer.getPersistentDataContainer();

            targetPlayerData.set(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "sendItem")
                    , PersistentDataType.STRING, "ok");

        }
        else {
            sender.sendMessage("아직 수락한 주문이 없습니다.");
        }
    }

    private void orderLookup(CommandSender sender) {
        if (OrderPlugin.merchantQueue.peek() != null) {
            MerChantModel request = OrderPlugin.merchantQueue.peek();
            sender.sendMessage("--------------------------\n"+
                    "대기열 첫번째 주문\n"+"주문자 : "+request.getClientName()+"\n"+
                    "주문 아이템 : "+request.getRequestItem()+"\n"
                    +"--------------------------");
        }
        else {
            sender.sendMessage("아직 수락한 주문이 없습니다.");
        }
    }

    private void orderQueue(CommandSender sender) {
        sender.sendMessage(String.valueOf(OrderPlugin.merchantQueue.size()));

        if (OrderPlugin.merchantQueue.peek() != null) {
            Queue<MerChantModel> readQueue = OrderPlugin.merchantQueue;
            int readQueueSize = readQueue.size();
            sender.sendMessage("--------------------------");

            for (int i=0; readQueueSize > i; i++) {

                MerChantModel request = readQueue.poll();
                readQueue.add(request);

                String message ="주문한 고객 이름 : "+request.getClientName()+"\n"
                        +"주문한 아이템 : "+request.getRequestItem()+"\n";

                sender.sendMessage(message);

            }

            sender.sendMessage("--------------------------");
        }
        else {
            sender.sendMessage("상점의 대기열이 없습니다.");
        }

    }
}




