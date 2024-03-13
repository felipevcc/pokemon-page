package com.accedotech.pokemonapi.dto.pokeapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Class that represents the PokeAPI response.
 * Contains the total Pokémon count and a list of Pokémon results.
 */
@Data
@AllArgsConstructor @NoArgsConstructor
public class PokemonApiResponse {
    // Total of all registered pokémons
    private Long count;

    // List of requested pokémons
    private List<PokemonResult> results;
}
