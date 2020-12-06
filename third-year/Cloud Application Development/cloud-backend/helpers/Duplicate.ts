import { Context, Status } from "https://deno.land/x/oak/mod.ts";

export function Duplicate(ctx: Context<any>) {
    ctx.response.status = Status.Conflict;
    ctx.response.body = { message: "Already existing" };
    return;
}
