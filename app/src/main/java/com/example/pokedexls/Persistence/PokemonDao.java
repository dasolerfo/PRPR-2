package com.example.pokedexls.Persistence;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokedexls.Entity.Pokemon;
import com.example.pokedexls.PokedexFragment;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PokemonDao extends Thread {
    private int punterIncial = 1;
    private int punterFinal = 16;
    private Pokemon[] pokemons = new Pokemon[15];
    private int totalPokemonsRequested = 0;
    private boolean flag = false;
    private PokedexFragment pokemonFragment;

    public PokemonDao(PokedexFragment pokemonFragment) {
        this.pokemonFragment = pokemonFragment;
    }

    public void demanarPokemonsRecycler(Context context) {
        for (int i = punterIncial; i < punterFinal; i++) {
            makeRequest(i, context);
        }
    }

    public Pokemon[] getPokemons() {


        flag = false;
        return pokemons;
    }

    private void makeRequest(int id, Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://pokeapi.co/api/v2/pokemon/"+ id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Pokemon pokemon =  gson.fromJson(response.toString(), Pokemon.class);
                        pokemons[id - punterIncial] = pokemon;
                        totalPokemonsRequested++;
                        Log.d("Pokemons rebuts: ", String.valueOf( totalPokemonsRequested));
                        if (totalPokemonsRequested >= 15) {
                            totalPokemonsRequested = 0;
                            punterIncial += 15;
                            punterFinal += 15;
                            pokemonFragment.afegirPokemonsRecycler(pokemons);
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("resposta", "Hi ha hagut un error:" + error);
                        totalPokemonsRequested++;
                        //makeRequest(id, context);
                    }
                }
                );

        queue.add(jsonObjectRequest);
    }

    public void demanarPokemonsQuery(Context context, String searchQuery) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="https://pokeapi.co/api/v2/pokemon";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                             JSONArray array = response.getJSONArray("results");
                            for (int i = 0; i < array.length(); i++) {
                                if (((String)array.getJSONObject(i).get("name")).contains(searchQuery)){
                                    demanarPokemonConcret(context, (String)array.getJSONObject(i).get("url"));
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("resposta", "Hi ha hagut un error:" + error);
                        //makeRequest(id, context);
                    }
                }
                );

    }

    private void demanarPokemonConcret(Context context, String query) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = query;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Pokemon pokemon =  gson.fromJson(response.toString(), Pokemon.class);
                        pokemonFragment.afegirPokemon(pokemon);


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("resposta", "Hi ha hagut un error:" + error);
                        //totalPokemonsRequested++;
                        //makeRequest(id, context);
                    }
                }
                );
    }

    public void resetPointers() {
         this.punterIncial = 1;
         this.punterFinal = 16;
    }

    public boolean haFetCrida() {
        if (punterIncial == 1) return true;
        return false;
    }
}
