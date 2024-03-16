import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Messages } from '@enums/messages.enum';
import { Paths } from '@enums/paths.enum';
import { PokemonPage } from '@models/pokemon/pokemon-page.interface';
import { PokemonService } from '@services/pokemon/pokemon.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-pokemons',
  templateUrl: './pokemons.component.html',
  styleUrls: ['./pokemons.component.scss']
})
export class PokemonsComponent {

  pokemonsPath: string = Paths.Pokemons;

  pokemonsPage: PokemonPage = {
    page: 1,
    pageSize: 15,
    totalPokemons: 0,
    totalPages: 0,
    pokemons: []
  };

  isLoading = true;

  constructor(
    private router: Router,
    private pokemonService: PokemonService,
    private messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.isLoading = true;
    this.getPokemons();
  }

  getPokemons(): void {
    this.messageService.clear();

    this.pokemonService.getPokemons(this.pokemonsPage.page, this.pokemonsPage.pageSize)
      .subscribe({
        next: (data: PokemonPage) => {
          this.pokemonsPage = data;
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

  onPageChange(event: any): void {
    this.pokemonsPage.page = event.page + 1;
    this.pokemonsPage.pageSize = event.rows;
    this.getPokemons();
  }

  onPokemonClick(id: number): void {
    this.router.navigate([`/${Paths.PokemonDetails}/${id}`]);
  }
}
