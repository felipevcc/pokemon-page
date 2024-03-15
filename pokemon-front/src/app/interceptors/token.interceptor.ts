import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpContextToken,
  HttpContext
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '@services/auth/auth.service';

/**
 * Http request context
 * Create a new HTTP request context with the value true associated with the CHECK_TOKEN token.
 */
const CHECK_TOKEN = new HttpContextToken<boolean>(() => false);
export function checkToken(): HttpContext {
  return new HttpContext().set(CHECK_TOKEN, true);
}

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(
    private authService: AuthService
  ) {}

  /**
   * Intercept the requests
   * @param request HttpRequest<unknown>
   * @param next HttpHandler
   * @returns Observable<HttpEvent<unknown>>
   */
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    // Add the auth token to the request header based on the state of the CHECK_TOKEN in the request context
    if (request.context.get(CHECK_TOKEN)) {
      return this.addToken(request, next);
    }
    // If there is no context the request continues
    return next.handle(request);
  }

  /**
   * Add the token to the authorization header in the requests if it exists
   * @param request HttpRequest<unknown>
   * @param next HttpHandler
   * @returns Observable<HttpEvent<unknown>>
   */
  private addToken(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    // Get the authentication token
    const authToken = this.authService.getAuthToken();

    // If the token exists, add it to the authorization header
    if (authToken) {
      const authRequest = request.clone({
        headers: request.headers.set('Authorization', `Bearer ${authToken}`)
      });
      // Continue the request with the authorization header
      return next.handle(authRequest);
    }

    // If the token does not exist, continue the request without the authorization header
    return next.handle(request);
  }
}
