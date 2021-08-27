import { TestBed } from '@angular/core/testing';

import { PlayListDTOService } from './play-list-dto.service';

describe('PlayListDTOService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PlayListDTOService = TestBed.get(PlayListDTOService);
    expect(service).toBeTruthy();
  });
});
