package com.plugin.orderplugin.model;

public class OrderModel {
    public String userName;
    public int bread;
    public int milk;
    public int water;
    public int chicken;
    public int fish;

    public  OrderModel(int bread, int milk, int water, int chicken, int fish){
        this.bread = bread;
        this.milk = milk;
        this.water = water;
        this.chicken = chicken;
        this.fish = fish;
    }

    public void setZero(){
        this.fish = 0;
        this.chicken = 0;
        this.water = 0;
        this.milk = 0;
        this.bread = 0;
    }

    public String setString(){
        String orderStr = "빵:"+bread+"개/우유:"+milk+"개/물:"+water+"개/치킨:"+chicken+"개/생선"+fish+"개";

        return orderStr;
    }
}
