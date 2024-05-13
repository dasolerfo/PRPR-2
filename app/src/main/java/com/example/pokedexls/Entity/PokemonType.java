package com.example.pokedexls.Entity;

import java.io.Serializable;

public class PokemonType implements Serializable {
    private int slot;
    private TypeP type;

    public TypeP getTypeP() {
        return type;
    }
}
