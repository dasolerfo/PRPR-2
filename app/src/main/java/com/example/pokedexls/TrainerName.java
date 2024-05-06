package com.example.pokedexls;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class TrainerName extends AppCompatActivity {

    private EditText trainerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_name);

        trainerName = findViewById(R.id.editNameTrainer);

    }
}