import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { AddStudentComponent } from './add-student/add-student.component';
import {MatIconModule} from '@angular/material/icon';
import { Injectable, ErrorHandler } from "@angular/core";
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AddPlayListComponent } from './add-play-list/add-play-list.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { DisplayPlayListComponent } from './display-play-list/display-play-list.component';
import { SignupComponent } from './signup/signup.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material';
import {MatInputModule} from '@angular/material/input';
import { LoginComponentComponent } from './login-component/login-component.component';
import {MatCardModule} from '@angular/material/card';
import { NavbarComponent } from './navbar/navbar.component';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import { authInterceptorProviders } from './services/auth.interceptor';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { UserDashboardComponent } from './users/user-dashboard/user-dashboard.component';
import { ErrorComponent } from './error-pages/error/error.component';
import { HomeComponent } from './home/home.component';
import {MatRadioModule} from '@angular/material/radio';
import {MatListModule} from '@angular/material/list';
import { SidebarComponent } from './sidebar/sidebar.component';
import { VgCoreModule } from 'videogular2/compiled/core';
import { VgControlsModule } from 'videogular2/compiled/controls';
import { VgOverlayPlayModule } from 'videogular2/compiled/overlay-play';
import { VgBufferingModule } from 'videogular2/compiled/buffering';
import { YouTubePlayerModule } from "@angular/youtube-player";


@NgModule({
  declarations: [
    AppComponent,
    AddStudentComponent,
    
    AddPlayListComponent,
    
    DisplayPlayListComponent,
    
    SignupComponent,
    
    LoginComponentComponent,
    
    NavbarComponent,
    
    DashboardComponent,
    
    UserDashboardComponent,
    
    ErrorComponent,
    
    HomeComponent,
    
    SidebarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatSnackBarModule,
    MatRadioModule,
     MatListModule ,VgControlsModule,VgCoreModule,VgOverlayPlayModule,VgBufferingModule,
     YouTubePlayerModule

  ],
   providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
