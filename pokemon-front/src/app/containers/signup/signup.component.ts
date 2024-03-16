import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Paths } from '@enums/paths.enum';
import { UserRegisterReq } from '@models/user/user-register-req.interface';
import { AuthService } from '@services/auth/auth.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {

  form!: FormGroup;

  constructor(
    private authService: AuthService,
    private messageService: MessageService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      email: ['', Validators.required]
    });
  }

  onSubmit() {
    this.messageService.clear();

    const formData = this.form.value;

    if (formData.password !== formData.confirmPassword) {
      this.messageService.add({severity: 'error', detail: 'Passwords do not match'});
      return;
    }

    const userData: UserRegisterReq = {
      firstName: formData.firstName,
      lastName: formData.lastName,
      password: formData.password,
      phoneNumber: formData.phoneNumber,
      email: formData.email
    };

    this.authService.signup(formData).subscribe({
      next: () => {
        this.router.navigate([Paths.Login]);
      },
      error: (error) => {
        this.messageService.add({severity: 'error', detail: error.error.message});
      }
    });
  }

  onSignin() {
    this.router.navigate([Paths.Login]);
  }

  onBack() {
    this.router.navigate([Paths.Pokemons]);
  }

}
