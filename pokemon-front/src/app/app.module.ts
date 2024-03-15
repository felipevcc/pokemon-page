import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './containers/login/login.component';
import { SignupComponent } from './containers/signup/signup.component';
import { PokemonsComponent } from './containers/pokemons/pokemons.component';
import { PokemonDetailsComponent } from './containers/pokemon-details/pokemon-details.component';
import { UpdateUserComponent } from './containers/update-user/update-user.component';
import { ChangePasswordComponent } from './containers/change-password/change-password.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    PokemonsComponent,
    PokemonDetailsComponent,
    UpdateUserComponent,
    ChangePasswordComponent,
    NavbarComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
