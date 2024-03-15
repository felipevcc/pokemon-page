import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { Paths } from '@enums/paths.enum';
import { AuthGuard } from '@guards/auth.guard';
import { NoauthGuard } from '@guards/noauth.guard';

import { PokemonsComponent } from '@containers/pokemons/pokemons.component';
import { LoginComponent } from '@containers/login/login.component';
import { SignupComponent } from '@containers/signup/signup.component';
import { PokemonDetailsComponent } from '@containers/pokemon-details/pokemon-details.component';
import { UpdateUserComponent } from '@containers/update-user/update-user.component';
import { ChangePasswordComponent } from '@containers/change-password/change-password.component';

const routes: Routes = [
  {
    path: Paths.Pokemons,
    title: "Pokemons",
    component: PokemonsComponent,
  },
  {
    path: Paths.Login,
    title: "Login",
    component: LoginComponent,
    canActivate: [NoauthGuard]
  },
  {
    path: Paths.Signup,
    title: "Signup",
    component: SignupComponent,
    canActivate: [NoauthGuard]
  },
  {
    path: `${Paths.PokemonDetails}/:id`,
    title: "Pokemon details",
    component: PokemonDetailsComponent,
    canActivate: [AuthGuard]
  },
  {
    path: Paths.UpdateUser,
    title: "Update user",
    component: UpdateUserComponent,
    canActivate: [AuthGuard]
  },
  {
    path: Paths.ChangePassword,
    title: "Change password",
    component: ChangePasswordComponent,
    canActivate: [AuthGuard]
  },
  {
    path: Paths.Undefined,
    title: "Page not found",
    redirectTo: Paths.Pokemons
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
