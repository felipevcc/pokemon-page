import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserEndpoints } from '@enums/user-endpoints.enum';
import { environment } from '@env/environment';
import { UserLoginReq } from '@models/user/user-login-req.interface';
import { UserRegisterReq } from '@models/user/user-register-req.interface';
import { UserService } from '@services/user/user.service';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

/**
 * AuthService
 * Service related to user authentication management
 */
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient,
    private cookieService: CookieService,
    private userService: UserService
  ) { }

  /**
   * Login a user
   * @param credentials UserLoginReq
   * @returns Observable<any>
   * 
   */
  login(credentials: UserLoginReq): Observable<any> {
    return this.http.post(`${environment.apiUrl}${UserEndpoints.UserLogin}`, credentials);
  }

  /**
   * Register a user
   * @param userData UserRegisterReq
   * @returns Observable<any>
   * 
   */
  signup(userData: UserRegisterReq): Observable<any> {
    return this.http.post(`${environment.apiUrl}${UserEndpoints.UserSignup}`, userData);
  }

  /**
   * Logout a user
   * Delete the token cookie
   */
  logout(): void {
    this.cookieService.delete('jwtToken', '/');
  }

  /**
   * Check if the user is authenticated
   * @returns boolean
   */
  isAuthenticated(): boolean {
    // Validate if the jwtToken cookie exists
    if (!this.cookieService.check('jwtToken')) return false;

    // Validate if the token is valid
    this.userService.getUserData().subscribe({
      next: (data) => {
        return true;
      },
      error: (error) => {
        this.logout();
        return false;
      }
    });

    return false;
  }

  /**
   * Set the token cookie
   * @param token string
   */
  setAuthToken(token: string): void {
    this.cookieService.set('jwtToken', token, 15, '/');
  }

  /**
   * Get the token cookie
   * @returns string | null
   */
  getAuthToken(): string | null {
    return this.cookieService.get('jwtToken');
  }
}
