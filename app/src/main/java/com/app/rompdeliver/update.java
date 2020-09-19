package com.app.rompdeliver;

public class update {
    String availability="1";
    String name;
    int cost;
    int counter=0;
update(String name,int cost){
    this.name=name;
    this.cost=cost;
}
    public String getAvailability() {
        return availability;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getCounter() {
        return counter;
    }
}
