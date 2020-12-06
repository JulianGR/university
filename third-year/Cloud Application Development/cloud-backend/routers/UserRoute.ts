import {Router} from "https://deno.land/x/oak/mod.ts";
import {UserController} from "../controllers/UserController.ts";
import {BadRequest} from "../helpers/BadRequest.ts";
import {NotFound} from '../helpers/NotFound.ts';
import {Success} from "../helpers/Success.ts";
import {Duplicate} from "../helpers/Duplicate.ts";
import {jwtAuth} from "../middlewares/jwt.ts";
import {CheckHex} from "../helpers/CheckHex.ts";

// instantiate our controller
const controller = new UserController();

export function UserRoutes(router: Router) {
    return router
        .get("/users", async (ctx) => {
            const users = await controller.getAll();

            if (users) {
                return Success(ctx, users)
            }
            return NotFound(ctx)
        })
        .get("/usernames", jwtAuth, async (ctx) => {
            const users = await controller.getAllUsernames();
            if (users) {
                return Success(ctx, users)
            }
            return NotFound(ctx)
        })
        .post("/login", async (ctx) => {
            if (!ctx.request.hasBody) {
                return BadRequest(ctx);
            }
            const {value} = await ctx.request.body();

            // generate JWT
            const jwt = await controller.login(value.userName, value.password);

            if (!jwt) {
                return BadRequest(ctx);
            }
            return Success(ctx, {jwt: jwt})
        })
        .get("/user/:id", async (ctx) => {
            if (!ctx.params.id || !CheckHex(ctx.params.id)) {
                return BadRequest(ctx);
            }
            const user = await controller.getOneById(ctx.params.id)

            if (user) {
                return Success(ctx, user)
            }
            return NotFound(ctx);
        })
        .post("/user", async (ctx) => {
            if (!ctx.request.hasBody) {
                return BadRequest(ctx);
            }
            const {value} = await ctx.request.body();

            const user = await controller.create(value).catch(
                (err) => Duplicate(ctx));

            if (user) {
                return Success(ctx, user)
            }
            return NotFound(ctx);
        })
        .patch("/user/:id", async (ctx) => {
            if (!ctx.request.hasBody || !ctx.params.id || !CheckHex(ctx.params.id)) {
                return BadRequest(ctx);
            }
            const {value} = await ctx.request.body();
            const user = await controller.update(ctx.params.id, value);

            if (user) {
                return Success(ctx, user)
            }
            return NotFound(ctx);
        })
        .delete("/user/:id", async (ctx) => {
            if (!ctx.params.id || !CheckHex(ctx.params.id)) {
                return BadRequest(ctx);
            }
            await controller.deleteById(ctx.params.id);
            return Success(ctx, {message: "Ok"})
        });
}
