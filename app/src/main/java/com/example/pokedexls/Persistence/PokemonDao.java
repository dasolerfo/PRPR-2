package com.example.pokedexls.Persistence;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pokedexls.Entity.Ability;
import com.example.pokedexls.Entity.Pokemon;
import com.example.pokedexls.Entity.PokemonType;
import com.example.pokedexls.Entity.Sprites;
import com.example.pokedexls.Entity.Stats;
import com.example.pokedexls.Entity.TypeP;
import com.example.pokedexls.PokedexFragment;
import com.example.pokedexls.PokemonDetail;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class PokemonDao extends Thread implements Serializable {
    private int punterIncial = 1;
    private int punterFinal = 16;
    private Pokemon[] pokemons = new Pokemon[15];
    private int totalPokemonsRequested = 0;
    private boolean flag = false;

    private PokedexFragment pokemonFragment;
    private PokemonDetail pokemonDetail;

    public PokemonDao(PokedexFragment pokemonFragment) {
        this.pokemonFragment = pokemonFragment;
    }

    public PokemonDao(){}

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

                        Log.d("Pokemons rebuts: ", String.valueOf( totalPokemonsRequested));
                        Gson gson = new Gson();
                       // Pokemon pokemon =  gson.fromJson(response.toString(), Pokemon.class);
                        Pokemon pokemon = new Pokemon();
                        try {
                            pokemon.setName(response.getString("name"));
                            pokemon.setStats(gson.fromJson(response.getJSONArray("stats").toString(),
                                    Stats[].class));
                            pokemon.setTypes(gson.fromJson(response.getJSONArray("types").toString(), PokemonType[].class));
                            pokemon.setSprites(gson.fromJson(response.getJSONObject("sprites").toString(), Sprites.class));
                            pokemon.setAbilities(gson.fromJson(response.getJSONArray("abilities").toString(), Ability[].class));
                            pokemon.setUrlDescription(response.getJSONObject("species").getString("url"));




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        pokemons[id - punterIncial] = pokemon;
                        totalPokemonsRequested++;

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
        queue.add(jsonObjectRequest);

    }


    public void demanarEvolucionsPokemon(String query, String pokemonName, Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        //String url ="https://pokeapi.co/api/v2/pokemon";
        String url = query;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        int evolutionsRemaining = 0;
                        try{
                            JSONObject evolutionDetails = response.getJSONObject("chain");

                            boolean conta = false;
                            while (evolutionDetails.getJSONArray("evolves_to").length() > 0) {

                                if (evolutionDetails.getJSONObject("species").getString("name").equals(pokemonName))
                                {
                                    conta = true;
                                }

                                if (conta){
                                evolutionsRemaining++;
                                }
                                evolutionDetails = evolutionDetails.getJSONArray("evolves_to").getJSONObject(0);

                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            pokemonDetail.sendEvolutionsRemaining(evolutionsRemaining);
                            //TODO : ENVIA A LA VISTA
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
        queue.add(jsonObjectRequest);

    }

    private void demamanarDescripcioPokemon(String query, String pokemonName, Context context){
        RequestQueue queue = Volley.newRequestQueue(context);
        //String url ="https://pokeapi.co/api/v2/pokemon";
        String url = query;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String description = "";
                        try {
                             description = response.getJSONArray("flavor_text_entries").getJSONObject(0)
                                    .getString("flavor_text");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            pokemonDetail.setDescription(description);
                            try {
                                demanarEvolucionsPokemon(response.getJSONObject("evolution_chain").getString("url"), pokemonName, pokemonDetail.getContext());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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
        queue.add(jsonObjectRequest);
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
        queue.add(jsonObjectRequest);
    }



    public void resetPointers() {
         this.punterIncial = 1;
         this.punterFinal = 16;
    }

    public boolean haFetCrida() {
        if (punterIncial == 1) return true;
        return false;
    }


    public void completePokemon(PokemonDetail pokemonDetail, Pokemon pokemon) {
        this.pokemonDetail = pokemonDetail;
        demamanarDescripcioPokemon(pokemon.getUrlDescription(), pokemon.getName(), pokemonDetail.getContext());

    }
}
