package com.accedotech.pokemonapi.dto.pokeapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that represents each result in the pokemon list.
 */
@Data
@AllArgsConstructor @NoArgsConstructor
public class PokemonResult {
    // Pokémon name
    private String name;

    // URL to consult detailed information about the Pokémon
    private String url;
}
