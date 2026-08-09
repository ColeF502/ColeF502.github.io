import { TestBed } from '@angular/core/testing';
import { AuthenticationService } from './authentication';
import { TripDataService } from './trip-data';
import { BROWSER_STORAGE } from '../storage';

/*
Tests the main authentication state changes to make sure that
logging in and logging out correctly update the app

*/

describe('AuthenticationService', () => {

  // Stores the authentication service being tested
  let service: AuthenticationService;

  // Creates fake browser storage so the tests don't use the real local storage
  const storageMock = {
    getItem: vi.fn().mockReturnValue(null),

    setItem: vi.fn(),
    removeItem: vi.fn()
  };

  // Creates a fake trip data service because the authentication service depends on it
  const tripDataServiceMock = {
    login: vi.fn()
  };


  beforeEach(() => {

    // Clears any existing calls left over from a previous test
    vi.clearAllMocks();

    // Makes sure the fake storage starts without a saved token
    storageMock.getItem.mockReturnValue(null);

    // Creates the testing environment with the fake dependencies
    TestBed.configureTestingModule({
      providers: [
        {
          provide: BROWSER_STORAGE,
          useValue: storageMock
        },

        {
          provide: TripDataService,
          useValue: tripDataServiceMock
        }
      ]
    });

    // Gets the authentication service from the testing environment
    service = TestBed.inject(AuthenticationService);
  });

  // Makes sure that saving a token changes the app to a logged-in state
  it('should show the user as logged in after saving a token', () => {

    service.saveToken('test-token');
    expect(service.isLoggedIn()).toBe(true);
    expect(storageMock.setItem).toHaveBeenCalled();

  });

  // Makes sure logging out removes the token and changes the login state
  it('should log the user out', () => {

    // Starts the test in a logged-in state
    service.saveToken('test-token');
    // Logs the user back out
    service.logout();

    expect(service.isLoggedIn()).toBe(false);
    expect(storageMock.removeItem).toHaveBeenCalled();

  });
});
