import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SongDTOService {

  songId:number;
  songName:String;

  constructor() { }
}
