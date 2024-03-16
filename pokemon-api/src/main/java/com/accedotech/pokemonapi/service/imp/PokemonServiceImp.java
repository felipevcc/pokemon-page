package com.accedotech.pokemonapi.service.imp;

import com.accedotech.pokemonapi.dto.pokeapi.PokemonApiResponse;
import com.accedotech.pokemonapi.dto.pokemon.PokemonDataDTO;
import com.accedotech.pokemonapi.dto.pokemon.PokemonsPageDTO;
import com.accedotech.pokemonapi.exceptions.PokeAPINotAvailableException;
import com.accedotech.pokemonapi.exceptions.PokemonNotFoundException;
import com.accedotech.pokemonapi.mapper.PokemonMapper;
import com.accedotech.pokemonapi.service.PokemonService;
import com.accedotech.pokemonapi.util.PokemonDataHelper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Pokémon service class.
 * Contains methods for Pokémon queries.
 */
@Service
public class PokemonServiceImp implements PokemonService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PokemonMapper pokemonMapper;

    // Url of the PokeAPI service
    public static final String POKE_API_URL = "https://pokeapi.co/api/v2/pokemon/";

    /**
     * Method to obtain all paged pokémon.
     */
    @Override
    public PokemonsPageDTO getAllPokemons(Integer page, Integer pageSize) {
        // Check if the page and page size are valid
        if (page <= 0 || pageSize <= 0) {
            throw new IllegalArgumentException("The page and page size must be positive numbers");
        }

        // Endpoint construction
        String apiUrl = POKE_API_URL + "?offset=" + ((page - 1) * pageSize) + "&limit=" + pageSize;

        try {
            // Make the paginated request to the PokeAPI service
            PokemonApiResponse response = restTemplate.getForObject(apiUrl, PokemonApiResponse.class);

            // Check if the PokeAPI service responded correctly
            if (response == null) {
                throw new PokeAPINotAvailableException();
            }
            // Map and return paged pokémons
            return pokemonMapper.pokemonApiResponseToDTO(page, pageSize, response);

        } catch (RestClientException e) {
            e.fillInStackTrace();
            throw new PokeAPINotAvailableException();
        }
    }

    /**
     * Method to obtain a pokémon by its id.
     */
    @Override
    public PokemonDataDTO getPokemonById(Long pokemonId) {
        // Endpoint construction
        String apiUrl = POKE_API_URL + pokemonId;

        try {
            // Make the Pokémon request to the PokeAPI service
            String response = restTemplate.getForObject(apiUrl, String.class);

            // Create JSON object from response text string
            JSONObject jsonObject = new JSONObject(response);

            PokemonDataDTO pokemonData = new PokemonDataDTO();
            pokemonData.setPokemonId(pokemonId);
            pokemonData.setName(jsonObject.optString("name", null));

            // Pokémon images
            PokemonDataHelper.setPokemonImages(jsonObject, pokemonData);

            PokemonDataHelper.setPokemonAbilities(jsonObject, pokemonData);
            pokemonData.setBaseExperience(jsonObject.optInt("base_experience", -1));
            pokemonData.setCry(jsonObject.getJSONObject("cries").optString("latest", null));
            PokemonDataHelper.setPokemonTypes(jsonObject, pokemonData);
            pokemonData.setWeight(jsonObject.optInt("weight", -1));

            return pokemonData;
        } catch (HttpClientErrorException.NotFound e) {
            e.fillInStackTrace();
            throw new PokemonNotFoundException();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw new PokeAPINotAvailableException();
        }
    }
}
