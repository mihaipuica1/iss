import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title: string;
  role: string;
  islogged: boolean;
  //@ViewChild('myModal') myModal: ElementRef;
  constructor(private auth:AuthService) {
    this.title = 'Users';
    this.role=localStorage.getItem('role');
  }

  ngOnInit() {


  }

  doLogin(){
    this.auth.loginUser();
    this.role=this.auth.r;
  }

  refre(){
    window.location.reload();
  }
}
