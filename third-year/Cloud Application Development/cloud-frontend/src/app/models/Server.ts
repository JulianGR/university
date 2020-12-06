import {Room} from "./Room";

export class Server {
  _id: string
  name: string;
  location: string;
  rooms: Room[];

  constructor(_id: string, name: string, location:string, rooms: Room[]) {
    this._id = _id;
    this.name = name;
    this.location = location;
    this.rooms = rooms;
  }
}
