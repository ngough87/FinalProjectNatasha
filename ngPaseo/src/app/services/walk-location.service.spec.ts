import { TestBed } from '@angular/core/testing';

import { WalkLocationService } from './walk-location.service';

describe('WalkLocationService', () => {
  let service: WalkLocationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WalkLocationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
