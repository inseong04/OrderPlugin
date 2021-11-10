package com.plugin.orderplugin.command;

import com.plugin.orderplugin.AppHelper;
import com.plugin.orderplugin.OrderPlugin;
import com.plugin.orderplugin.model.MerChantModel;
import com.plugin.orderplugin.model.OrderModel;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.LinkedList;
import java.util.Queue;

public class MerchantCommand implements CommandExecutor {


    PersistentDataContainer MerchantData;
    private Queue<MerChantModel> merchantQueue = new LinkedList<>();
    ;
    public static String clientName;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        MerchantData = p.getPersistentDataContainer();
        if (AppHelper.isPlayer(sender)) {

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
                        sender.sendMessage("§l/상점 수락 :" + " 들어온 주문을 수락합니다. \n" +
                                "§l/상점 거절 :" + " 들어온 주문을 거절합니다. \n" +
                                "§l/상점 주문확인 :" + " 상점의 주문을 확인합니다.");

                        break;
                    }
                }


            }
            return true;
        } else {
            //콘솔창에서 사용한 경우
            sender.sendMessage("플레이어가 아닙니다.");
            return false;
        }
    }

    private void order(String[] args, CommandSender sender) {

        if (!MerchantData.has(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "sendToData"), PersistentDataType.STRING)) {
            sender.sendMessage(sender.getName() + " 님에게 들어온 주문이 없습니다.");
        } else {
            // if request exist

            final int bread = MerchantData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "bread"), PersistentDataType.INTEGER);
            final int milk = MerchantData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "milk"), PersistentDataType.INTEGER);
            final int water = MerchantData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "water"), PersistentDataType.INTEGER);
            final int chicken = MerchantData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "chicken"), PersistentDataType.INTEGER);
            final int fish = MerchantData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "fish"), PersistentDataType.INTEGER);

            OrderModel orderModel = new OrderModel(bread, milk, water, chicken, fish);

            MerChantModel merChantModel = new MerChantModel(MerchantData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "clientName"), PersistentDataType.STRING),
                    MerchantData.get(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "sendToData"), PersistentDataType.STRING),
                    orderModel);

            if (args[0].equals("수락")) {



                merchantQueue.add(merChantModel);
                sender.sendMessage(merChantModel.getClientName() + " 님의 주문을 수락합니다.");

                Player targetPlayer = sender.getServer().getPlayer(merChantModel.getClientName());
                targetPlayer.sendMessage(sender.getName() + " 님이 주문을 수락하였습니다!");

            } else if (args[0].equals("거절")) {


                Player targetPlayer = sender.getServer().getPlayer(merChantModel.getClientName());
                sender.sendMessage(targetPlayer.getName()+" 님의 주문을 거절하였습니다.");
                targetPlayer.sendMessage(sender.getName() + "님이 주문을 거절하였습니다.");
            }

            // if request exist

        }
    }

    private void orderComplete(CommandSender sender) {
        if (merchantQueue.peek() != null) {
            MerChantModel request = merchantQueue.peek();
            Player targetPlayer = sender.getServer().getPlayer(request.getClientName());
            Player player = sender.getServer().getPlayer(sender.getName());

            ItemStack[] itemStacks = player.getInventory().getContents();
            if (request.getOrderModel().bread > 0) {

                if (AppHelper.isNotSatisfyItem(itemStacks, Material.BREAD, request.getOrderModel().bread)) {
                    sender.sendMessage("현재 인벤토리에 빵이 존재하지 않거나 갯수가 부족합니다.");
                    return;
                }
                ItemStack itemStack = new ItemStack(Material.BREAD);
                itemStack.setAmount(request.getOrderModel().bread);
                player.getInventory().removeItem(itemStack);
            }
            if (request.getOrderModel().chicken > 0) {
                if (AppHelper.isNotSatisfyItem(itemStacks, Material.COOKED_CHICKEN, request.getOrderModel().chicken)) {
                    sender.sendMessage("현재 인벤토리에 치킨이 존재하지 않거나 갯수가 부족합니다.");
                    return;
                }
                ItemStack itemStack = new ItemStack(Material.COOKED_CHICKEN);
                itemStack.setAmount(request.getOrderModel().chicken);
                player.getInventory().removeItem(itemStack);

            }
            if (request.getOrderModel().fish > 0) {
                if (AppHelper.isNotSatisfyItem(itemStacks, Material.TROPICAL_FISH, request.getOrderModel().fish)) {
                    sender.sendMessage("현재 인벤토리에 열대어가 존재하지 않거나 갯수가 부족합니다.");
                    return;
                }
                ItemStack itemStack = new ItemStack(Material.TROPICAL_FISH);
                itemStack.setAmount(request.getOrderModel().fish);
                player.getInventory().removeItem(itemStack);
            }
            if (request.getOrderModel().milk > 0) {

                if (AppHelper.isNotSatisfyItem(itemStacks, Material.MILK_BUCKET, request.getOrderModel().milk)) {
                    sender.sendMessage("현재 인벤토리에 우유가 존재하지 않거나 갯수가 부족합니다.");
                    return;
                }
                ItemStack itemStack = new ItemStack(Material.MILK_BUCKET);
                itemStack.setAmount(request.getOrderModel().milk);
                player.getInventory().removeItem(itemStack);

            }
            if (request.getOrderModel().water > 0) {

                if (AppHelper.isNotSatisfyItem(itemStacks, Material.WATER_BUCKET, request.getOrderModel().water)) {
                    sender.sendMessage("현재 인벤토리에 물이 존재하지 않거나 갯수가 부족합니다.");
                    return;
                }
                ItemStack itemStack = new ItemStack(Material.WATER_BUCKET);
                itemStack.setAmount(request.getOrderModel().water);
                player.getInventory().remove(itemStack);
            }

            player.sendMessage("아이템을 성공적으로 "+targetPlayer.getName()+" 님에게 보냈습니다.");


            targetPlayer.sendMessage(sender.getName() + "에서" + targetPlayer.getName() + " 님이 주문한 아이템이 완성되었습니다!\n아이템을 받으려면 /주문 아이템수령 을 입력해주세요!");

            PersistentDataContainer targetPlayerData = targetPlayer.getPersistentDataContainer();

            targetPlayerData.set(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "sendItem")
                    , PersistentDataType.STRING, "ok");
            targetPlayerData.set(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "bread"),
                    PersistentDataType.INTEGER, request.getOrderModel().bread);
            targetPlayerData.set(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "chicken"),
                    PersistentDataType.INTEGER, request.getOrderModel().chicken);
            targetPlayerData.set(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "fish"),
                    PersistentDataType.INTEGER, request.getOrderModel().fish);
            targetPlayerData.set(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "milk"),
                    PersistentDataType.INTEGER, request.getOrderModel().milk);
            targetPlayerData.set(new NamespacedKey(OrderPlugin.getPlugin(OrderPlugin.class), "water"),
                    PersistentDataType.INTEGER, request.getOrderModel().water);

            merchantQueue.poll();

        } else {
            sender.sendMessage("아직 수락한 주문이 없습니다.");
        }
    }

    private void orderLookup(CommandSender sender) {
        if (merchantQueue.peek() != null) {
            MerChantModel request = merchantQueue.peek();
            sender.sendMessage("--------------------------\n" +
                    "대기열 첫번째 주문\n" + "주문자 : " + request.getClientName() + "\n" +
                    "주문 아이템 : " + request.getRequestItem() + "\n"
                    + "--------------------------");
        } else {
            sender.sendMessage("아직 수락한 주문이 없습니다.");
        }
    }

    private void orderQueue(CommandSender sender) {

        if (merchantQueue.peek() != null) {
            Queue<MerChantModel> readQueue = merchantQueue;
            int readQueueSize = readQueue.size();
            sender.sendMessage("--------------------------");

            for (int i = 0; readQueueSize > i; i++) {

                MerChantModel request = readQueue.poll();
                readQueue.add(request);

                String message = "주문한 고객 이름 : " + request.getClientName() + "\n"
                        + "주문한 아이템 : " + request.getRequestItem() + "\n";

                sender.sendMessage(message);

            }

            sender.sendMessage("--------------------------");
        } else {
            sender.sendMessage("상점의 대기열이 없습니다.");
        }

    }
}




