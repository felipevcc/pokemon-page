import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Messages } from '@enums/messages.enum';
import { Paths } from '@enums/paths.enum';
import { UserUpdateReq } from '@models/user/user-update-req.interface';
import { User } from '@models/user/user.interface';
import { AuthService } from '@services/auth/auth.service';
import { UserService } from '@services/user/user.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.scss']
})
export class UpdateUserComponent {

  form!: FormGroup;
  isLoading: boolean = true;
  user!: User;
  
  constructor(
    private userService: UserService,
    private authService: AuthService,
    private messageService: MessageService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit() {
    this.form = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      email: ['', Validators.required]
    });
    this.isLoading = true;
    this.getUserData();
  }

  getUserData(): void {
    this.userService.getUserData().subscribe({
      next: (data: User) => {
        this.user = data;
        this.form.patchValue({
          firstName: this.user.firstName,
          lastName: this.user.lastName,
          phoneNumber: this.user.phoneNumber,
          email: this.user.email
        });
        this.isLoading = false;
      },
      error: (error) => {
        this.isLoading = false;
        console.error(error);
        this.messageService.add({severity: 'error', detail: Messages.ERROR_GET});
      }
    });
  }

  onSubmit() {
    this.messageService.clear();

    const formData = this.form.value;

    const userData: UserUpdateReq = {
      firstName: formData.firstName,
      lastName: formData.lastName,
      phoneNumber: formData.phoneNumber,
      email: formData.email
    };

    this.userService.updateUser(userData).subscribe({
      next: () => {
        if (this.user.email !== formData.email) {
          this.authService.logout();
        }
        this.router.navigate([Paths.Login]);
      },
      error: (error) => {
        let msg = Messages.ERROR_UPDATE;
        if (error.error) {
          msg = error.error;
        }
        this.messageService.add({severity: 'error', detail: msg});
      }
    });
  }

  onPokemonsClick(): void {
    this.router.navigate([Paths.Pokemons]);
  }

}
