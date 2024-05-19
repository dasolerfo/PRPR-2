package com.example.pokedexls.Entity;

import java.io.Serializable;

public class Ability implements Serializable {
     private boolean is_hidden;
     private AbilityP ability;


     public boolean isIs_hidden() {
          return is_hidden;
     }

     public String getName() {
          return ability.getName().toUpperCase().charAt(0) +
                  ability.getName().substring(1, ability.getName().length()).toLowerCase();
     }
}
