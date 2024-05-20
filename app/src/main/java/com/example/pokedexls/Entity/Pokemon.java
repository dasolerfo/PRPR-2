package com.example.pokedexls.Entity;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private String name;
    private Sprites sprites;
    private PokemonType[] types;
    private String description;
    private int evolution = 4;
    private boolean captured;
    private int ballCaptured;

    public Ability[] getAbilities() {
        return abilities;
    }

    private Stats[] stats;
    private Ability[] abilities;


    public Sprites getSprites() {
        return sprites;
    }

    public String getName() {
        return name;
    }

    public PokemonType[] getTypes() {
        return types;
    }

    public Stats[] getStats() {
        return stats;
    }

    public int getEvolution() {
        return evolution;
    }

    public void setEvolution(int evolution) {
        this.evolution = evolution;
    }

    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public int getBallCaptured() {
        return ballCaptured;
    }

    public void setBallCaptured(int ballCaptured) {
        this.ballCaptured = ballCaptured;
    }
}
