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

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public abstract class MainActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ShopFragment thirdFragment = new ShopFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.bottomNavigation);

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
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.frame_container);

        if (fragment == null){
            fragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.frame_container, fragment).commit();
        }

    }


}