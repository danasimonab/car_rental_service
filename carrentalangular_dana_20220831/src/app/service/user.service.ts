import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../interfaces/User"


@Injectable({
  providedIn: 'root'
})

export class UserService {

  private getUsersUrl = 'http://localhost:8080/find';
  private postUserUrl = 'http://localhost:8080/add';
  private deleteUserUrl = 'http://localhost:8080/deleteById';
  private updateUserUrl = 'http://localhost:8080/update';

  constructor(private http:HttpClient) { }

  getUsers(): Observable<any> {
      return this.http.get<User>(`${this.getUsersUrl}`);
  }

  postUser(userJson:User): Observable<User> {
      return this.http.post<User>(this.postUserUrl, userJson);
  }

  deleteUser(userId:number): Observable<any>{
    return this.http.delete<User>(this.deleteUserUrl + '/' +  userId);
  }

  updateUser(user:User): Observable<any>{
    return this.http.put<User>(this.updateUserUrl, user);
  }

}
