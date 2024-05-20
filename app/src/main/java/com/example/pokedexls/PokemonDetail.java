package com.example.pokedexls;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokedexls.Entity.Ability;
import com.example.pokedexls.Entity.Pokemon;
import com.example.pokedexls.Entity.PokemonColor;
import com.example.pokedexls.Entity.Trainer;
import com.example.pokedexls.Entity.TrainerUpdate;
import com.example.pokedexls.Persistence.PokemonDao;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.squareup.picasso.Picasso;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PokemonDetail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokemonDetail extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "dao";

    // TODO: Rename and change types of parameters
    private Pokemon pokemon;
    private String mParam2;
    private TrainerUpdate trainerUpdate;
    private static final String ARG_TRAINER = "trainer";
    private Trainer trainer;

    private PokemonDao pokemonDao;
    public PokemonDetail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment PokemonDetail.
     */
    // TODO: Rename and change types and number of parameters
    public static PokemonDetail newInstance(Pokemon param1, Trainer trainer) {
        PokemonDetail fragment = new PokemonDetail();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, param1);
        args.putSerializable(ARG_TRAINER, trainer);
        args.putSerializable(ARG_PARAM3, new PokemonDao());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        trainer = (Trainer) getArguments().getSerializable(ARG_TRAINER);
        trainerUpdate = (TrainerUpdate) getContext();
        if (getArguments() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                pokemon = getArguments().getSerializable(ARG_PARAM1, Pokemon.class);
                 pokemonDao = getArguments().getSerializable(ARG_PARAM3, PokemonDao.class);
                pokemonDao.completePokemon(this, pokemon);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false);
    }

    private void setAbilities() {

        LinearLayout abilities = getView().findViewById(R.id.llistaHabilitats);



        boolean tr = true;
        Random r = new Random();
        for (Ability ability : pokemon.getAbilities()){
            if (!tr) break;
            if (!ability.isIs_hidden()){
                if ((r.nextInt(1)+1) % 2 == 0) {
                        TextView textView = new TextView(getContext());
                        textView.setText(ability.getName());
                        textView.setTypeface(getResources().getFont(R.font.encodesanscondensedbold));
                        textView.setTextSize(18);
                        abilities.addView(textView);
                        tr = false;
                }
            }
                else if ((r.nextInt(3)) == 1){
                    TextView textView = new TextView(getContext());
                    textView.setText(ability.getName());
                    textView.setTypeface(getResources().getFont(R.font.encodesanscondensedbold), Typeface.BOLD);
                    textView.setTextSize(18);
                    abilities.addView(textView);
                    tr = false;
                }
        }
        if (tr){
            TextView textView = new TextView(getContext());
            textView.setText(pokemon.getAbilities()[0].getName());
            textView.setTypeface(getResources().getFont(R.font.encodesanscondensedbold));
            textView.setTextSize(18);
            abilities.addView(textView);
        }
    }


    private void setStats() {
        ((TextView)getView().findViewById(R.id.hp)).setText("HP: "+ pokemon.getStats()[0].getBase_stat());
        ((TextView)getView().findViewById(R.id.atk)).setText("ATK: "+ pokemon.getStats()[1].getBase_stat());
        ((TextView)getView().findViewById(R.id.DEF)).setText("DEF: "+ pokemon.getStats()[2].getBase_stat());
        ((TextView)getView().findViewById(R.id.spAtk)).setText("SP. ATK: "+ pokemon.getStats()[3].getBase_stat());
        ((TextView)getView().findViewById(R.id.SPDEF)).setText("SP. DEF: "+ pokemon.getStats()[4].getBase_stat());
        ((TextView)getView().findViewById(R.id.SPD)).setText("SPD: "+ pokemon.getStats()[5].getBase_stat());
    }

    private void setImage() {
        ImageView imageView = getView().findViewById(R.id.fotoPerfil);
        Picasso.get().load(pokemon.getSprites().getFront_default()).into(imageView);
    }

    private void creacioTypes() {

        LinearLayout typeList = this.getView().findViewById(R.id.typeList);

        LinearLayout type2 = new LinearLayout(this.getContext());
        type2.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f)); // layout_weight
        type2.setBackground(ContextCompat.getDrawable(this.getContext(), R.drawable.boto_rodonet));
        //type2.setBackgroundTintList(ContextCompat.getColorStateList(this.getContext(), PokemonColor.valueOf(pokemon.getTypes()[0].getTypeP().getName()).getColor() ));
        GradientDrawable drawable = (GradientDrawable)type2.getBackground();
        drawable.setColor(PokemonColor.valueOf(pokemon.getTypes()[0].getTypeP().getName()).getColor());
        // Crear TextView
        TextView textView = new TextView(this.getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(16, 16, 16, 16); // padding en pixels, potser necessites convertir dp a pixels
        textView.setText(pokemon.getTypes()[0].getTypeP().getName());
        textView.setTypeface(getResources().getFont(R.font.encodesanscondensedbold)); // Necessitaràs afegir la font a res/font
        textView.setTextSize(20);
        textView.setTextColor(Color.WHITE);
        type2.addView(textView);
        typeList.addView(type2);

        if (pokemon.getTypes().length > 1){
            LinearLayout type1   = new LinearLayout(this.getContext());

            type1.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1f)); // layout_weight
            type1.setBackground(ContextCompat.getDrawable(this.getContext(), R.drawable.boto_rodonet));
            GradientDrawable drawable2 = (GradientDrawable)type1.getBackground();
            drawable2.setColor(PokemonColor.valueOf(pokemon.getTypes()[1].getTypeP().getName()).getColor());// Crear TextView
            TextView textView1 = new TextView(this.getContext());
            textView1.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            textView1.setGravity(Gravity.CENTER);
            textView1.setPadding(16, 16, 16, 16); // padding en pixels, potser necessites convertir dp a pixels
            textView1.setText(pokemon.getTypes()[1].getTypeP().getName());
            textView1.setTypeface(getResources().getFont(R.font.encodesanscondensedbold), Typeface.BOLD); // Necessitaràs afegir la font a res/font
            textView1.setTextColor(Color.WHITE); // Si necessites un color específic, canvia'l aquí
            textView1.setTextSize(20);
            type1.addView(textView1);
            typeList.addView(type1);

        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        // Inflate the layout for this fragment
        TextView nom = this.getView().findViewById(R.id.nomPokemon);
        nom.setText(pokemon.getName().toUpperCase());

        LinearLayout fons = this.getView().findViewById(R.id.fonsDetalls);
        fons.setBackgroundColor(PokemonColor.valueOf(pokemon.getTypes()[0].getTypeP().getName()).getColor());

        //TODO: CAL FER UNA ALTRE CRIDA A LA API A SPECIES
        //setDescription();

        setAbilities();

        creacioTypes();

        setImage();

        setStats();


        Button button = this.getView().findViewById(R.id.capturar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
                View view = LayoutInflater.from(PokemonDetail.this.getContext()).inflate(R.layout.bottom_sheet_details, null);
                ImageButton pokeball = (ImageButton) view.findViewById(R.id.capPokeball);
                TextView tpokeball = (TextView) view.findViewById(R.id.pokeballNumber);
                ImageButton superball = (ImageButton) view.findViewById(R.id.capSuperball);
                TextView tsuperball = (TextView) view.findViewById(R.id.superballNumber);
                ImageButton ultraball = (ImageButton) view.findViewById(R.id.capUltraball);
                TextView tultraball = (TextView) view.findViewById(R.id.ultraballNumber);
                ImageButton masterball = (ImageButton) view.findViewById(R.id.capMasterball);
                TextView tmasterball = (TextView) view.findViewById(R.id.masterballNumber);

                tpokeball.setText(trainer.getNumPokeballs()+"");
                tsuperball.setText(trainer.getNumSuperballs()+"");
                tultraball.setText(trainer.getNumUltraballs()+"");
                tmasterball.setText(trainer.getNumMasterballs()+"");

                pokeball.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (trainer.getPokemons().size() < 6) {
                            if (trainer.getNumPokeballs() > 0) {

                            } else {

                            }
                        } else{
                            
                        }
                    }
                });
                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();


            }
        });
    }

    public void sendEvolutionsRemaining(int evolutionsRemaining) {
        pokemon.setForms(evolutionsRemaining);
    }
    public void setDescription(String description){
        pokemon.setDescription(description);
        TextView descripcio = (TextView) getView().findViewById(R.id.description);
        descripcio.setText(description);

    }
}