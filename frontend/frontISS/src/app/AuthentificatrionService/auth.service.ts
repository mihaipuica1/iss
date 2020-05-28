import { Injectable } from '@angular/core';
import * as jwt_decode from 'jwt-decode';
import { Router } from '@angular/router';

@Injectable()
export class AuthService {

  private token: string = '';
  public  decoded = "";
  public r = "";

  constructor(private _router: Router) { 
    //this.token ='eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwicm9sZSI6InJldmlld2VyIiwiaWF0IjoxNTE2MjM5MDIyfQ.E2cX6ZJtMO1zhxGhSBQJEk9xvNm1yclHLC9389rnV7U';
    //this.decoded = jwt_decode(this.token);
    //this.r = this.decoded['role'];
  }

  isLoggedIn(){
    return !!localStorage.getItem('token');  
  }

  loginUser(){
    this.token ='eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwicm9sZSI6InNwZWFrZXIiLCJpYXQiOjE1MTYyMzkwMjJ9.f_r1Qr232o_7ZZrbdBLuvrhXjA2hEiq8jaaPBucSSB0';
    localStorage.setItem ('token', this.token);
    this.decoded = jwt_decode(this.token);
    this.r = this.decoded['role'];
    if(this.r="reviewer"){
      this._router.navigate(['reviewer'])
    }

    else if(this.r="speaker"){
      this._router.navigate(['speaker'])
    }

    else{
    this._router.navigate(['']);
    }
  }
  
  getRole(){
    if(localStorage.getItem("token") === null){
      return "none";
    }
    else{
    return jwt_decode(localStorage.getItem('token'))['role'];
    }
  }

  logoutUser(){
    this.token="";
    this.decoded="";
    localStorage.removeItem('token');
    this._router.navigate(['']);
  }

}
