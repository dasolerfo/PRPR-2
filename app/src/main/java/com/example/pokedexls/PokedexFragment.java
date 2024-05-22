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

import com.android.volley.toolbox.Volley;
import com.example.pokedexls.Entity.Pokemon;
import com.example.pokedexls.Entity.Trainer;
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
    private static final String ARG_TRAINER = "trainer";
    private PokemonDao pokemonDao = new PokemonDao(this);
    private Trainer trainer;
    private String searchQuery;
    private boolean refresh = true;
    private boolean recarrega = false;


    //private ArrayList<Pokemon> pokemons= new ArrayList<>();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PokedexFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PokedexFragment newInstance(int columnCount, Trainer trainer) {
        PokedexFragment fragment = new PokedexFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        args.putSerializable(ARG_TRAINER, trainer);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        trainer = (Trainer) getArguments().getSerializable(ARG_TRAINER);
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
        if (isAdded()) {
            if (!recarrega){
                adapter.addPokemons(llistaPokemons);
                progressBar.setVisibility(View.INVISIBLE);
                pokemonDao.setNewPointers();
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            } else {
                recarrega = false;
                pokemonDao.resetPointers();
                demanarNousPokemons();
            }

        }

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

            if (pokemonDao.haFetCrida() & adapter == null ||  adapter.getItemCount() > 0) {
                adapter = new MyPokemonRecyclerViewAdapter(trainer);
                recyclerView.setAdapter(adapter);
                //demanarNousPokemons();
            } else {
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }


            nestedScrollView.setOnScrollChangeListener(new  NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
                    if (refresh){
                        if ( i1 == nestedScrollView.getChildAt(0).getMeasuredHeight() - nestedScrollView.getMeasuredHeight()){
                            progressBar.setVisibility(View.VISIBLE);
                            demanarNousPokemons();
                        }
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
            this.refresh = false;
            this.pokemonDao.demanarPokemonsQuery(this.getContext(), searchQuery);
        } else {
            this.refresh = true;
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

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onResume() {
        super.onResume();

        if (adapter.getItemCount() == 0 && !pokemonDao.isCooking())
        {
            pokemonDao.resetPointers();
            demanarNousPokemons();
        } else if (pokemonDao.isCooking())  {
            recarrega = true;
        } else {
            pokemonDao.resetPointers();
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

}

