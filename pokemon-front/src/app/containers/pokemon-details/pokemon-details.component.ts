import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Messages } from '@enums/messages.enum';
import { Paths } from '@enums/paths.enum';
import { PokemonData } from '@models/pokemon/pokemon-data.interface';
import { PokemonService } from '@services/pokemon/pokemon.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-pokemon-details',
  templateUrl: './pokemon-details.component.html',
  styleUrls: ['./pokemon-details.component.scss']
})
export class PokemonDetailsComponent {

  pokemonId: number = 0;
  pokemon!: PokemonData;

  isLoading: boolean = true;

  constructor(
    private pokemonService: PokemonService,
    private router: Router,
    private route: ActivatedRoute,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const pokemonIdStr = params.get('id');
      if (pokemonIdStr != null) {
        this.pokemonId = parseInt(pokemonIdStr, 10);

        this.isLoading = true;
        this.getPokemonData(this.pokemonId);
      }
    });
  }

  getPokemonData(pokemonId: number): void {
    this.messageService.clear();

    this.pokemonService.getPokemonById(pokemonId).subscribe({
      next: (data: PokemonData) => {
        this.pokemon = data;
        this.isLoading = false;
      },
      error: (error) => {
        this.isLoading = false;
        console.error(error);
        let msg: string = Messages.ERROR_GET;
        if (error.error) {
          msg = error.error;
        }
        this.messageService.add({severity: 'error', summary: 'Error', detail: msg});
      }
    });
  }

  onPokemonsClick(): void {
    this.router.navigate([Paths.Pokemons]);
  }

}
