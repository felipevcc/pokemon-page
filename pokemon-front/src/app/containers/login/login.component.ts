import { HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Messages } from '@enums/messages.enum';
import { Paths } from '@enums/paths.enum';
import { UserLoginReq } from '@models/user/user-login-req.interface';
import { AuthService } from '@services/auth/auth.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  form!: FormGroup;

  constructor(
    private authService: AuthService,
    private messageService: MessageService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit() {
    this.form = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    this.messageService.clear();

    const formData = this.form.value;

    // Create a UserLoginReq object with the form values
    const credentials: UserLoginReq = {
      email: formData.email,
      password: formData.password
    };

    this.authService.login(credentials).subscribe({
      next: (response: HttpResponse<any>) => {
        const jwtToken = response.headers.get('Authorization');
        if (!jwtToken) return;
        this.authService.setAuthToken(jwtToken);
        this.router.navigate([Paths.Pokemons]);
      },
      error: (error) => {
        this.messageService.add({severity: 'error', detail: Messages.INCORRECT_CREDETIALS});
      }
    });
  }

  onSignup() {
    this.router.navigate([Paths.Signup]);
  }

  onBack() {
    this.router.navigate([Paths.Pokemons]);
  }

}
