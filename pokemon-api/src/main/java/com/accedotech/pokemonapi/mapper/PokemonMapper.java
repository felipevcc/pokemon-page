package com.accedotech.pokemonapi.mapper;

import com.accedotech.pokemonapi.dto.pokeapi.PokemonApiResponse;
import com.accedotech.pokemonapi.dto.pokeapi.PokemonResult;
import com.accedotech.pokemonapi.dto.pokemon.PokemonDTO;
import com.accedotech.pokemonapi.dto.pokemon.PokemonsPageDTO;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Pokémon class to map objects from one class to another.
 * Contains the necessary mappings.
 */
@Configuration
public class PokemonMapper {

    /**
     * Method to map objects from the PokemonApiResponse class to the PokemonsPageDTO class.
     */
    public PokemonsPageDTO pokemonApiResponseToDTO(
            Integer page,
            Integer pageSize,
            PokemonApiResponse response
    ) {
        PokemonsPageDTO pokemonsPage = new PokemonsPageDTO();

        pokemonsPage.setPage(page);
        pokemonsPage.setPageSize(pageSize);
        pokemonsPage.setTotalPokemons(response.getCount());

        // Set the total number of pages by dividing the total pokémon and the page size
        pokemonsPage.setTotalPages((int) Math.ceil((double) response.getCount() / pageSize));

        // Set the id and name of each pokémon
        List<PokemonDTO> pokemonDTOList = new ArrayList<>();
        for (PokemonResult result : response.getResults()) {
            Long pokemonId = extractPokemonId(result.getUrl());
            pokemonDTOList.add(new PokemonDTO(pokemonId, result.getName()));
        }
        pokemonsPage.setPokemons(pokemonDTOList);

        return pokemonsPage;
    }

    /**
     * Method to extract the Pokémon ID within its url.
     */
    private Long extractPokemonId(String pokemonUrl) {
        String[] parts = pokemonUrl.split("/");
        return Long.parseLong(parts[parts.length - 1]);
    }
}
