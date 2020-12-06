import {Context} from "https://deno.land/x/oak/mod.ts";
import {validateJwt} from "https://deno.land/x/djwt/validate.ts";
import {WrongToken} from "../helpers/WrongToken.ts";
import {Unauthorized} from "../helpers/Unauthorized.ts";
import "https://deno.land/x/dotenv/load.ts";

/**
 * Create a default configuration
 */
export const JwtConfig = {
    header: "Authorization",
    schema: "Bearer",
    // use Env variable
    secretKey: Deno.env.get("SECRET") || "",
    expirationTime: 60000,
    type: "JWT",
    alg: "HS256",
};

export const JitsiJwtConfig = {
    expirationTime: 60000,
    type: "JWT",
    alg: "HS256",
};

export async function jwtAuth(
    ctx: Context<Record<string, any>>,
    next: () => Promise<void>
) {
    // Get the token from the request
    const token = ctx.request.headers
        .get(JwtConfig.header)
        ?.replace(`${JwtConfig.schema} `, "");

    // reject request if token was not provided
    if (!token) {
        return Unauthorized(ctx);
    }
    // check the validity of the token
    if (
        // @ts-ignore
        !(await validateJwt(token, JwtConfig.secretKey, {isThrowing: false}))
    ) {
        return WrongToken(ctx);
    }
    // JWT is correct, so continue and call the private route
    await next();
}
