import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest,HTTP_INTERCEPTORS } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { OperationsService } from "../operations.service";



@Injectable()
export class AuthInterceptor implements HttpInterceptor{

    constructor(private operationService:OperationsService){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // console.log("inside interceptor");
        let authReq=req;
        // Add jwt token (local storage) request 
        const token=this.operationService.getToken();
        // console.log("token "+token);
        if(token!=null){
           authReq=authReq.clone({setHeaders:{Authorization:'Bearer '+token}});
        }
        return next.handle(authReq);
    }
    
}


export const authInterceptorProviders=[
    {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true
    }
]
     