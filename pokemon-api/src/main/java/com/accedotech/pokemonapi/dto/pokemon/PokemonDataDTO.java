package com.accedotech.pokemonapi.dto.pokemon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Class that represents the Pokémon's data.
 */
@Data
@AllArgsConstructor @NoArgsConstructor
public class PokemonDataDTO {
    // Unique pokémon identifier
    private Long pokemonId;

    // Pokémon name
    private String name;

    // Small Pokémon image
    private String smallImage;

    // Large Pokémon image
    private String largeImage;

    // A list of abilities that this Pokémon could potentially have
    private List<String> abilities;

    // The base experience gained from defeating the Pokémon
    private Integer baseExperience;

    // URL of the cry used to represent this Pokémon in the game
    private String cry;

    // Pokémon properties
    private List<String> types;

    // The weight of the Pokémon in hectograms
    private Integer weight;
}
