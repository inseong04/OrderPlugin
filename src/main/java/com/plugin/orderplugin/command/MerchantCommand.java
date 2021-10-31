package com.plugin.orderplugin.command;

import com.plugin.orderplugin.AppHelper;
import com.plugin.orderplugin.OrderPlugin;
import com.plugin.orderplugin.model.ClientRequestModel;
import com.plugin.orderplugin.model.MerChantModel;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.LinkedList;
import java.util.Queue;

public class MerchantCommand implements CommandExecutor {

    Queue<MerChantModel> merchantQueue = new LinkedList<>();

    public static String clientName;
    public static String requestItem;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (AppHelper.isPlayer(sender)) {



            sender.sendMessage(String.valueOf(args[0]));
            if (args[0] != null) {
                switch (args[0]) {
                    case "수락":
                    case "거절": {

                            //if server request exist

/*                            MerChantModel merChantModel = null;

                            ClientRequestModel clientRequestModel;
                            for (int i = 0; i < OrderPlugin.requestList.size(); i++) {
                                 clientRequestModel = OrderPlugin.requestList.get(i);
                                if (sender.getName().equals(clientRequestModel.getMerchantName())) {
                                    merChantModel = new MerChantModel
                                            (clientRequestModel.getClientName(), clientRequestModel.getRequestItem());
                                    break;
                                }
                                clientRequestModel = null;
                            }*/


                            if (clientName == null) {
                                sender.sendMessage(sender.getName() + " 님에게 들어온 주문이 없습니다.");
                            }

                            else {
                                // if request exist

                                if(args[0].equals("수락")) {
                                    MerChantModel merChantModel = new MerChantModel(clientName, requestItem);
                                    merchantQueue.add(merChantModel);
                                    sender.sendMessage(merChantModel.getClientName() + " 님의 주문을 수락합니다.");

                                    Player targetPlayer = sender.getServer().getPlayer(merChantModel.getClientName());
                                    targetPlayer.sendMessage(sender.getName() + " 님이 주문을 수락하였습니다!");
                                }

                                else if (args[0].equals("거절")) {
                                    Player targetPlayer = sender.getServer().getPlayer(clientName);
                                    targetPlayer.sendMessage(sender.getName() + "님이 주문을 거절하였습니다.");
                                }

                                // if request exist

                        }

                        break;
                    }

                    case "완료": {
                        if (merchantQueue.peek() != null) {
                            MerChantModel request = merchantQueue.poll();
                            Player targetPlayer = sender.getServer().getPlayer(request.getClientName());
                            targetPlayer.sendMessage(sender.getName()+"에서"+targetPlayer.getName()+" 님이 주문한 아이템이 완성되었습니다!");


                        }
                        else {
                            sender.sendMessage("아직 수락한 주문이 없습니다.");
                        }
                    }

                    case "주문확인": {
                        if (merchantQueue.peek() != null) {
                            MerChantModel request = merchantQueue.peek();
                            sender.sendMessage("주문자 : "+request.getClientName()+"\n"+
                                    "주문 아이템 : "+request.getRequestItem()+"\n");
                        }
                        else {
                            sender.sendMessage("아직 수락한 주문이 없습니다.");
                        }
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
}




