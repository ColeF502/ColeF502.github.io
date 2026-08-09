import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { Trip } from '../models/trip';
import { User } from '../models/user';
import { AuthResponse } from '../models/auth-response';



@Injectable({
  providedIn: 'root'
})
export class TripDataService {
  constructor(
    private http: HttpClient
  ) { }

  baseUrl = environment.apiUrl;
  url = this.baseUrl + '/trips';

  getTrips(): Observable<Trip[]> {
    return this.http.get<Trip[]>(this.url);
  }

  addTrip(formData: Trip): Observable<Trip> {
    return this.http.post<Trip>(this.url, formData);
  }

  getTrip(tripCode: string): Observable<Trip[]> {
    return this.http.get<Trip[]>(this.url + '/' + tripCode);
  }

  updateTrip(formData: Trip, originalTripCode: string): Observable<Trip> {
    return this.http.put<Trip>(this.url + '/' + originalTripCode, formData);
  }

  deleteTrip(tripCode: string): Observable<Trip> {
    return this.http.delete<Trip>(this.url + '/' + tripCode);
  }

  // Call to /login endpoint, returns JWT
  login(user: User, passwd: string): Observable<AuthResponse> {
    return this.handleAuthAPICall('login', user, passwd);
  }

  // Call to /register endpoint, creates user and returns JWT
  register(user: User, passwd: string): Observable<AuthResponse> {
    return this.handleAuthAPICall('register', user, passwd);
  }


  // Helper method for processing both the login and register methods
  handleAuthAPICall(endpoint: string, user: User, passwd: string): Observable<AuthResponse> {
    let formData = {
      name: user.name,
      email: user.email,
      password: passwd
      
    };

    return this.http.post<AuthResponse>(this.baseUrl + '/' + endpoint, formData);
  }
}
