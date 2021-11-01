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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBread() {
        return bread;
    }

    public void setBread(int bread) {
        this.bread = bread;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getChicken() {
        return chicken;
    }

    public void setChicken(int chicken) {
        this.chicken = chicken;
    }

    public int getFish() {
        return fish;
    }

    public void setFish(int fish) {
        this.fish = fish;
    }

    public void setZero(){
        this.fish = 0;
        this.chicken = 0;
        this.water = 0;
        this.milk = 0;
        this.bread = 0;
    }
}
