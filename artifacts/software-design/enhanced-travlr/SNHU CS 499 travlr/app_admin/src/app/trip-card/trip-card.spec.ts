import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { NEVER } from 'rxjs';
import { TripCard } from './trip-card';
import { TripDataService } from '../services/trip-data';
import { AuthenticationService } from '../services/authentication';

/*
Tests some of the main trip card functions to ensure that the
card loads correctly and the delete confirmation actually works
*/


describe('TripCard', () => {

  // Stores the component being tested and its test environment
  let component: TripCard;
  let fixture: ComponentFixture<TripCard>;

  // Creates a fake router so the test doesn't actually change pages
  const routerMock = {
    navigate: vi.fn()
  };

  // Creates a fake authentication service for the trip card
  const authenticationServiceMock = {
    isLoggedIn: vi.fn().mockReturnValue(true)
  };

  // Creates a fake delete method so no real API request's made
  const tripDataServiceMock = {
    deleteTrip: vi.fn()
  };

  // Gives a fake trip that can be displayed and deleted by the tests
  const testTrip: any = {
    _id: '12345',
    code: 'TEST260718',
    name: 'Test Trip',
    length: '4 nights / 5 days',
    start: '2026-08-01',
    resort: 'Test Resort',
    perPerson: '1000.00',
    image: 'test.jpg',
    description: 'A hypothetical trip for testing'


  };

  beforeEach(async () => {

    // Clears calls that may have been made during a previous test
    vi.clearAllMocks();

    // Prevents the fake delete request from continuing into the page refresh
    tripDataServiceMock.deleteTrip.mockReturnValue(NEVER);

    // Creates the testing environment with fake versions of the dependencies
    await TestBed.configureTestingModule({
      imports: [TripCard],
      providers: [
        {
          provide: Router,
          useValue: routerMock
        },

        {
          provide: AuthenticationService,
          useValue: authenticationServiceMock
        },

        {
          provide: TripDataService,
          useValue: tripDataServiceMock
        }

      ]

    }).compileComponents();

    // Creates the trip card and gives it a trip before the HTML is loaded
    fixture = TestBed.createComponent(TripCard);

    component = fixture.componentInstance;
    component.trip = testTrip;
    fixture.detectChanges();
  });

  // Prevents the Cancel/OK confirmation mocks from carrying over between the tests
  afterEach(() => {
    vi.restoreAllMocks();
  });

  // Ensures the trip card can be created with trip information
  it('should create the trip card', () => {
    expect(component).toBeTruthy();
  });

  // Ensures canceling the confirmation doesn't delete the trip
  it('should not delete a trip when confirmation is canceled', () => {

    // Simulates clicking Cancel on the confirmation box
    vi.spyOn(window, 'confirm').mockReturnValue(false);

    component.deleteTrip(testTrip);

    // Makes sure that the delete method wasn't called
    expect(tripDataServiceMock.deleteTrip).not.toHaveBeenCalled();
  });

  // Makes sure confirming the deletion calls the delete method
  it('should delete a trip when confirmation is accepted', () => {

    // Simulates clicking OK on the confirmation box
    vi.spyOn(window, 'confirm').mockReturnValue(true);

    component.deleteTrip(testTrip);

    // Checks that the correct trip code was sent to the delete method
    expect(tripDataServiceMock.deleteTrip)
      .toHaveBeenCalledWith(testTrip.code);
  });


});