package com.example.pokedexls;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.pokedexls.Entity.Pokemon;
import com.example.pokedexls.Persistence.PokemonDao;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A fragment representing a list of Items.
 */
public class PokedexFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private int totalPokemonsRequested = 0;
    private ProgressBar progressBar;
    private int punterIncial = 1;
    private int punterFinal = 15;
    private Pokemon[] pokemons = new Pokemon[15];
    private RecyclerView recyclerView;
    private MyPokemonRecyclerViewAdapter adapter;
    private NestedScrollView nestedScrollView;
    private ImageView searchButton;
    private TextInputEditText textInputEditText;
    private PokemonDao pokemonDao = new PokemonDao(this);
    private String searchQuery;
    //private ArrayList<Pokemon> pokemons= new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PokedexFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PokedexFragment newInstance(int columnCount) {
        PokedexFragment fragment = new PokedexFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon_list, container, false);

        // Set the adapter

        return view;
    }

    private void demanarNousPokemons() {
        pokemonDao.demanarPokemonsRecycler(this.getContext());
    }

    public void afegirPokemonsRecycler(Pokemon[] llistaPokemons){
        adapter.addPokemons(llistaPokemons);
        progressBar.setVisibility(View.INVISIBLE);
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.nestedScrollView = getView().findViewById(R.id.nestedView);
        this.recyclerView = getView().findViewById(R.id.list);
        this.progressBar = getView().findViewById(R.id.progress_circular);
        this.searchButton = getView().findViewById(R.id.botoBusca);
        this.textInputEditText = getView().findViewById(R.id.textField);

        if (this.recyclerView instanceof RecyclerView) {
            Context context = this.recyclerView.getContext();
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            if (pokemonDao.haFetCrida()) {
                adapter = new MyPokemonRecyclerViewAdapter();
                recyclerView.setAdapter(adapter);
                demanarNousPokemons();
            } else {
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
               // pokemonDao.resetPointers();
            }


            nestedScrollView.setOnScrollChangeListener(new  NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                    if ( i1 == nestedScrollView.getChildAt(0).getMeasuredHeight() - nestedScrollView.getMeasuredHeight()){
                            progressBar.setVisibility(View.VISIBLE);
                           demanarNousPokemons();

                    }
                }

            });
            this.searchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getQuery();
                }
            });

        }
    }

    private void getQuery() {

        this.adapter.cleanAll();
        this.pokemonDao.resetPointers();
        String searchQuery = this.textInputEditText.getText().toString();
        if (!searchQuery.equals("")){
            this.pokemonDao.demanarPokemonsQuery(this.getContext(), searchQuery);
        } else {
            demanarNousPokemons();
        }

    }

    public void afegirPokemon(Pokemon pokemon) {
        int i = adapter.addPokemon(pokemon);
        getActivity().runOnUiThread(new Runnable() {
            public void run() {
                adapter.notifyItemInserted(i);
            }
        });
    }
}
