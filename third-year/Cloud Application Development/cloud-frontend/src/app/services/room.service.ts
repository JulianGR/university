import {Injectable} from '@angular/core';
import {AuthService} from './auth.service';
import {HttpClient} from '@angular/common/http';
import {Data} from '@angular/router';
import {Observable} from 'rxjs';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  BACKEND_URL = environment.apiUrl

  constructor(private authService: AuthService,
              private http: HttpClient) {

  }

  getRooms(): Observable<any> {
    console.log('fetching rooms')
    return this.http.get<Data>(this.BACKEND_URL, this.authService.getAuthenticatedHeaders())
  }
}
