import {User} from "./User";

export class Room {
  roomName: string;
  users: User[];

  constructor(roomName: string, users: User[]) {
    this.roomName = roomName;
    this.users = users;
  }
}
