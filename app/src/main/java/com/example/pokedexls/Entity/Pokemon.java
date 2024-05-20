package com.example.pokedexls.Entity;

import java.io.Serializable;

public class Pokemon implements Serializable {
    private String name;
    private Sprites sprites;
    private PokemonType[] types;
    private String description;
    private boolean is_legendary;

    private Stats[] stats;
    private Ability[] abilities;


    private String urlDescription;
    private String urlForms;
    private String Description;
    private int forms;


    public void setName(String name) {
        this.name = name;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public void setTypes(PokemonType[] types) {
        this.types = types;
    }

    public String getDescription() {
        return description;
    }

    public int getForms() {
        return forms;
    }

    public void setStats(Stats[] stats) {
        this.stats = stats;
    }

    public void setAbilities(Ability[] abilities) {
        this.abilities = abilities;
    }

    public void setIs_legendary(boolean is_legendary) {
        this.is_legendary = is_legendary;
    }

    public void setUrlDescription(String urlDescription) {
        this.urlDescription = urlDescription;
    }

    public void setUrlForms(String urlForms) {
        this.urlForms = urlForms;
    }
    private int evolution = 4;
    private boolean captured;
    private int ballCaptured;


    public Ability[] getAbilities() {
        return abilities;
    }

    public boolean isIs_legendary() {
        return is_legendary;
    }


    public String getUrlDescription() {
        return urlDescription;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setForms(int forms) {
        this.forms = forms;
    }

    public String getUrlForms() {
        return urlForms;
    }

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
