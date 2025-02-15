import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = 'http://localhost:8080'; // Ganti dengan URL backend Anda

  constructor(private http: HttpClient, private router: Router) {}

  signin(credentials: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/signin`, credentials);
  }

  signup(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/signup`, data);
  }

  forgotPassword(email: string): Observable<any> {
    return this.http.post(`${this.baseUrl}/forgot-password`, { email });
  }

  resetPassword(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/reset-password`, data);
  }

  signout(): void {
    const token = localStorage.getItem('token');
    if (token) {
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
      this.http.post(`${this.baseUrl}/signout`, {}, { headers }).subscribe(() => {
        localStorage.removeItem('token');
        this.router.navigate(['/signin']);
      });
    }
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }
}
