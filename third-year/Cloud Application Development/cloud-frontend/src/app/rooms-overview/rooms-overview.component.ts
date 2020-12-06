import {Component, OnInit} from '@angular/core';
import {RoomService} from '../services/room.service';
import {AuthService} from "../services/auth.service";
import {FormControl, FormGroup} from "@angular/forms";
import {TenantService} from "../services/tenant.service";
import {User} from "../models/User";
import {Router} from "@angular/router";
import {Server} from "../models/Server";

@Component({
  selector: 'app-rooms-overview',
  templateUrl: './rooms-overview.component.html',
  styleUrls: ['./rooms-overview.component.css']
})
export class RoomsOverviewComponent implements OnInit {

  constructor(private roomService: RoomService,
              private authService: AuthService,
              private tenantService: TenantService,
              private router: Router) {
  }

  user: User
  serverArray: Server[] = [];

  ngOnInit(): void {
    this.authService.getUserData().subscribe(
      (data) => {
        this.user = new User(data._id, data.userName, data.firstName, data.lastName, data.isTenant)
      },
      (err) => {
        this.router.navigate(['/login'])
      }
    )
    this.tenantService.getAllRoomsForUser().subscribe(
      (data) => {
        this.serverArray = data;
      }
    )
  }

  joinPremiumRoom(id, location, name, roomName) {
    this.authService.getRoomJWT(id.$oid, roomName).subscribe(
      (data) => {
        const combinedUrl = location + '/' + roomName + '?jwt=' + data.jwt
        window.location.assign(combinedUrl);
      }
    )
  }
}
