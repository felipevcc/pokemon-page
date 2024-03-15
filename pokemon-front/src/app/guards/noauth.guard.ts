import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { Paths } from '@enums/paths.enum';
import { AuthService } from '@services/auth/auth.service';

/**
 * NoauthGuard
 * Prevents the user from accessing the login and signup pages if they are already authenticated
 */
@Injectable({
  providedIn: 'root'
})
export class NoauthGuard implements CanActivate {

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.authService.isAuthenticated()) {
      this.router.navigate([Paths.Pokemons]);
      return false;
    }
    return true;
  }
  
}
