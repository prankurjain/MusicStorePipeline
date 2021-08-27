import { AddPlayListComponent } from './add-play-list/add-play-list.component';
import { AddStudentComponent } from './add-student/add-student.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DisplayPlayListComponent } from "src/app/display-play-list/display-play-list.component";
import { SignupComponent } from './signup/signup.component';
import { LoginComponentComponent } from './login-component/login-component.component';
import { NavbarComponent } from './navbar/navbar.component';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { UserDashboardComponent } from './users/user-dashboard/user-dashboard.component';
import { AdminGuard } from './guard/admin.guard';
import { ErrorComponent } from './error-pages/error/error.component';
import { UserGuard } from './guard/user.guard';
import { HomeComponent } from './home/home.component';


const routes: Routes = [
  {
    path:"addsong",
    component:AddStudentComponent
  },
  {
    path:"addplaylist",
    component:AddPlayListComponent
  },
  {
    path:"displayplaylist",
    component:DisplayPlayListComponent
  },
  {
    path:"signup",
    component:SignupComponent
  },
  {
    path:"login",
    component:LoginComponentComponent
  },
  {
    path:"navbar",
    component: NavbarComponent
  },{
    path:"admindashboard",
    component:DashboardComponent,
    canActivate: [AdminGuard],
    children:[{
      path:"addsong",
      component:AddStudentComponent
    },{
      path:"",
      component:HomeComponent
    }]
  },
  {
    path:"userdashboard",
    component: UserDashboardComponent,
    canActivate:[UserGuard]
  },
  {
    path: 'error',
    component:ErrorComponent
  },
  {
    path: '',
    component:HomeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
