import { Component, OnInit } from '@angular/core';
import { AuthService } from '../AuthentificatrionService/auth.service';

@Component({
  selector: 'app-chose-role-user',
  templateUrl: './chose-role-user.component.html',
  styleUrls: ['./chose-role-user.component.css']
})
export class ChoseRoleUserComponent implements OnInit {

  constructor(private auth:AuthService) { }

  ngOnInit() {
  }

  heGoesSpeaker(){

    this.auth.heWantsSpeaker("speaker");
    localStorage.setItem('role','speaker');
    window.location.replace('http://localhost:8080/api/login?redirect_uri=http://localhost:4200/home');
  }

}
