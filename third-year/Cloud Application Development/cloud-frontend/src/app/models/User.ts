export class User {

  constructor(_id, userName, firstName, lastName, isTenant) {
    this._id = _id;
    this.userName = userName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.isTenant = isTenant;

  }

  _id?: any;
  userName: string;
  firstName: string;
  lastName: string;
  password?: string;
  isTenant?: string;
}

export class AnonymousUser {
  _id?: string;
  userName: string;

  constructor(_id: string, userName: string) {
    this._id = _id;
    this.userName = userName;
  }
}

