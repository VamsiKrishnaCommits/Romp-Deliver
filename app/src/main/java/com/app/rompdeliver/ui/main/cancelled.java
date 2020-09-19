package com.app.rompdeliver.ui.main;

public class cancelled {
    String phone;
    int cost;

    cancelled(String phone,int cost){
        this.phone=phone;
        this.cost=cost;
    }

    public int getCost() {
        return cost;
    }

    public String getPhone() {
        return phone;
    }
}
