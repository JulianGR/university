import {Injectable} from '@angular/core';
import {AuthService} from "./auth.service";
import {HttpClient} from "@angular/common/http";
import {User} from "../models/User";
import {Server} from "../models/Server";
import {environment} from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class TenantService {

  BACKEND_URL = environment.apiUrl

  constructor(private authService: AuthService,
              private http: HttpClient) {
  }

  initializeTenant(location: string, name: string) {
    const body = {
      'location': location,
      'name': name,
      'rooms': []
    };
    return this.http.post(this.BACKEND_URL + 'server', body, this.authService.getAuthenticatedHeaders())
  }

  addRoomToExistingServer(serverId: string, roomName: string, initialUser: User[]) {
    const body = {
      'roomName': roomName,
      'users': initialUser
    }
    return this.http.post(this.BACKEND_URL + 'server/' + serverId, body, this.authService.getAuthenticatedHeaders())

  }

  deleteRoomFromExistingServer(serverId: string, roomName: string) {
    const body = {
      'serverId': serverId,
      'roomName': roomName
    }
    return this.http.patch(this.BACKEND_URL + 'room', body, this.authService.getAuthenticatedHeaders())
  }

  getAllRoomsForUser() {
    return this.http.get<Server[]>(this.BACKEND_URL + 'rooms', this.authService.getAuthenticatedHeaders())
  }

  getOwnedServer(){
    return this.http.get<Server[]>(this.BACKEND_URL + 'servers', this.authService.getAuthenticatedHeaders())
  }

  addUserToExistingRoom(serverId: string, roomName: string, userName: string){
    const body = {
      'serverId': serverId,
      'roomName': roomName,
      'userName': userName
    }
    return this.http.post(this.BACKEND_URL + 'room', body, this.authService.getAuthenticatedHeaders())
  }

  deleteUserFromExistingRoom(serverId: string, roomName: string, userId: string) {
    const body = {
      "serverId": serverId,
      'roomName': roomName,
      'userId': userId
    }
    return this.http.patch(this.BACKEND_URL + 'room/admin', body, this.authService.getAuthenticatedHeaders())
  }

}
