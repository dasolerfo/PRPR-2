package com.example.pokedexls;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        File cache = getCacheDir();
        cache.setReadOnly();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_content);
        if (fragment == null){
            fragment = new PokedexFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_content, fragment).commit();

        }
    }
}