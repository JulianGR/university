import {Router} from "https://deno.land/x/oak/mod.ts";
import {jwtAuth} from "../middlewares/jwt.ts";
import {ServerController} from "../controllers/ServerController.ts";
import {BadRequest} from "../helpers/BadRequest.ts";
import {NotFound} from "../helpers/NotFound.ts";
import {UserController} from "../controllers/UserController.ts";
import {Unauthorized} from "../helpers/Unauthorized.ts";
import {Success} from "../helpers/Success.ts";
import {ExtractPayload} from "../helpers/ExtractPayload.ts";
import {CheckHex} from "../helpers/CheckHex.ts";


const serverController = new ServerController();
const userController = new UserController();

export function ServerRoutes(router: Router) {
    return router
        .get("/servers", jwtAuth, async (ctx) => {
            const payload = ExtractPayload(ctx.request.headers.get("Authorization"))
            const servers = await serverController.getAllOwnedServers(payload.id)
            if (servers) {
                return Success(ctx, servers)
            }
            return NotFound(ctx)

        })
        .post("/server", jwtAuth, async (ctx) => {
            if (!ctx.request.hasBody) {
                return BadRequest(ctx);
            }
            const payload = ExtractPayload(ctx.request.headers.get("Authorization"))
            const user = await userController.getOneById(payload.id)

            if (!user.isTenant) {
                return Unauthorized(ctx);
            }
            const {value} = await ctx.request.body();
            const newServer = await serverController.create(value, payload.id)

            if (newServer) {
                return Success(ctx, newServer)
            }

            return NotFound(ctx);
        })
        .post("/server/:id", jwtAuth, async (ctx) => {
            if (!ctx.request.hasBody || !ctx.params.id || !CheckHex(ctx.params.id)) {
                return BadRequest(ctx);
            }

            const payload = ExtractPayload(ctx.request.headers.get("Authorization"))
            const server = await serverController.getServerById(ctx.params.id)
            if (payload.id !== server.ownerId) {
                return Unauthorized(ctx)
            }

            const {value} = await ctx.request.body();
            const updateRoom = await serverController.addRoom(server._id.$oid, value)

            if (updateRoom.modifiedCount > 0) {
                return Success(ctx, {message: 'Successfully updated'})
            }
            return NotFound(ctx)
        })
}
