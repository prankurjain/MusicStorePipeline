import { Component, OnInit } from '@angular/core';
import { OperationsService } from 'src/app/operations.service';
import swal from 'sweetalert2';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  username:String;
  constructor(private operationService:OperationsService) { }

  ngOnInit() {
  }

  giveAccess(){
    
    if(this.username==null || this.username==''){
      swal.fire("Error","Enter Username","error");
    return;
    }
    this.operationService.giveAccess(this.username).subscribe((data)=>{
      swal.fire("Success",data.toString(),"success");
    },
    (error)=>{
      console.log(JSON.parse(error.error).errorMessage);
      swal.fire("Error",JSON.parse(error.error).errorMessage,'error');
    }
    );
  }
}
