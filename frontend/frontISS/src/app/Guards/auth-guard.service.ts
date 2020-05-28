import { Injectable } from '@angular/core';
import { AuthService } from '../AuthentificatrionService/auth.service';
import { Router, CanActivate } from '@angular/router';

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private _authService: AuthService,
    private _router: Router) { }

  canActivate(): boolean {
    if (this._authService.isLoggedIn() ) {
      console.log('true')
      return true
    } else {
      console.log('false')            
      this._router.navigate(['home'])
      return false
    }
  }

}
