import { Pokemon } from "./pokemon.interface";

// Pokemon page interface
export interface PokemonPage {
	page: number;
	pageSize: number;
	totalPokemons: number;
	totalPages: number;
	pokemons: Pokemon[];
}
