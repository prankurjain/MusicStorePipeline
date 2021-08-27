import { TestBed } from '@angular/core/testing';

import { SongDTOService } from './song-dto.service';

describe('SongDTOService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SongDTOService = TestBed.get(SongDTOService);
    expect(service).toBeTruthy();
  });
});
