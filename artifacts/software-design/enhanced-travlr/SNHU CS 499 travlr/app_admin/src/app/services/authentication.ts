import { Inject, Injectable, signal } from '@angular/core';
import { BROWSER_STORAGE } from '../storage';
import { User } from '../models/user';
import { AuthResponse } from '../models/auth-response';
import { TripDataService } from './trip-data';
import { TOKEN_STORAGE_KEY } from '../config/app.constants';
import { Observable, tap } from 'rxjs';

// Setup for authentication service
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(
    @Inject(BROWSER_STORAGE) private storage: Storage,
    private tripDataService: TripDataService
  ) {
    this.loggedInState.set(this.hasValidToken());
  }

  // Variable for handling Authentication Responses
  authResp: AuthResponse = new AuthResponse();
  // Keeps track of the current login state so the interface updates right away
  private loggedInState = signal(false);


  // Gets token from storage provider
  public getToken(): string {
    let out: any;
    out = this.storage.getItem(TOKEN_STORAGE_KEY);

    if (!out) {
      return '';
    }

    return out;
  }


  // Save token to Storage provider
  public saveToken(token: string): void {
    this.storage.setItem(TOKEN_STORAGE_KEY, token);
    this.loggedInState.set(true);
  }

  // Logout of application and removes the JWT from Storage
  public logout(): void {
    this.storage.removeItem(TOKEN_STORAGE_KEY);
    this.loggedInState.set(false);
  }

  // Checks whether the stored JWT is still valid
  private hasValidToken(): boolean {
    const token: string = this.getToken();

    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1]));

      return payload.exp > (Date.now() / 1000);
    }

    return false;
  }

  // Returns the current login state
  public isLoggedIn(): boolean {
    return this.loggedInState();
  }

  // Retrieves the current user
  public getCurrentUser(): User {
    const token: string = this.getToken();
    const { email, name } = JSON.parse(atob(token.split('.')[1]));

    return { email, name } as User;
  }


  // Logs the user in and saves the returned JWT
  public login(user: User, passwd: string): Observable<AuthResponse> {
    return this.tripDataService.login(user, passwd)
      .pipe(
        tap((value: AuthResponse) => {
          this.authResp = value;
          this.saveToken(this.authResp.token);

        })
      );
  }

  // Register method that leverages the register method from tripDataService
  public register(user: User, passwd: string): void {
    this.tripDataService.register(user, passwd)
      .subscribe({
        next: (value: any) => {
          if (value) {

            console.log(value);
            this.authResp = value;
            this.saveToken(this.authResp.token);
          }
        },
        error: (error: any) => {
          console.log('Error: ' + error);
        }
      });
  }

  
}
