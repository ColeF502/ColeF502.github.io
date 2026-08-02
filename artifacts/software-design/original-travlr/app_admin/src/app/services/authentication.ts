import { Inject, Injectable } from '@angular/core';
import { BROWSER_STORAGE } from '../storage';
import { User } from '../models/user';
import { AuthResponse } from '../models/auth-response';
import { TripDataService } from './trip-data';

// Setup for authentication service
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(
    @Inject(BROWSER_STORAGE) private storage: Storage,
    private tripDataService: TripDataService
  ) { }

  // Variable for handling Authentication Responses
  authResp: AuthResponse = new AuthResponse();


  // Gets token from storage provider
  public getToken(): string {
    let out: any;
    out = this.storage.getItem('travlr-token');

    if(!out)
    {
      return '';
    }

    return out;
  }


  // Save token to Storage provider
  public saveToken(token: string): void {
    this.storage.setItem('travlr-token', token);
  }

  // Logout of application and removes the JWT from Storage
  public logout(): void {
    this.storage.removeItem('travlr-token');
  }

  // Boolean to determine if we're logged in and the token is still valid
  public isLoggedIn(): boolean {
    const token: string = this.getToken();

    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.exp > (Date.now() / 1000);
    } else {
      return false;


    }
  }

  // Retrieves the current user
  public getCurrentUser(): User {
    const token: string = this.getToken();
    const { email, name } = JSON.parse(atob(token.split('.')[1]));

    return { email, name } as User;
  }


  // Login method that leverages the login method from tripDataService
  public login(user: User, passwd: string) : void {
    this.tripDataService.login(user, passwd)
    .subscribe({
      next: (value: any) => {
        if(value)
        {
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

  // Register method that leverages the register method from tripDataService
  public register(user: User, passwd: string) : void {
    this.tripDataService.register(user, passwd)
    .subscribe({
      next: (value: any) => {
        if(value)
        {
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
