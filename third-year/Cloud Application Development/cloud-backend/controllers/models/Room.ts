import {Model} from "https://deno.land/x/denodb/mod.ts";
import {JitsiJwtConfig} from "../../middlewares/jwt.ts";
import {Jose, makeJwt, Payload, setExpiration,} from "https://deno.land/x/djwt/create.ts";

export interface IRoom {
    _id: { $oid: string };
    roomName: string;
    users: string[];
}

export class Room extends Model {
}

