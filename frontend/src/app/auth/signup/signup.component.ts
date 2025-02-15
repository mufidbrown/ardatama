import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  email = '';
  password = '';

  constructor(private authService: AuthService, private router: Router) {}
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  signup(): void {
    const data = { email: this.email, password: this.password };

    this.authService.signup(data).subscribe(
      () => {
        alert('Signup berhasil. Silakan Signin.');
        this.router.navigate(['/signin']);
      },
      (error) => {
        alert('Signup gagal.');
      }
    );
  }
}