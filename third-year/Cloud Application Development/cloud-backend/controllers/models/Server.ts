import {IRoom} from "./Room.ts";
import {JitsiJwtConfig} from "../../middlewares/jwt.ts";
import {Jose, makeJwt, Payload, setExpiration,} from "https://deno.land/x/djwt/create.ts";
import {Model} from "https://deno.land/x/denodb/mod.ts";

export interface IServer {
    _id: { $oid: string };
    location: string;
    name: string;
    ownerId: string;
    rooms: IRoom[];
}

export class Server extends Model {
    static generateJitsiJwt(secret: string, aud: string, iss: string, rooms: string) {
        //For further information see:
        //https://github.com/jitsi/lib-jitsi-meet/blob/master/doc/tokens.md
        const payload: Payload = {
            aud: aud,
            iss: iss,
            sub: "meet.jit.si",
            exp: setExpiration(new Date().getTime() + JitsiJwtConfig.expirationTime),
            room: rooms,
        };
        const header: Jose = {
            alg: JitsiJwtConfig.alg as Jose["alg"],
            typ: JitsiJwtConfig.type,
        };
        return makeJwt({header, payload, key: secret});
    }
}
