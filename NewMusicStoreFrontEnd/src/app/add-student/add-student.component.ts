import { Song } from '../song';
import { OperationsService } from '../operations.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.scss']
})
export class AddStudentComponent implements OnInit {

  song: Song=new Song();
  addSong:FormGroup;
  message:any;

  constructor(private service:OperationsService) { }

  ngOnInit() {
   this.addSong=new  FormGroup(
      {
        songName:new FormControl(),
        songArtist:new FormControl(),
        songLocation:new FormControl(),
        songAlbum:new FormControl(),
        songDuration:new FormControl(),
        songTags:new FormControl()
      }
    )
    }
   public onSubmit()
    {
      console.log(this.addSong.value);
      this.song=this.addSong.value;
      this.service.addSong(this.song).subscribe(data=>this.message=data);
      this.addSong.reset();
    }
    public clear()
    {
       this.addSong.reset();
    }
}
