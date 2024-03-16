package com.accedotech.pokemonapi.util;

import com.accedotech.pokemonapi.dto.pokemon.PokemonDataDTO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to get and set Pokémon data.
 * Set images, abilities and types.
 */
public class PokemonDataHelper {

    /**
     * Method to obtain and set pokemon's abilities.
     * Gets the small and large image.
     */
    public static void setPokemonImages(JSONObject jsonObject, PokemonDataDTO pokemonData) {
        JSONObject pokemonImages = jsonObject.optJSONObject("sprites");
        if (pokemonImages != null) {
            setLargeImage(pokemonImages.optJSONObject("other"), pokemonData);
            pokemonData.setSmallImage(pokemonImages.optString("front_default", null));
        }
    }

    /**
     * Method to set the large image.
     */
    private static void setLargeImage(JSONObject otherSprites, PokemonDataDTO pokemonData) {
        if (otherSprites != null) {
            JSONObject dreamWorld = otherSprites.optJSONObject("dream_world");
            JSONObject officialArtwork = otherSprites.optJSONObject("official-artwork");
            JSONObject home = otherSprites.optJSONObject("home");
            if (dreamWorld != null && pokemonData.getLargeImage() == null) {
                pokemonData.setLargeImage(dreamWorld.optString("front_default", null));
            }
            if (officialArtwork != null && pokemonData.getLargeImage() == null) {
                pokemonData.setLargeImage(officialArtwork.optString("front_default", null));
            }
            if (home != null && pokemonData.getLargeImage() == null) {
                pokemonData.setLargeImage(home.optString("front_default", null));
            }
        }
    }

    /**
     * Method to obtain and set pokemon's abilities.
     */
    public static void setPokemonAbilities(JSONObject jsonObject, PokemonDataDTO pokemonData) {
        // Get the ability array
        JSONArray abilities = jsonObject.getJSONArray("abilities");

        // List to store ability names
        List<String> abilityNames = new ArrayList<>();

        // Get ability names
        if (abilities != null) {
            for (int i = 0; i < abilities.length(); i++) {
                JSONObject ability = abilities.optJSONObject(i);
                if (ability != null) {
                    JSONObject abilityObject = ability.optJSONObject("ability");
                    if (abilityObject != null) {
                        abilityNames.add(abilityObject.optString("name", null));
                    }
                }
            }
        }

        // Set abilities
        pokemonData.setAbilities(abilityNames);
    }

    /**
     * Method to obtain and set pokemon's types.
     */
    public static void setPokemonTypes(JSONObject jsonObject, PokemonDataDTO pokemonData) {
        // Get the type array
        JSONArray types = jsonObject.getJSONArray("types");

        // List to store type names
        List<String> typeNames = new ArrayList<>();

        // Get type names
        if (types != null) {
            for (int i = 0; i < types.length(); i++) {
                JSONObject type = types.optJSONObject(i);
                if (type != null) {
                    JSONObject typeObject = type.optJSONObject("type");
                    if (typeObject != null) {
                        typeNames.add(typeObject.optString("name", null));
                    }
                }
            }
        }
        // Set types
        pokemonData.setTypes(typeNames);
    }
}
