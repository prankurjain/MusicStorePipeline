import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatSnackBar,MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition, } from '@angular/material';
import { Router} from '@angular/router';
import { LoginDTO } from 'src/dto/loginDto';
import { UserToken } from 'src/dto/user-token';
import swal from 'sweetalert2';
import { OperationsService } from '../operations.service';


@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.scss']
})
export class LoginComponentComponent implements OnInit {

  loginForm:FormGroup;
  loginDto:LoginDTO=new LoginDTO();
  userToken:UserToken;
  horizontalPosition: MatSnackBarHorizontalPosition = 'right';
  verticalPosition: MatSnackBarVerticalPosition = 'top';
  constructor(private operationService: OperationsService,private snackbar: MatSnackBar,private router: Router) { }

  ngOnInit() {
    this.loginForm=new FormGroup({
      username: new FormControl,
      password: new FormControl
    })
  }

  login(){
    // console.log(this.loginDto);
    // console.log(this,this.loginForm);
    if(this.loginDto.username==''||this.loginDto.username==null ){
      this.snackbar.open("Username is required",'',{duration: 3000});
      return;
    }
    if(this.loginDto.password==''||this.loginDto.password==null ){
      this.snackbar.open("Password is required",'',{duration: 3000});
      return;
    }

    this.operationService.login(this.loginDto).subscribe(
      (data: any)=>{
        this.operationService.storeToken(data);
        this.operationService.getCurrentUSer().subscribe((user)=>{
          this.operationService.setUser(user);
          this.snackbar.open("Login Successfully",'',{
            duration: 3000,
            horizontalPosition: this.horizontalPosition,
            verticalPosition: this.verticalPosition
          });
          // swal.fire("Success",'Login Successfully','success');
          this.operationService.loginStatusSubject.next(this.operationService.getUserRole());
          this.router.navigateByUrl('/');
          
        })
      },
      (error)=>{
        swal.fire("Error",JSON.parse(error.error).errorMessage,'error'); 
      }
    )
  }
}
