import { TestBed } from '@angular/core/testing';

import { JwtInterceptor } from './jwt-interceptor';
import { AuthenticationService } from '../services/authentication';

describe('JwtInterceptor', () => {
  let interceptor: JwtInterceptor;

  beforeEach(() => {
    const authenticationServiceStub = {
      isLoggedIn: () => false,
      getToken: () => ''
    };

    TestBed.configureTestingModule({
      providers: [
        JwtInterceptor,
        { provide: AuthenticationService, useValue: authenticationServiceStub }
      ]
    });

    interceptor = TestBed.inject(JwtInterceptor);
  });

  it('should be created', () => {
    expect(interceptor).toBeTruthy();
  });
});
