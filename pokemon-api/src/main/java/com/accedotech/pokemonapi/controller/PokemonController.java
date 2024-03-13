package com.accedotech.pokemonapi.controller;

import com.accedotech.pokemonapi.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pokemon")
@CrossOrigin
public class PokemonController {

    @Autowired
    PokemonService pokemonService;
}
