package com.accedotech.pokemonapi.dto.pokemon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Class that represents the list of paged pokémons.
 * Contains basic information about the pokémon.
 */
@Data
@AllArgsConstructor @NoArgsConstructor
public class PokemonsPageDTO {
    // Requested page
    private Integer page;

    // Requested page size
    private Integer pageSize;

    // Total of all registered pokémons
    private Long totalPokemons;

    // Total pages
    private Integer totalPages;

    // List of pokémons
    private List<PokemonDTO> pokemons;
}
