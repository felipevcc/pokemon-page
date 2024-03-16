import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Paths } from '@enums/paths.enum';
import { User } from '@models/user/user.interface';
import { AuthService } from '@services/auth/auth.service';
import { UserService } from '@services/user/user.service';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  items!: MenuItem[];

  isAuthenticated: boolean = false;
  authenticatedUser: User | null = null;

  pokemonsPath: string = Paths.Pokemons;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.isAuthenticated = this.authService.isAuthenticated();
    if (this.authService.isAuthenticated()) {
      this.getUserData();
      this.items = [
        {
          label: this.authenticatedUser?.firstName || 'User',
          items: [
            {
              label: 'Update data',
              routerLink: '/update-user'
            },
            {
              label: 'Change password',
              routerLink: '/change-password'
            },
            {
              label: 'Logout',
              command: () => this.logout()
            }
          ]
        }
      ];
    }
  }

  getUserData(): void {
    this.userService.getUserData().subscribe({
      next: (data: User) => {
        this.authenticatedUser = data;
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate([Paths.Pokemons]);
  }

  onLoginClick(): void {
    this.router.navigate([Paths.Login]);
  }

  onSignupClick(): void {
    this.router.navigate([Paths.Signup]);
  }

}
