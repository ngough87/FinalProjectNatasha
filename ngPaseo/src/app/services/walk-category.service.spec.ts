import { TestBed } from '@angular/core/testing';

import { WalkCategoryService } from './walk-category.service';

describe('WalkCategoryService', () => {
  let service: WalkCategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WalkCategoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
