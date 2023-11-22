import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {User} from "../entity/user";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UsersService {
  private baseURL = "http://localhost:8080/users";
  constructor(private httpClient: HttpClient) {}
  createUser(user: User): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, user);
  }

  loginUser(email: string | null, password: string | null):Observable<any>{
    const passwordValue = password || '';
    const emailValue = email || '';
    const params = new HttpParams()
      .set('operation', 'LOGIN')
      .set('email', emailValue)
      .set('password', passwordValue);

    return this.httpClient.patch(`${this.baseURL}`, null, { params: params });
  }


}
