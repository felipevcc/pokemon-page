import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';

import { CookieService } from 'ngx-cookie-service';
import { MessageService } from 'primeng/api';

import { AppRoutingModule } from './app-routing.module';
import { PaginatorModule } from 'primeng/paginator';
import { MessagesModule } from 'primeng/messages';
import { MenubarModule } from 'primeng/menubar';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';

import { AppComponent } from './app.component';
import { LoginComponent } from '@containers/login/login.component';
import { SignupComponent } from '@containers/signup/signup.component';
import { PokemonsComponent } from '@containers/pokemons/pokemons.component';
import { PokemonDetailsComponent } from '@containers/pokemon-details/pokemon-details.component';
import { UpdateUserComponent } from '@containers/update-user/update-user.component';
import { ChangePasswordComponent } from '@containers/change-password/change-password.component';
import { NavbarComponent } from '@components/navbar/navbar.component';
import { FooterComponent } from '@components/footer/footer.component';

import { TokenInterceptor } from '@interceptors/token.interceptor';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

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
    AppRoutingModule,
    HttpClientModule,
    PaginatorModule,
    MessagesModule,
    BrowserAnimationsModule,
    MenubarModule,
    ButtonModule,
    FormsModule,
    ReactiveFormsModule,
    InputTextModule
  ],
  providers: [
    CookieService,
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
    MessageService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
