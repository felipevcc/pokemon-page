import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PokemonEndpoints } from '@enums/pokemon-endpoints.enum';
import { environment } from '@env/environment';
import { checkToken } from '@interceptors/token.interceptor';

/**
 * PokemonService
 * Service related to Pokémon management
 */
@Injectable({
  providedIn: 'root'
})
export class PokemonService {

  constructor(private http: HttpClient) { }

  /**
   * Get paged pokemons
   * @param page number
   * @param pageSize number
   * @returns Observable<any>
   */
  getPokemons(page: number, pageSize: number): Observable<any> {
    return this.http.get(`${environment.apiUrl}${PokemonEndpoints.Pokemons}?page=${page}&pageSize=${pageSize}`, { context: checkToken() });
  }

  /**
   * Get pokemon data by id
   * @param id number
   * @returns Observable<any>
   */
  getPokemonById(id: number): Observable<any> {
    return this.http.get(`${environment.apiUrl}${PokemonEndpoints.Pokemons}/${id}`, { context: checkToken() });
  }
}
