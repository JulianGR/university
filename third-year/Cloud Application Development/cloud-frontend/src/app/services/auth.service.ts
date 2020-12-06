import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Data, Router} from '@angular/router';
import * as jwt_decode from "jwt-decode";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  BACKEND_URL = environment.apiUrl
  token;

  constructor(private http: HttpClient,
              private router: Router) {
  }

  getToken() {
    if(!this.token) {
      this.token = localStorage.getItem('id_token');
    }
    return this.token;
  }

  isAuthorized() {
    if(!this.token) {
      this.token = localStorage.getItem('id_token');
    }
    return !!this.token;

  }

  login(username, password) {
    const body = {
      'userName': username,
      'password': password
    };

    this.http.post<Data>(this.BACKEND_URL + 'login', body, this.getHeaders()).subscribe(
      data => {
        this.token = data.jwt;
        localStorage.setItem('id_token', data.jwt);
        this.router.navigate(['/rooms']);
      }
    );
  }

  getUserData() {
    let tokenInfo = this.getDecodedAccessToken()
    return this.http.get<Data>(this.BACKEND_URL + 'user/' + tokenInfo?.id, this.getHeaders())
  }

  getUserIDFromToken(): any {
    let tokenInfo = this.getDecodedAccessToken()
    return tokenInfo.id
  }

  getDecodedAccessToken(): any {
    try{
      return jwt_decode(this.getToken());
    }
    catch(Error){
      return null;
    }
  }

  getHeaders() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      })
    };
  }

  getAuthenticatedHeaders() {
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': this.getToken()
      })
    };
  }

  logOut() {
    localStorage.removeItem('id_token')
    this.token = null;
  }

  getRoomJWT(serverId: string, roomName: string) {
    return this.http.get<Data>(this.BACKEND_URL + 'join/' + serverId + '/' + roomName, this.getAuthenticatedHeaders())
  }

  getFreeRoomJWT(roomName: string) {
    return this.http.get<Data>(this.BACKEND_URL + 'free/join/' + roomName, this.getAuthenticatedHeaders())
  }
}
