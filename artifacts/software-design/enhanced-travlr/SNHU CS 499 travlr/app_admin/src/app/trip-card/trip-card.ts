import { Component, OnInit, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { Trip } from '../models/trip';
import { AuthenticationService } from '../services/authentication';
import { TripDataService } from '../services/trip-data';
import { TRIP_CODE_STORAGE_KEY } from '../config/app.constants';

@Component({
  selector: 'app-trip-card',
  imports: [CommonModule],
  templateUrl: './trip-card.html',
  styleUrl: './trip-card.css'
})

export class TripCard implements OnInit {
  @Input('trip') trip: any;
  isDeleting = false;
  successMessage = '';
  errorMessage = '';

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private tripDataService: TripDataService
  ) { }



  ngOnInit(): void {
  }

  public editTrip(trip: Trip): void {
    localStorage.removeItem(TRIP_CODE_STORAGE_KEY);
    localStorage.setItem(TRIP_CODE_STORAGE_KEY, trip.code);

    this.router.navigate(['edit-trip']);
  }

  public deleteTrip(trip: Trip): void {
    const confirmed = confirm('Are you sure you want to delete this trip?');

    if (confirmed) {
      this.isDeleting = true;
      this.successMessage = '';
      this.errorMessage = '';

      this.tripDataService.deleteTrip(trip.code)
        .subscribe({
          next: () => {
            this.isDeleting = false;
            this.successMessage = 'Trip deleted successfully';

            // Gives administrator time to see the success message before refreshing the trip list
            setTimeout(() => {
              window.location.reload();
            }, 1000);
          },
          error: () => {
            this.isDeleting = false;
            this.errorMessage = 'There was a problem deleting the trip';
          }
        });
    }
  }

  public isLoggedIn(): boolean {
    return this.authenticationService.isLoggedIn();
  }
  
}