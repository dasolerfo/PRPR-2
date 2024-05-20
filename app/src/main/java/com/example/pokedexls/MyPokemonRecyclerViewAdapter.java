package com.example.pokedexls;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokedexls.Entity.Pokemon;
import com.example.pokedexls.Entity.PokemonColor;
import com.example.pokedexls.Entity.Trainer;
import com.example.pokedexls.Entity.TypeP;
import com.example.pokedexls.Persistence.PokemonDao;
import com.example.pokedexls.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.pokedexls.databinding.FragmentPokedexBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPokemonRecyclerViewAdapter extends RecyclerView.Adapter<MyPokemonRecyclerViewAdapter.ViewHolder> {

    private final List<Pokemon> mValues = new ArrayList<>();
    private Trainer trainer;

    public MyPokemonRecyclerViewAdapter(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentPokedexBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        TypeP type = mValues.get(position).getTypes()[0].getTypeP();
        holder.itemView.setBackgroundColor(PokemonColor.valueOf(type.getName()).getColor());
        Picasso.get().load(mValues.get(position).getSprites().getFront_default()).into(holder.pokeFoto);
        holder.pokeNom.setText(mValues.get(position).getName().toUpperCase());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PokemonDetail secondFragment = new PokemonDetail();


                /*Bundle bundle = new Bundle();
                bundle.putSerializable("param1", mValues.get(position));*/

                PokemonDetail fragment;
                fragment = PokemonDetail.newInstance(mValues.get(position), trainer);
                int a = trainer.getNumPokeballs();
                ((AppCompatActivity) v.getContext()).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .addToBackStack(null)
                        .commit();


            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public int addPokemon(Pokemon pokemon) {
        mValues.add(pokemon);
        return mValues.size() - 1;
    }

    public void cleanAll() {
        mValues.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView pokeFoto;
        public final TextView pokeNom;
        public Pokemon mItem;


        public ViewHolder(FragmentPokedexBinding binding) {
            super(binding.getRoot());
            pokeFoto = binding.pokemonFoto;
            pokeNom = binding.content;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + pokeNom.getText() + "'";
        }
    }

    public void addPokemons(Pokemon[] llista){
        mValues.addAll(Arrays.asList(llista));
    }


}