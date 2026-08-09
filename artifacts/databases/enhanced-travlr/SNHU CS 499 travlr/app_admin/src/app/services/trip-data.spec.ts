import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { TripDataService } from './trip-data';

describe('TripDataService', () => {
  let service: TripDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient()
      ]
    });

    service = TestBed.inject(TripDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

