package com.example.pokedexls;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.pokedexls.Entity.Trainer;
import com.example.pokedexls.Entity.TrainerUpdate;
import com.example.pokedexls.Persistence.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public abstract class MainActivity extends AppCompatActivity implements TrainerUpdate {

    protected abstract Fragment createFragment();

    PokedexFragment firstFragment = new PokedexFragment();
    SecondFragment secondFragment = new SecondFragment();
    ShopFragment thirdFragment = new ShopFragment();
    private Trainer trainer;
    private SharedPrefManager sharedPrefManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.bottomNavigation);
        sharedPrefManager = new SharedPrefManager(this);
        trainer = sharedPrefManager.getTrainer();
        if (trainer == null){
            trainer = new Trainer();
            sharedPrefManager.saveTrainer(trainer);
        }

        firstFragment = PokedexFragment.newInstance(0, trainer);
        //secondFragment = SecondFragment.newInstance(trainer);
        thirdFragment = ShopFragment.newInstance(trainer);
        loadFragment(firstFragment);

        navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.firstFragment){
                    loadFragment(firstFragment);

                }
                if (item.getItemId() == R.id.secondFragment){
                    loadFragment(secondFragment);

                }
                if (item.getItemId() == R.id.thirdFragment){
                    loadFragment(thirdFragment);
                }
                return true;
            }
        });

        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    public void actualitzaTrainer(Trainer trainer){
        this.trainer = trainer;
        sharedPrefManager.saveTrainer(trainer);

        firstFragment = PokedexFragment.newInstance(0, trainer);
        //secondFragment = SecondFragment.newInstance(trainer);
        thirdFragment = ShopFragment.newInstance(trainer);
    }

}