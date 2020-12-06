import { Context, Status } from "https://deno.land/x/oak/mod.ts";

export function Success(ctx: Context<any>, body: any) {
    ctx.response.status = Status.OK;
    ctx.response.body = body
    return;
}
