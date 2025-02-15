import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css']
})
export class SigninComponent implements OnInit {

  email = '';
  password = '';

  constructor(private authService: AuthService, private router: Router) {}
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  signin(): void {
    const credentials = { email: this.email, password: this.password };

    this.authService.signin(credentials).subscribe(
      (response) => {
        localStorage.setItem('token', response); // Simpan token
        this.router.navigate(['/admin/dashboard']); // Arahkan ke dashboard admin
      },
      (error) => {
        alert('Signin gagal. Periksa kredensial Anda.');
      }
    );
  }
}
