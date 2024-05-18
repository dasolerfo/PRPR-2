package com.example.pokedexls.Entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Trainer implements Serializable{
    private String name;
    private int numPokeballs;
    private int numSuperballs;
    private int numUltraballs;
    private int numMasterballs;
    private int money;
    private ArrayList<Pokemon> pokemons;

    public Trainer() {
        this.name = "Trainer";
        this.numPokeballs = 20;
        this.numSuperballs = 10;
        this.numUltraballs = 5;
        this.numMasterballs = 2;
        this.money = 2000;
        this.pokemons = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumPokeballs() {
        return numPokeballs;
    }

    public void setNumPokeballs(int numPokeballs) {
        this.numPokeballs = numPokeballs;
    }

    public int getNumSuperballs() {
        return numSuperballs;
    }

    public void setNumSuperballs(int numSuperballs) {
        this.numSuperballs = numSuperballs;
    }

    public int getNumUltraballs() {
        return numUltraballs;
    }

    public void setNumUltraballs(int numUltraballs) {
        this.numUltraballs = numUltraballs;
    }

    public int getNumMasterballs() {
        return numMasterballs;
    }

    public void setNumMasterballs(int numMasterballs) {
        this.numMasterballs = numMasterballs;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(ArrayList<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}
