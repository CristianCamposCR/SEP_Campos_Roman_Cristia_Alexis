import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../service/auth.service';
import { UserLogin } from '../../types/user';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
})
export class SigninComponent implements OnInit {
  user: UserLogin = {
    email: '',
    password: '',
  };

  session: any = {
    logged: false
  };

  logoPath: string = '../../../../../assets/img/utez.png';
  get isLoading (){
    return this.authServive.loading;
  }

  constructor(private authServive: AuthService, private router: Router) {

    this.session.logged = localStorage.getItem("token")? true: false;

  }

  ngOnInit(): void {
    if (this.session.logged) {
      this.router.navigateByUrl('/');
    }
  }

  signin() {
    this.authServive.signin(this.user);
  }
}
