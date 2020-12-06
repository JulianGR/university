import { Context, Status } from "https://deno.land/x/oak/mod.ts";

export function Unauthorized(ctx: Context<any>) {
    ctx.response.status = Status.Unauthorized;
    ctx.response.body = { message: "Access Denied" };
    return;
}
