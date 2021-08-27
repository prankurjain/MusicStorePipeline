import { FormControl, FormGroup } from '@angular/forms';

import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { PlayListDTOService } from '../play-list-dto.service';
import { Data } from 'webdriver-js-extender/built/spec/mockdriver';
import { SongDTOService } from '../song-dto.service';
import { Song } from '../song';
import { OperationsService } from '../operations.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-play-list',
  templateUrl: './add-play-list.component.html',
  styleUrls: ['./add-play-list.component.scss']
})
export class AddPlayListComponent implements OnInit {

  songs:SongDTOService[];
  create: FormGroup;
  selected:any=[];
  playlist: PlayListDTOService;
  messge:any;

  constructor(private service: OperationsService) { }

  ngOnInit() {

    this.service.getList().subscribe(data=>this.songs=data);

    // this.create=new  FormGroup(
    //   {
    //     playlistName:new FormControl(''),
        
    //   }
    // )
    
    this.playlist=new PlayListDTOService();

  }
  add(event){
    let index=this.selected.indexOf(event.target.value);
    if(index==-1)
      {
        this.selected.push(event.target.value);
      }
      else
        {
          this.selected.splice(index,1);
        }
        console.log(this.selected);
        
  }
  addPlayList()
  {
    console.log((<HTMLInputElement>document.getElementById("playlistname")).value);
    console.log(this.selected);
    this.playlist.playlistName=(<HTMLInputElement>document.getElementById("playlistname")).value;
// this.playlist.playListName = (<HTMLInputElement>document.getElementById("playList")).value
    this.playlist.songs=this.selected;
    console.log(this.playlist);
     this.service.createPlayList(this.playlist).subscribe(data=>this.messge=data);
  }

}
