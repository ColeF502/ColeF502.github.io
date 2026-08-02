import { TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { authGuard } from './auth.guard';
import { AuthenticationService } from '../services/authentication';

/*
Tests the authentication guard to make sure that logged-in users
can access protected pages while logged-out users are sent to login
*/
describe('authGuard', () => {

  // Creates a fake authentication service for controlling the login state
  const authenticationServiceMock = {
    isLoggedIn: vi.fn()
  };

  // Creates a fake router so the tests don't change pages
  const routerMock = {
    createUrlTree: vi.fn().mockReturnValue('/login')
  };
  beforeEach(() => {

    // Clear any calls or values left over from a previous test
    vi.clearAllMocks();

    // Creates the testing environment with the fake services
    TestBed.configureTestingModule({
      providers: [
        {
          provide: AuthenticationService,
          useValue: authenticationServiceMock
        },

        {
          provide: Router,
          useValue: routerMock
        }

      ]
    });



  });

  // Makes sure a logged-in user is allowed to open a protected page
  it('should allow access when the user is logged in', () => {

    // Makes the fake authentication service return a logged-in state
    authenticationServiceMock.isLoggedIn.mockReturnValue(true);

    // Runs the guard inside Angular's testing environment
    const result = TestBed.runInInjectionContext(() =>
      authGuard({} as any, {} as any)
    );
    // Checks that the guard allowed access
    expect(result).toBe(true);
  });

  // Ensures a logged-out user is redirected to the login page
  it('should redirect to login when the user is logged out', () => {

    // Ensures the fake authentication service return a logged-out state
    authenticationServiceMock.isLoggedIn.mockReturnValue(false);

    // Runs the guard inside Angular's testing environment
    const result = TestBed.runInInjectionContext(() =>
      authGuard({} as any, {} as any)
    );

    // Checks that the login page was used for the redirect
    expect(routerMock.createUrlTree)
      .toHaveBeenCalledWith(['/login']);

    expect(result).toBe('/login');


  });
});