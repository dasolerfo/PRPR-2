package com.example.pokedexls;

public class Trainer {
    private String name;
    private double money;

    public Trainer(String name, double money){
        this.name = name;
        this.money = money;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


}
