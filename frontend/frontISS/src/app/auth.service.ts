import { Injectable } from '@angular/core';
import * as jwt_decode from 'jwt-decode';
import { Router } from '@angular/router';

@Injectable()
export class AuthService {

  private role;
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
    this.token ='eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwicm9sZSI6InJldmlld2VyIiwiaWF0IjoxNTE2MjM5MDIyfQ.E2cX6ZJtMO1zhxGhSBQJEk9xvNm1yclHLC9389rnV7U';
    localStorage.setItem ('token', this.token);
    
    this.decoded = jwt_decode(this.token);
    this.r = this.decoded['role'];
    localStorage.setItem ('role', this.decoded['role']);
    this._router.navigate(['']);
  }

  logoutUser(){
    this.token="";
    this.decoded="";
    this.decoded="";
    localStorage.removeItem('token');
    this._router.navigate(['']);
  }

}
