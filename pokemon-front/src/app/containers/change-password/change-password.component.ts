import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Paths } from '@enums/paths.enum';
import { PasswordChangeReq } from '@models/user/pwd-change-req.interface';
import { UserService } from '@services/user/user.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent {

  form!: FormGroup;

  constructor (
    private userService: UserService,
    private messageService: MessageService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit() {
    this.form = this.fb.group({
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    });
  }

  onSubmit() {
    this.messageService.clear();

    const formData = this.form.value;

    if (formData.password !== formData.confirmPassword) {
      this.messageService.add({severity: 'error', detail: 'Passwords do not match'});
      return;
    }

    const passwordData: PasswordChangeReq = {
      newPassword: formData.password
    };

    this.userService.changePassword(passwordData).subscribe({
      next: () => {
        this.router.navigate([Paths.Pokemons]);
      },
      error: () => {
        this.messageService.add({severity: 'error', detail: 'Error updating password'});
      }
    });
  }

  onPokemonsClick(): void {
    this.router.navigate([Paths.Pokemons]);
  }

}
