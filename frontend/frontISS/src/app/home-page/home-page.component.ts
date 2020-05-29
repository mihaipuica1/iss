import { Component, OnInit } from '@angular/core';
import { AuthService } from '../AuthentificatrionService/auth.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  constructor(private auth:AuthService, private router: ActivatedRoute) { }

  ngOnInit() {
    var tok=this.router.snapshot.queryParamMap.get('token');
    this.auth.setToken(tok);
  }
}
