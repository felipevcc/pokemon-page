package com.accedotech.pokemonapi.dto.pokemon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that represents each pokémon in the paginated list.
 * Contains basic information about the pokémon.
 */
@Data
@AllArgsConstructor @NoArgsConstructor
public class PokemonDTO {
    // Unique pokémon identifier
    private Long pokemonId;

    // Pokémon name
    private String name;
}
