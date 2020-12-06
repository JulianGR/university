import {Model} from "https://deno.land/x/denodb/mod.ts";
import * as bcrypt from "https://deno.land/x/bcrypt/mod.ts";
import {Jose, makeJwt, Payload, setExpiration,} from "https://deno.land/x/djwt/create.ts";
import {JwtConfig} from "../../middlewares/jwt.ts";

export interface IUser {
    _id: { $oid: string };
    userName: string;
    firstName: string;
    lastName: string;
    password: string;
    isTenant: boolean;
}

export class User extends Model {

    static async hashPassword(password: string) {
        const salt = await bcrypt.genSalt(8);
        return bcrypt.hash(password, salt);
    }

    static generateJwt(id: string) {
        // Create the payload with the expiration date (token have an expiry date) and the id of current user (you can add that you want)
        const payload: Payload = {
            id,
            exp: setExpiration(new Date().getTime() + JwtConfig.expirationTime),
        };
        const header: Jose = {
            alg: JwtConfig.alg as Jose["alg"],
            typ: JwtConfig.type,
        };

        // return the generated token
        return makeJwt({header, payload, key: JwtConfig.secretKey});
    }
}
