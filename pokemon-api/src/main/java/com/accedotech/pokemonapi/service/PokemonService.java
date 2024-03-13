package com.accedotech.pokemonapi.service;

import com.accedotech.pokemonapi.dto.pokemon.PokemonDataDTO;
import com.accedotech.pokemonapi.dto.pokemon.PokemonsPageDTO;

public interface PokemonService {

    PokemonsPageDTO getAllPokemons(Integer page, Integer pageSize);

    PokemonDataDTO getPokemonById(Long pokemonId);
}
