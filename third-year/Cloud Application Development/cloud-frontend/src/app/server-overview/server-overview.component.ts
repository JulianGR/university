import {Component, OnInit} from '@angular/core';
import {TenantService} from "../services/tenant.service";
import {Server} from "../models/Server";
import {UserService} from "../services/user.service";
import {AddUserDialogComponent} from "../helper/add-user-dialog/add-user-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {AnonymousUser, User} from "../models/User";
import {AuthService} from "../services/auth.service";
import {AddRoomDialogComponent} from "../helper/add-room-dialog/add-room-dialog.component";

@Component({
  selector: 'app-server-overview',
  templateUrl: './server-overview.component.html',
  styleUrls: ['./server-overview.component.css']
})
export class ServerOverviewComponent implements OnInit {

  ownedServer: Server[] = [];
  userNames: AnonymousUser[];

  constructor(private tenantService: TenantService,
              private userService: UserService,
              private authService: AuthService,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.tenantService.getOwnedServer().subscribe(
      (data) => {
        this.ownedServer = data
      }
    )
    this.userService.getAllUsernames().subscribe(
      (data) => {
        this.userNames = data;
      }
    )
  }

  updateServerData() {
    this.tenantService.getOwnedServer().subscribe(
      (data) => {
        this.ownedServer = data
      })
  }


  deleteUserFromRoom(serverId, roomName, userId) {
    serverId = serverId?.$oid
    userId = userId?.$oid
    this.tenantService.deleteUserFromExistingRoom(serverId, roomName, userId).subscribe(
      (data) => this.updateServerData()
    )
  }

  openAddUserDialog(serverID: any, roomName: string, existingUsers: User[]) {
    const alreadyAssignedUsernames = existingUsers.map((user) => user.userName);
    const filteredUserNames = this.userNames.filter((anonymousUser) =>
      !alreadyAssignedUsernames.includes(anonymousUser.userName))

    let dialogRef = this.dialog.open(AddUserDialogComponent, {
      data: {users: filteredUserNames},
    })
    dialogRef.afterClosed().subscribe(
      (result) => {
        if (result) {
          this.tenantService.addUserToExistingRoom(serverID.$oid, roomName, result).subscribe(
            (data) => this.updateServerData()
          )
        }
      });
  }

  createPremiumRoom(serverId) {
    let dialogRef = this.dialog.open(AddRoomDialogComponent)
    dialogRef.afterClosed().subscribe(
      (result) => {
        if (result) {
          this.authService.getUserData().subscribe(
            (data) => {
              let initialUsers: User[] = [];
             let currentUser = new User(data._id, data.userName, data.firstName, data.lastName, data.isTenant)
              initialUsers.push(currentUser)
              this.tenantService.addRoomToExistingServer(serverId.$oid, result, initialUsers).subscribe(
                (data) => this.updateServerData()
              )
            }
          )
        }
      });
  }

  deleteRoomFromServer(serverId: any, roomName: string) {
    serverId = serverId?.$oid
    this.tenantService.deleteRoomFromExistingServer(serverId, roomName).subscribe(
      (data) => this.updateServerData()
    )
  }
}
