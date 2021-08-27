import { PlayListDTOService } from './play-list-dto.service';
import { SongDTOService } from './song-dto.service';
import { HttpClient, JsonpClientBackend } from '@angular/common/http';
import { Song } from './song';
import { AddStudentComponent } from './add-student/add-student.component';
import { Injectable } from '@angular/core';
import { User } from 'src/entity/user';
import { LoginDTO } from 'src/dto/loginDto';
import { Subject } from 'rxjs';
import { LocalUrl } from 'src/dto/local_url';

@Injectable({
  providedIn: 'root'
})
export class OperationsService {
  
  songURL:String;
  songURL2:String;
  user:String;
  image:String;
  generateToken: String;
  loginStatusSubject=new Subject<String>();
  localurl:String;


  constructor(private http:HttpClient) { 
    this.localurl=LocalUrl.localurl;
    // this.songURL="http://localhost:8080/song";
    // this.songURL2="http://localhost:8080/playlist";
    // this.user="http://localhost:8080/user";
    // this.generateToken="http://localhost:8080";
    // this.image="http://localhost:8080/image";

    this.songURL=this.localurl+":8080/song";
    this.songURL2=this.localurl+":8080/playlist";
    this.user=this.localurl+":8080/user";
    this.generateToken=this.localurl+":8080";
    this.image=this.localurl+":8080/image";
    
  }
  uploadImage(uploadImageData){
    return this.http.post(this.image+"/upload", uploadImageData, {responseType:"text" as "json"});
  }
  getImage(imageName){
    return this.http.get(this.image+"/get/?imageName=" + imageName);
  }

  register(user : User){
    return this.http.post<String>(this.user+'/register',user,{responseType:"text" as "json"});
  }
  giveAccess(username: any) {
    return this.http.post<String>(this.user+'/give-access?username=',username,{responseType:"text" as "json"})
  }

  public getCurrentUSer(){
    return this.http.get<String>(this.generateToken+"/currentUser");
  }

  login(loginDto : LoginDTO){
    
    return this.http.post<String>(this.generateToken+"/generate-token",loginDto,{responseType:"text" as "json"});
  }

  addSong(song :Song)
  {
    console.log(song);
    return this.http.post<String>(this.songURL+'/addsong',song,{responseType:"text" as "json"});
  }
  getList()
  {
    
    return this.http.get<SongDTOService[]>(this.songURL+'/displaysong');
  }
  createPlayList(playlist: PlayListDTOService)
  {
    // console.log(playlist);
    return this.http.post<String>(this.songURL2+'/addplaylist',playlist,{responseType:"text" as "json"});
  }
  displayPlayList(name:String)
  {
    return this.http.get<String[]>(this.songURL2+'/displayplaylist?playlistName='+name);
  }

   storeToken(token){
    //  console.log("token inside service "+token);
    localStorage.setItem("token",token);
    return true;
  }

  isLoggedIn(){
    let token=localStorage.getItem("token");
    if(token==undefined || token=='' || token==null)
    return false;
    else
    return true;
  }
  logout(){
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    return true;
  }
  getToken()
  {
    let token=localStorage.getItem('token');
    // console.log("token inside service "+token);
    return token;
  }
  setUser(user){
    localStorage.setItem("user",JSON.stringify(user));
  }
  getUser(){
    let userStr=localStorage.getItem("user");
    if(userStr==undefined || userStr==null || userStr==''){
      this.logout();
    return null;
    }
    else{
    return JSON.parse(userStr);
    }
  }
  getUserRole(){
    let user=this.getUser();
    if( user.authorities[0].authority!=null){
      return user.authorities[0].authority}
      return false;
  }
}
