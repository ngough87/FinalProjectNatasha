import { TestBed } from '@angular/core/testing';

import { WalkTypeService } from './walk-type.service';

describe('WalkTypeService', () => {
  let service: WalkTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WalkTypeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
