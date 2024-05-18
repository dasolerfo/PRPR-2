package com.example.pokedexls.Persistence;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pokedexls.Entity.Trainer;
import com.google.gson.Gson;

public class SharedPrefManager {
    private static final String PREFNAME = "trainer";
    private static final String KEY = "key_trainer";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson gson;

    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFNAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        gson = new Gson();
    }

    public void saveTrainer(Trainer usuario) {
        String json = gson.toJson(usuario);
        editor.putString(KEY, json);
        editor.apply();
    }

    public Trainer getTrainer() {
        String json = sharedPreferences.getString(KEY, null);
        if (json != null) {
            return gson.fromJson(json, Trainer.class);
        }
        return null;
    }
}
