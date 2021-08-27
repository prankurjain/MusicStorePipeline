import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { OperationsService } from '../operations.service';

@Injectable({
  providedIn: 'root'
})
export class UserGuard implements CanActivate {
  constructor(private operationService: OperationsService,private router :Router){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(this.operationService.getUserRole()=='normal' && this.operationService.isLoggedIn()){
        console.log("Inside User guard "+(this.operationService.getUserRole()));
        return true;
    }
    else if(this.operationService.isLoggedIn()==false){
      console.log("Inside user guard");
      alert("error");
    }
    else{
      this.router.navigateByUrl("/login");
    }
  }
  
}
