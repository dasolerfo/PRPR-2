package com.example.pokedexls;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokedexls.Entity.Pokemon;
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

    public MyPokemonRecyclerViewAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentPokedexBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Picasso.get().load(mValues.get(position).getSprites().getFront_default()).into(holder.pokeFoto);
        holder.pokeNom.setText(mValues.get(position).getName());
        //holder.mIdView.setText(mValues.get(position).id);
        //holder.mContentView.setText(mValues.get(position).content);
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