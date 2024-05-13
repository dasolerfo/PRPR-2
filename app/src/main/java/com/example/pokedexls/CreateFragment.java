package com.example.pokedexls;

import androidx.fragment.app.Fragment;

public class CreateFragment extends MainActivity{
    @Override
    protected Fragment createFragment() {
        return new TrainerName();
    }
}
