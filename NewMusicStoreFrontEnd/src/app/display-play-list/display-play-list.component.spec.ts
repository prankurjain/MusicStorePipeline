import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayPlayListComponent } from './display-play-list.component';

describe('DisplayPlayListComponent', () => {
  let component: DisplayPlayListComponent;
  let fixture: ComponentFixture<DisplayPlayListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayPlayListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayPlayListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
