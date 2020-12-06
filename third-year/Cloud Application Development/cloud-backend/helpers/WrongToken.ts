import { Context, Status } from "https://deno.land/x/oak/mod.ts";

export function WrongToken(ctx: Context<any>) {
    ctx.response.status = Status.Unauthorized;
    ctx.response.body = { message: "Wrong Token" };
    return;
}
