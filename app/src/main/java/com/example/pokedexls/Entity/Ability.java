package com.example.pokedexls.Entity;

import java.io.Serializable;

public class Ability implements Serializable {
     private boolean is_hidden;
     private String name;
     private String url;

     public boolean isIs_hidden() {
          return is_hidden;
     }

     public String getName() {
          return name;
     }
}
