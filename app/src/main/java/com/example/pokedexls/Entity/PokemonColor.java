package com.example.pokedexls.Entity;

import android.graphics.Color;

public enum PokemonColor {
        LightBlue(0xFFBAC7FF),
        LightGrey(0xFFAAAAAA),
        HPColor(0xFFF5FF00),
        AtkColor(1f, 0f, 0f, 0.66f),
        DefColor(0f, 0f, 1f, 0.44f),
        SpAtkColor(0.671f, 0f, 1f, 0.57f),
        SpDefColor(1f, 0f, 0.8f, 0.7f),
        SpdColor(0f, 1f, 0.063f, 0.55f),
        normal(0xFFA8A77A),
        fire(0xFFEE8130),
        water(0xFF6390F0),
        electric(0xFFF7D02C),
        grass(0xFF7AC74C),
        ice(0xFF96D9D6),
        fighting(0xFFC22E28),
        poison(0xFFA33EA1),
        ground(0xFFE2BF65),
        flying(0xFFA98FF3),
        psychic(0xFFF95587),
        bug(0xFFA6B91A),
        rock(0xFFB6A136),
        ghost(0xFF735797),
        dragon(0xFF6F35FC),
        dark(0xFF705746),
        steel(0xFFB7B7CE),
        fairy(0xFFD685AD);

        private int color;

        PokemonColor(int color) {
            this.color = color;
        }

        PokemonColor(float r, float g, float b, float alpha) {
            this.color = Color.argb((int)(alpha * 255), (int)(r * 255), (int)(g * 255), (int)(b * 255));
        }

        public int getColor() {
            return color;
        }
    }

