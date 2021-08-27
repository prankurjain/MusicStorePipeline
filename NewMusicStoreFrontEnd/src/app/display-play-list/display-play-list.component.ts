import { Component, OnInit } from '@angular/core';
import { OperationsService } from '../operations.service';

@Component({
  selector: 'app-display-play-list',
  templateUrl: './display-play-list.component.html',
  styleUrls: ['./display-play-list.component.scss']
})
export class DisplayPlayListComponent implements OnInit {

  name:String[];
  constructor(private service: OperationsService) { }

  ngOnInit() {
  }

  displayPlayList()
  {
    this.service.displayPlayList((<HTMLInputElement>document.getElementById("playlistname")).value).subscribe(data=>{
      this.name=data;
    console.log(this.name)});
  }
}
