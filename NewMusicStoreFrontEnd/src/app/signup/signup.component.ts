import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { User } from 'src/entity/user';
import { ErrorHandler } from 'src/dto/errorhandler';
import { OperationsService } from '../operations.service';
import swal from 'sweetalert2';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit {

  userForm: FormGroup;
  user: User=new User();
  errorHandler: ErrorHandler=new ErrorHandler();
  constructor(private service: OperationsService) {
   }



  ngOnInit() {
    this.userForm =new FormGroup({
      username: new FormControl(),
      password: new FormControl(),
      firstName:new FormControl(),
      lastName: new FormControl(),
      email: new FormControl(),
      phone: new FormControl()


    });
  }

  formSubmit(){
     console.log(this.user);
     this.service.register(this.user).subscribe((msg) =>{
       console.log(msg);
       swal.fire("Success",msg.toString(),'success');
     
    },
       (error)=>{
          
        //  alert( JSON.parse(error.error).errorMessage); 
        swal.fire("Error",JSON.parse(error.error).errorMessage,'error'); 
        
       });
       this.userForm.reset();
    
  }
}
