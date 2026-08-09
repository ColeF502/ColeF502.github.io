import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TRIP_CODE_STORAGE_KEY } from '../config/app.constants';
import { TripDataService } from '../services/trip-data';


@Component({
    selector: 'app-trip-form',
    imports: [CommonModule, ReactiveFormsModule],
    templateUrl: './trip-form.html',
    styleUrl: './trip-form.css'
})
export class TripForm implements OnInit {
    public tripForm!: FormGroup;
    submitted = false;
    isEditMode = false;
    isLoading = false;
    successMessage = '';
    errorMessage = '';
    originalTripCode = '';

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private tripDataService: TripDataService,
        private changeDetectorRef: ChangeDetectorRef
    ) { }

    ngOnInit(): void {
        // The same form's used for both adding and editing trips
        this.isEditMode = this.route.snapshot.data['mode'] === 'edit';

        this.tripForm = this.formBuilder.group({
            _id: [],
            code: ['', [
                Validators.required,
                Validators.minLength(10),
                Validators.maxLength(10)
            ]],
            name: ['', [
                Validators.required,
                Validators.maxLength(50)
            ]],
            length: ['', [
                Validators.required,
                Validators.maxLength(50)
            ]],
            start: ['', Validators.required],
            resort: ['', [
                Validators.required,
                Validators.maxLength(100)
            ]],
            perPerson: ['', [
                Validators.required,
                Validators.pattern(/^\d+(\.\d{1,2})?$/)
            ]],
            image: ['', [
                Validators.required,
                Validators.maxLength(100)
            ]],
            description: ['', [
                Validators.required,
                Validators.maxLength(2000)
            ]]
        });

        // Existing trip information only needs to be loaded when editing
        if (this.isEditMode) {
            this.loadTrip();
        }
    }

    private loadTrip(): void {
        const tripCode = localStorage.getItem(TRIP_CODE_STORAGE_KEY);
        if (!tripCode) {
            alert("Something went wrong and the trip couldn't be found.");
            this.router.navigate(['']);
            return;
        }
        this.originalTripCode = tripCode;
        this.isLoading = true;

        this.errorMessage = '';
        this.tripDataService.getTrip(tripCode)
            .subscribe({
                next: (value: any) => {
                    this.isLoading = false;

                    if (value && value.length > 0) {
                        this.tripForm.patchValue(value[0]);
                    } else {
                        this.errorMessage = 'The trip could not be found';
                    }
                },
                error: () => {
                    this.isLoading = false;
                    this.errorMessage = 'There was a problem loading the trip';
                }
            });
    }

    public onSubmit(): void {
        this.submitted = true;
        this.successMessage = '';
        this.errorMessage = '';

        if (!this.tripForm.valid) {
            this.errorMessage = 'Please correct the errors in the form before saving';
            return;
        }

        this.isLoading = true;

        // Use correct service method based on whether the trip is new or it's being edited
        const tripRequest = this.isEditMode
            ? this.tripDataService.updateTrip(this.tripForm.value, this.originalTripCode)
            : this.tripDataService.addTrip(this.tripForm.value);

        tripRequest.subscribe({
            next: () => {
                this.isLoading = false;
                this.successMessage = this.isEditMode
                    ? 'Trip updated successfully'
                    : 'Trip added successfully';

                // Refreshes the view after the API request finishes
                this.changeDetectorRef.detectChanges();
            },
            error: () => {
                this.isLoading = false;
                this.errorMessage = this.isEditMode
                    ? 'There was a problem updating the trip'
                    : 'There was a problem adding the trip';

                // Refreshes the view so the error message is displayed
                this.changeDetectorRef.detectChanges();
            }
        });
    }

    // Shortens access to the form controls in the HTML validation checks
    get f() {
        return this.tripForm.controls;
    }
}