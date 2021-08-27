import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PlayListDTOService {

  playlistName:String;
  songs:any=[];
  constructor() { }
}
