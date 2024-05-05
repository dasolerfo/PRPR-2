package com.example.pokedexls.Entity;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private String name;
    private Sprites sprites;
    private PokemonType[] types;
    private String description;
    private Stats[] stats;
    private Ability[] abilities;


    public Sprites getSprites() {
        return sprites;
    }

    public String getName() {
        return name;
    }
}
