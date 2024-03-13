package com.accedotech.pokemonapi.service;

import com.accedotech.pokemonapi.dto.pokemon.PokemonDataDTO;
import com.accedotech.pokemonapi.dto.pokemon.PokemonsPageDTO;

/**
 * Interface that represents the pokemon service.
 * Contains methods with the necessary functionalities for pokémon queries.
 */
public interface PokemonService {

    // Method to obtain all paged pokémon
    PokemonsPageDTO getAllPokemons(Integer page, Integer pageSize);

    // Method to obtain a pokémon by its id
    PokemonDataDTO getPokemonById(Long pokemonId);
}
