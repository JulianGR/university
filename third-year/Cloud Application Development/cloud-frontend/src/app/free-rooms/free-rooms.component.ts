import {Component, OnInit} from '@angular/core';
import {environment} from "../../environments/environment";
import {FormControl, FormGroup} from "@angular/forms";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-free-rooms',
  templateUrl: './free-rooms.component.html',
  styleUrls: ['./free-rooms.component.css']
})
export class FreeRoomsComponent implements OnInit {

  serverRoute = environment.jitsiUrl;
  freeRoomPrefix = environment.freeRoomPrefix;

  freeRoomForm = new FormGroup({
    roomCode: new FormControl(''),
  });

  constructor(private authService: AuthService) {
  }

  ngOnInit(): void {
  }

  joinPremiumRoom() {
    const roomName = this.freeRoomPrefix + this.freeRoomForm.value.roomCode;
    this.authService.getFreeRoomJWT(roomName).subscribe(
      (data) => {
        const combinedUrl = this.serverRoute + '/' +  roomName + '?jwt=' + data.jwt
        window.location.assign(combinedUrl);
      }
    )
  }
}
