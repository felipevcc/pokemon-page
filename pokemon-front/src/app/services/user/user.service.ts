import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserEndpoints } from '@enums/user-endpoints.enum';
import { PasswordChangeReq } from '@models/user/pwd-change-req.interface';
import { UserUpdateReq } from '@models/user/user-update-req.interface';
import { environment } from '@env/environment';
import { checkToken } from '@interceptors/token.interceptor';

/**
 * UserService
 * Service related to user management
 */
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  /**
   * Get user data
   * @returns Observable<any>
   */
  getUserData(): Observable<any> {
    return this.http.get(`${environment.apiUrl}${UserEndpoints.UserData}`, { context: checkToken() });
  }

  /**
   * Update user data
   * @param userData UserUpdateReq
   * @returns Observable<any>
   */
  updateUser(userData: UserUpdateReq): Observable<any> {
    return this.http.put(`${environment.apiUrl}${UserEndpoints.UserUpdate}`, userData, { context: checkToken() });
  }

  /**
   * Change user password
   * @param passwordData PasswordChangeReq
   * @returns Observable<any>
   */
  changePassword(passwordData: PasswordChangeReq): Observable<any> {
    return this.http.put(`${environment.apiUrl}${UserEndpoints.UserPasswordChange}`, passwordData, { context: checkToken() });
  }
}
