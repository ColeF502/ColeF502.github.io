import { Routes } from '@angular/router';
import { TripForm } from './trip-form/trip-form';
import { TripListing } from './trip-listing/trip-listing';
import { Login } from './login/login';
import { authGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: 'add-trip', component: TripForm, canActivate: [authGuard], data: { mode: 'add' } },
  { path: 'edit-trip', component: TripForm, canActivate: [authGuard], data: { mode: 'edit' } },
  { path: 'login', component: Login },
  { path: '', component: TripListing, pathMatch: 'full' }
];