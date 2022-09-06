import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = "http://localhost:8080/users";

  constructor(private httpclient: HttpClient) { }

  getAllUsers(): Observable<User[]>{
    return this.httpclient.get<User[]>(`${this.baseUrl}`);
  }

  createUser(user: User): Observable<Object>{
    return this.httpclient.post(`${this.baseUrl}`, user);
  }

  getUserById(id: number): Observable<User>{
    return this.httpclient.get<User>(`${this.baseUrl}/${id}`);
  }

  updateUserById(id: number, user: User): Observable<Object>{
    return this.httpclient.put(`${this.baseUrl}/${id}`, user);
  }

  deleteUserById(id: number): Observable<Object>{
    return this.httpclient.delete(`${this.baseUrl}/${id}`);
  }
}
