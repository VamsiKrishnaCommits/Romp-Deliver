package com.app.rompdeliver;
public  class item{
    public String getName() {
        return name;
    }
    public int getCost() {
        return cost;
    }
    public int getCounter() {
        return counter;
    }
    public boolean confirm=false;
public String availability;
    public int cost;
    public String name;
    public int counter=0;
    String getAvailable(){
        return  availability;
    }

    item(int cost,String name,String availability){
        this.cost=cost;
        this.name=name;
        this.availability=availability;
    }
    item(){

    }

}