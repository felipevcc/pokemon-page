// Pokemon data interface
export interface PokemonData {
	pokemonId: number;
	name: string;
	smallImage: string;
	largeImage: string;
	abilities: string[];
	baseExperience: number;
	cry: string;
	types: string[];
	weight: number;
}
