package com.accedotech.pokemonapi.service.imp;

import com.accedotech.pokemonapi.dto.pokeapi.PokemonApiResponse;
import com.accedotech.pokemonapi.dto.pokemon.PokemonDataDTO;
import com.accedotech.pokemonapi.dto.pokemon.PokemonsPageDTO;
import com.accedotech.pokemonapi.exceptions.PokeAPINotAvailableException;
import com.accedotech.pokemonapi.exceptions.PokemonNotFoundException;
import com.accedotech.pokemonapi.mapper.PokemonMapper;
import com.accedotech.pokemonapi.service.PokemonService;
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
            pokemonData.setName(jsonObject.getString("name"));

            // Pokémon images
            JSONObject pokemonImages = jsonObject.getJSONObject("sprites");
            pokemonData.setSmallImage(pokemonImages.getString("front_default"));
            pokemonData.setLargeImage(pokemonImages.getJSONObject("other").getJSONObject("dream_world").getString("front_default"));

            pokemonData.setAbilities(getPokemonAbilities(jsonObject));
            pokemonData.setBaseExperience(jsonObject.getInt("base_experience"));
            pokemonData.setCry(jsonObject.getJSONObject("cries").getString("latest"));
            pokemonData.setTypes(getPokemonTypes(jsonObject));
            pokemonData.setWeight(jsonObject.getInt("weight"));

            return pokemonData;
        } catch (HttpClientErrorException.NotFound e) {
            e.fillInStackTrace();
            throw new PokemonNotFoundException();
        } catch (Exception e) {
            e.fillInStackTrace();
            throw new PokeAPINotAvailableException();
        }
    }

    /**
     * Method to obtain a pokemon's abilities.
     */
    private List<String> getPokemonAbilities(JSONObject jsonObject) {
        // Get the ability array
        JSONArray abilities = jsonObject.getJSONArray("abilities");

        // List to store ability names
        List<String> abilityNames = new ArrayList<>();

        // Get ability names
        for (int i = 0; i < abilities.length(); i++) {
            JSONObject ability = abilities.getJSONObject(i).getJSONObject("ability");
            abilityNames.add(ability.getString("name"));
        }
        return abilityNames;
    }

    /**
     * Method to obtain a pokemon's types.
     */
    private List<String> getPokemonTypes(JSONObject jsonObject) {
        // Get the type array
        JSONArray types = jsonObject.getJSONArray("types");

        // List to store type names
        List<String> typeNames = new ArrayList<>();

        // Get type names
        for (int i = 0; i < types.length(); i++) {
            JSONObject ability = types.getJSONObject(i).getJSONObject("type");
            typeNames.add(ability.getString("name"));
        }
        return typeNames;
    }
}
