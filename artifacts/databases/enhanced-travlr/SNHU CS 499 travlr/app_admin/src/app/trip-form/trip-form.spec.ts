import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute, Router } from '@angular/router';
import { of } from 'rxjs';
import { TripForm } from './trip-form';
import { TripDataService } from '../services/trip-data';
import { TRIP_CODE_STORAGE_KEY } from '../config/app.constants';

/* 
Tests the main functions of the shared trip form to make sure that
adding, editing, and basic form validation are working correctly
*/

describe('TripForm', () => {

  // Stores the component being tested and its test environment
  let component: TripForm;
  let fixture: ComponentFixture<TripForm>;

  // Creates a fake Router so the tests aren't actually changing pages
  const routerMock = {
    navigate: vi.fn()
  };

  // Starts the form in add mode by default
  const activatedRouteMock = {
    snapshot: {
      data: {
        mode: 'add'
      }
    }
  };
  // Creates fake versions of the service methods that are used by the form
  // Prevents the tests from making real API calls
  const tripDataServiceMock = {
    getTrip: vi.fn(),
    addTrip: vi.fn(),
    updateTrip: vi.fn()

  };

  // Provides valid trip information that can be reused by the tests
  const testTrip = {
    _id: '12345',
    code: 'TEST260718',
    name: 'Test Trip',
    length: '4 nights / 5 days',
    start: '2026-08-01',
    resort: 'Test Resort',
    perPerson: '1000.00',
    image: 'test.jpg',
    description: 'An imaginary, hypothetical and possibly theoretical trip for testing'
  };


  beforeEach(async () => {

    // Clears any previous calls made to the fake service methods
    vi.clearAllMocks();

    // Removes any stored trip code left over from another test
    localStorage.removeItem(TRIP_CODE_STORAGE_KEY);

    // Resets the route back to add mode before each of the tests
    activatedRouteMock.snapshot.data = {

      mode: 'add'
    };

    // Sets the fake service methods to return successful results
    tripDataServiceMock.getTrip.mockReturnValue(of([testTrip]));
    tripDataServiceMock.addTrip.mockReturnValue(of(testTrip));
    tripDataServiceMock.updateTrip.mockReturnValue(of(testTrip));

    // Creates the testing environment for the TripForm component
    // The real route, router, and service are replaced with the fake versions up above
    await TestBed.configureTestingModule({
      imports: [TripForm],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: activatedRouteMock
        },

        {
          provide: Router,
          useValue: routerMock
        },

        {
          provide: TripDataService,
          useValue: tripDataServiceMock
        }

      ]
    }).compileComponents();

  });

  // Ensures the shared trip form can be created successfully
  it('should create the trip form', () => {

    // Creates an instance of the component for this test
    fixture = TestBed.createComponent(TripForm);
    component = fixture.componentInstance;

    // Runs the component initialization code
    fixture.detectChanges();

    // Checks that the component was created
    expect(component).toBeTruthy();

  });

  // Makes sure an invalid form isn't submitted
  it('should prevent an invalid trip from being added', () => {

    // Creates the form and leaves all of its fields empty
    fixture = TestBed.createComponent(TripForm);
    component = fixture.componentInstance;

    fixture.detectChanges();

    // Tries to submit the invalid form
    component.onSubmit();

    // Makes sure the add method wasn't called
    expect(tripDataServiceMock.addTrip).not.toHaveBeenCalled();
  });

  // Ensures a valid trip can be added
  it('should add a valid trip', () => {

    // Creates the form in its normal add mode
    fixture = TestBed.createComponent(TripForm);
    component = fixture.componentInstance;
    fixture.detectChanges();

    // Fills the form with valid test information
    component.tripForm.setValue(testTrip);

    // Submits the completed form
    component.onSubmit();

    // Checks that the form information was sent to the add method
    expect(tripDataServiceMock.addTrip)
      .toHaveBeenCalledWith(component.tripForm.value);

  });

  // Makes sure an existing trip uses the update method
  it('should update an existing trip', () => {

    // Stores a trip code the same way the real application does before editing
    localStorage.setItem(TRIP_CODE_STORAGE_KEY, testTrip.code);

    // Changes the route to edit mode for this test
    activatedRouteMock.snapshot.data = {
      mode: 'edit'
    };

    // Creates the form, which'll now load the existing test trip
    fixture = TestBed.createComponent(TripForm);
    component = fixture.componentInstance;
    fixture.detectChanges();

    // Submits the loaded trip information
    component.onSubmit();

    // Checks that editing uses the update method and the original trip code
    expect(tripDataServiceMock.updateTrip)
      .toHaveBeenCalledWith(component.tripForm.value, testTrip.code);
  });


});