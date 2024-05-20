// SecondFragment.java
package com.example.pokedexls;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokedexls.Entity.TrainerUpdate;
import com.squareup.picasso.Picasso;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.pokedexls.Entity.Pokemon;
import com.example.pokedexls.Entity.Trainer;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private Button bWriteName;
    private ImageButton bFreePokemon1;
    private ImageButton bFreePokemon2;
    private ImageButton bFreePokemon3;
    private ImageButton bFreePokemon4;
    private ImageButton bFreePokemon5;
    private ImageButton bFreePokemon6;
    private static final String ARG_TRAINER = "trainer";
    private TextView tTrainerName;
    private TextView tNumberPokeballs;
    private TextView tNumberMegaballs;
    private TextView tNumberSuperballs;
    private TextView tNumberMasterball;
    private TextView tNumberCoins;
    private ArrayList<Pokemon> pokemons;
    private ImageView pokemon1;
    private ImageView pokemon2;
    private ImageView pokemon3;
    private ImageView pokemon4;
    private ImageView pokemon5;
    private ImageView pokemon6;
    private TextView pokemon1Name;
    private TextView pokemon2Name;
    private TextView pokemon3Name;
    private TextView pokemon4Name;
    private TextView pokemon5Name;
    private TextView pokemon6Name;
    private ImageView pokeball1;
    private ImageView pokeball2;
    private ImageView pokeball3;
    private ImageView pokeball4;
    private ImageView pokeball5;
    private ImageView pokeball6;
    private ImageView tImage;
    private Trainer trainer;
    private TrainerUpdate trainerUpdate;

    public static SecondFragment newInstance(Trainer trainer) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_TRAINER, trainer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trainer = (Trainer) getArguments().getSerializable(ARG_TRAINER);
        trainerUpdate = (TrainerUpdate) getContext();
    }

    private void showPokeball(Pokemon pokemon, ImageView image){
        switch (pokemon.getBallCaptured()) {
            case 1:
                image.setImageResource(R.drawable.user_pokeball);
                break;
            case 2:
                image.setImageResource(R.drawable.user_superball);
                break;
            case 3:
                image.setImageResource(R.drawable.user_ultraball);
                break;
            case 4:
                image.setImageResource(R.drawable.user_megaball);
                break;
        }
    }

    private void setPokemonsPokedex(View view){
        int option = 0;

        pokemons = trainer.getPokemons();

        bFreePokemon1 = view.findViewById(R.id.bPokemon1Free);
        bFreePokemon2 = view.findViewById(R.id.bPokemon2Free);
        bFreePokemon3 = view.findViewById(R.id.bPokemon3Free);
        bFreePokemon4 = view.findViewById(R.id.bPokemon4Free);
        bFreePokemon5 = view.findViewById(R.id.bPokemon5Free);
        bFreePokemon6 = view.findViewById(R.id.bPokemon6Free);

        pokemon1 = view.findViewById(R.id.iPokemon1);
        pokeball1 = view.findViewById(R.id.iPokemon1Pokeball);
        pokemon1Name = view.findViewById(R.id.tPokemon1Name);
        pokemon2 = view.findViewById(R.id.iPokemon2);
        pokeball2 = view.findViewById(R.id.iPokemon2Pokeball);
        pokemon2Name = view.findViewById(R.id.tPokemon2Name);
        pokemon3 = view.findViewById(R.id.iPokemon3);
        pokeball3 = view.findViewById(R.id.iPokemon3Pokeball);
        pokemon3Name = view.findViewById(R.id.tPokemon3Name);
        pokemon4 = view.findViewById(R.id.iPokemon4);
        pokeball4 = view.findViewById(R.id.iPokemon4Pokeball);
        pokemon4Name = view.findViewById(R.id.tPokemon4Name);
        pokemon5 = view.findViewById(R.id.iPokemon5);
        pokeball5 = view.findViewById(R.id.iPokemon5Pokeball);
        pokemon5Name = view.findViewById(R.id.tPokemon5Name);
        pokemon6 = view.findViewById(R.id.iPokemon6);
        pokeball6 = view.findViewById(R.id.iPokemon6Pokeball);
        pokemon6Name = view.findViewById(R.id.tPokemon6Name);

        pokemon1.setVisibility(View.INVISIBLE);
        pokeball1.setVisibility(View.INVISIBLE);
        pokemon1Name.setVisibility(View.INVISIBLE);
        pokemon2.setVisibility(View.INVISIBLE);
        pokeball2.setVisibility(View.INVISIBLE);
        pokemon2Name.setVisibility(View.INVISIBLE);
        pokemon3.setVisibility(View.INVISIBLE);
        pokeball3.setVisibility(View.INVISIBLE);
        pokemon3Name.setVisibility(View.INVISIBLE);
        pokemon4.setVisibility(View.INVISIBLE);
        pokeball4.setVisibility(View.INVISIBLE);
        pokemon4Name.setVisibility(View.INVISIBLE);

        pokemon5.setVisibility(View.INVISIBLE);
        pokeball5.setVisibility(View.INVISIBLE);
        pokemon5Name.setVisibility(View.INVISIBLE);
        pokemon6.setVisibility(View.INVISIBLE);
        pokeball6.setVisibility(View.INVISIBLE);
        pokemon6Name.setVisibility(View.INVISIBLE);

        bFreePokemon1.setVisibility(View.INVISIBLE);
        bFreePokemon2.setVisibility(View.INVISIBLE);
        bFreePokemon3.setVisibility(View.INVISIBLE);
        bFreePokemon4.setVisibility(View.INVISIBLE);
        bFreePokemon5.setVisibility(View.INVISIBLE);
        bFreePokemon6.setVisibility(View.INVISIBLE);

        for (Pokemon pokemon : pokemons){
            option++;

            switch (option){
                case 1:
                    try {
                        Picasso.get().load(pokemon.getSprites().getFront_default()).into(pokemon1);
                        showPokeball(pokemon, pokeball1);
                        pokemon1Name.setText(pokemon.getName());
                        bFreePokemon1.setVisibility(View.VISIBLE);

                        pokemon1.setVisibility(View.VISIBLE);
                        pokeball1.setVisibility(View.VISIBLE);
                        pokemon1Name.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                case 2:
                    try {
                        Picasso.get().load(pokemon.getSprites().getFront_default()).into(pokemon2);
                        showPokeball(pokemon, pokeball2);
                        pokemon2Name.setText(pokemon.getName());
                        bFreePokemon2.setVisibility(View.VISIBLE);

                        pokemon2.setVisibility(View.VISIBLE);
                        pokeball2.setVisibility(View.VISIBLE);
                        pokemon2Name.setVisibility(View.VISIBLE);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    try {
                        Picasso.get().load(pokemon.getSprites().getFront_default()).into(pokemon3);
                        showPokeball(pokemon, pokeball3);
                        pokemon3Name.setText(pokemon.getName());
                        bFreePokemon3.setVisibility(View.VISIBLE);

                        pokemon3.setVisibility(View.VISIBLE);
                        pokeball3.setVisibility(View.VISIBLE);
                        pokemon3Name.setVisibility(View.VISIBLE);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    try {
                        Picasso.get().load(pokemon.getSprites().getFront_default()).into(pokemon4);
                        showPokeball(pokemon, pokeball4);
                        pokemon4Name.setText(pokemon.getName());
                        bFreePokemon4.setVisibility(View.VISIBLE);

                        pokemon4.setVisibility(View.VISIBLE);
                        pokeball4.setVisibility(View.VISIBLE);
                        pokemon4Name.setVisibility(View.VISIBLE);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    try {
                        Picasso.get().load(pokemon.getSprites().getFront_default()).into(pokemon5);
                        showPokeball(pokemon, pokeball5);
                        pokemon5Name.setText(pokemon.getName());
                        bFreePokemon5.setVisibility(View.VISIBLE);

                        pokemon5.setVisibility(View.VISIBLE);
                        pokeball5.setVisibility(View.VISIBLE);
                        pokemon5Name.setVisibility(View.VISIBLE);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 6:
                    try {
                        Picasso.get().load(pokemon.getSprites().getFront_default()).into(pokemon6);
                        showPokeball(pokemon, pokeball6);
                        pokemon6Name.setText(pokemon.getName());
                        bFreePokemon6.setVisibility(View.VISIBLE);

                        pokemon6.setVisibility(View.VISIBLE);
                        pokeball6.setVisibility(View.VISIBLE);
                        pokemon6Name.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }

        }
    }

    private void setInfoTrainer (View view){
        bWriteName = view.findViewById(R.id.bWriteName);
        tTrainerName = view.findViewById(R.id.tTrainerName);
        tNumberPokeballs = view.findViewById(R.id.tNumberPokeballs);
        tNumberMegaballs = view.findViewById(R.id.tNumberMegaballs);
        tNumberSuperballs = view.findViewById(R.id.tNumberSuperballs);
        tNumberMasterball = view.findViewById(R.id.tNumberMasterball);
        tNumberCoins = view.findViewById(R.id.tNumberCoins);

        tTrainerName.setText(trainer.getName());
        tNumberPokeballs.setText(String.valueOf(trainer.getNumPokeballs()));
        tNumberSuperballs.setText(String.valueOf(trainer.getNumSuperballs()));
        tNumberMegaballs.setText(String.valueOf(trainer.getNumUltraballs()));
        tNumberMasterball.setText(String.valueOf(trainer.getNumMasterballs()));
        tNumberCoins.setText(String.valueOf(trainer.getMoney()) + "€");
    }

    private void freePokemon (View view, int num) {
        pokemons.remove(num);

        trainer.setPokemons(pokemons);
        trainerUpdate.actualitzaTrainer(trainer);

        setPokemonsPokedex(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        setInfoTrainer(view);
        setPokemonsPokedex(view);

        bWriteName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrainerName trainerNameFragment = TrainerName.newInstance(trainer);
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_container, trainerNameFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        bFreePokemon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pokemons.size() > 1) {
                    View layout = inflater.inflate(R.layout.toast_freepokemon, null);
                    Toast toast = new Toast(getActivity());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();

                    freePokemon (view, 0);
                }

                else {
                    View layout = inflater.inflate(R.layout.toast_cantfreepokemon, null);
                    Toast toast = new Toast(getActivity());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }

            }
        });


        bFreePokemon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pokemons.size() > 1) {
                    View layout = inflater.inflate(R.layout.toast_freepokemon, null);
                    Toast toast = new Toast(getActivity());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();

                    freePokemon (view, 1);
                }

                else {
                    View layout = inflater.inflate(R.layout.toast_cantfreepokemon, null);
                    Toast toast = new Toast(getActivity());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }
            }
        });


        bFreePokemon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pokemons.size() > 1) {
                    View layout = inflater.inflate(R.layout.toast_freepokemon, null);
                    Toast toast = new Toast(getActivity());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();

                    freePokemon (view, 2);
                }

                else {
                    View layout = inflater.inflate(R.layout.toast_cantfreepokemon, null);
                    Toast toast = new Toast(getActivity());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }
            }
        });

        bFreePokemon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pokemons.size() > 1) {
                    View layout = inflater.inflate(R.layout.toast_freepokemon, null);
                    Toast toast = new Toast(getActivity());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();

                    freePokemon (view, 3);
                }

                else{
                    View layout = inflater.inflate(R.layout.toast_cantfreepokemon, null);
                    Toast toast = new Toast(getActivity());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }

            }
        });

        bFreePokemon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pokemons.size() > 1) {
                    View layout = inflater.inflate(R.layout.toast_freepokemon, null);
                    Toast toast = new Toast(getActivity());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();

                    freePokemon (view, 4);
                }

                else {
                    View layout = inflater.inflate(R.layout.toast_cantfreepokemon, null);
                    Toast toast = new Toast(getActivity());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }
            }
        });

        bFreePokemon6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pokemons.size() > 1) {
                    View layout = inflater.inflate(R.layout.toast_freepokemon, null);
                    Toast toast = new Toast(getActivity());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();

                    freePokemon (view, 5);
                }
                else {
                    View layout = inflater.inflate(R.layout.toast_cantfreepokemon, null);
                    Toast toast = new Toast(getActivity());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }

            }
        });
        return view;
    }
}
