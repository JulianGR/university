import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Data} from "@angular/router";
import {AnonymousUser, User} from "../models/User";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient,
              private authService: AuthService) { }

  BACKEND_URL = environment.apiUrl

  getUserData() {
    let id = this.authService.getUserIDFromToken();
    return this.http.get<Data>(this.BACKEND_URL + 'user/' + id, this.authService.getAuthenticatedHeaders())
  }

  registerUser(newUser: User) {
    return this.http.post<Data>(this.BACKEND_URL + 'user', newUser, this.authService.getHeaders())
  }

  patchTenantStatus(isTenant: boolean) {
    let id = this.authService.getUserIDFromToken();
    const body = {
      'isTenant': isTenant,
    };
    return this.http.patch(this.BACKEND_URL + 'user/' + id, body, this.authService.getAuthenticatedHeaders())
  }

  getAllUsernames() {
    return this.http.get<AnonymousUser[]>(this.BACKEND_URL + 'usernames', this.authService.getAuthenticatedHeaders())
  }
}
