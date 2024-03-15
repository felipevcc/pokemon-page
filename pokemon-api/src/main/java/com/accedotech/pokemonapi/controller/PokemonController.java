package com.accedotech.pokemonapi.controller;

import com.accedotech.pokemonapi.dto.pokemon.PokemonDataDTO;
import com.accedotech.pokemonapi.dto.pokemon.PokemonsPageDTO;
import com.accedotech.pokemonapi.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller of endpoints related to Pokémon.
 * Contains all call inputs and outputs for each endpoint.
 */
@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    PokemonService pokemonService;

    @GetMapping
    public ResponseEntity<PokemonsPageDTO> getAllPokemons(
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize
    ) {
        PokemonsPageDTO pokemonsPage = pokemonService.getAllPokemons(page, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(pokemonsPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonDataDTO> getPokemonById(@PathVariable Long id) {
        PokemonDataDTO pokemon = pokemonService.getPokemonById(id);
        return ResponseEntity.status(HttpStatus.OK).body(pokemon);
    }
}
