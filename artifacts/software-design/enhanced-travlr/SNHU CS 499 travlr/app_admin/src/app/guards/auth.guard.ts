import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication';

// Prevents users who aren't logged in from being able to open protected admin pages
export const authGuard: CanActivateFn = () => {
  const authenticationService = inject(AuthenticationService);
  const router = inject(Router);

  if (authenticationService.isLoggedIn()) {
    return true;
  }


  // Sends the user to the login page if there isn't a valid login
  return router.createUrlTree(['/login']);
};