import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OperationsService } from '../operations.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  isLoggedIn=false;
  user=null;
  role=null;
  constructor(public operationService: OperationsService,private router: Router) { }

  ngOnInit() {
    // this.user=this.operationService.getCurrentUSer();
    // // console.log("user"+this.user);

    // this.isLoggedIn=this.operationService.isLoggedIn();
    // console.log("isloggedIn"+this.isLoggedIn);
    this.operationService.loginStatusSubject.asObservable().subscribe((data)=>{
    // console.log(data);
    this.role=data;
    this.user=this.operationService.getCurrentUSer();
    // console.log("user"+this.user);

    this.isLoggedIn=this.operationService.isLoggedIn();
    // console.log("isloggedIn"+this.isLoggedIn);
    });
  }
logout(){
  this.operationService.logout();
  this.isLoggedIn=false;
  this.user=null;
  this.role=null;
  this.router.navigateByUrl("/login");
  
}
}
