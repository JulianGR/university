// instantiate our controller
import {Router} from "https://deno.land/x/oak/mod.ts";
import {jwtAuth} from "../middlewares/jwt.ts";
import {BadRequest} from "../helpers/BadRequest.ts";
import {NotFound} from '../helpers/NotFound.ts';
import {ExtractPayload} from "../helpers/ExtractPayload.ts";
import {Success} from "../helpers/Success.ts";
import {ServerController} from "../controllers/ServerController.ts";
import {UserController} from "../controllers/UserController.ts";
import {Unauthorized} from "../helpers/Unauthorized.ts";
import {CheckHex} from "../helpers/CheckHex.ts";
import {CheckFreeRoom} from "../helpers/CheckFreeRoom.ts";

const serverController = new ServerController();
const userController = new UserController();

export function RoomRoutes(router: Router) {
    return router
        .get("/rooms", jwtAuth, async (ctx) => {
            const payload = ExtractPayload(ctx.request.headers.get("Authorization"))
            const servers = await serverController.getRoomsByUserId(payload.id)
            if (servers) {
                return Success(ctx, servers)
            }
            return NotFound(ctx)
        })
        .post("/room", jwtAuth, async (ctx) => {
            if (!ctx.request.hasBody) {
                return BadRequest(ctx);
            }
            const {value} = await ctx.request.body();
            const payload = ExtractPayload(ctx.request.headers.get("Authorization"))
            const server = await serverController.getServerById(value.serverId)
            const filteredRoom = server.rooms.filter((room: any) => room.roomName === value.roomName)

            const users = await userController.getProjectedOneByName(value.userName);
            const userToAdd = users[0]

            if (payload.id !== server.ownerId) {
                return Unauthorized(ctx)
            }

            const updateRoom = await serverController.addUserToRoom(server._id.$oid, value.roomName, userToAdd)

            if (updateRoom.modifiedCount > 0) {
                return Success(ctx, {message: 'Successfully updated'})
            }
            return NotFound(ctx)

        })
        .patch("/room/admin", jwtAuth, async (ctx) => {
            if (!ctx.request.hasBody) {
                return BadRequest(ctx);
            }
            const {value} = await ctx.request.body();
            const payload = ExtractPayload(ctx.request.headers.get("Authorization"))
            const server = await serverController.getServerById(value.serverId)

            if (payload.id !== server.ownerId) {
                return Unauthorized(ctx)
            }
            const updatedRoom = await serverController.deleteUserFromRoom(server._id.$oid, value.roomName, value.userId)

            if (updatedRoom.modifiedCount > 0) {
                return Success(ctx, {message: 'User successfully deleted'})
            }
            return NotFound(ctx)
        })
        .get("/join/:id/:name", jwtAuth, async (ctx) => {
            if (!ctx.params.id || !ctx.params.name || !CheckHex(ctx.params.id)) {
                return BadRequest(ctx);
            }
            const payload = ExtractPayload(ctx.request.headers.get("Authorization"))
            const server = await serverController.getServerById(ctx.params.id)
            const availableRooms = server.rooms.filter((room: any) => {
                const availableUser = room.users.filter((user: any) => user._id.$oid === payload.id)
                if ((room.roomName === ctx.params.name) && availableUser) {
                    return room
                }
            })
            if(!availableRooms) {
                return Unauthorized(ctx);
            }
            const serverJwt = await serverController.join(ctx.params.name)
            if (serverJwt) {
                return Success(ctx, serverJwt)
            }
            return NotFound(ctx)
        })
        .get("/free/join/:name", jwtAuth, async (ctx) => {
            if (!ctx.params.name || !CheckFreeRoom(ctx.params.name)) {
                return BadRequest(ctx);
            }
            const payload = ExtractPayload(ctx.request.headers.get("Authorization"))
            const user = await userController.getOneById(payload.id)

            if(!user) {
                Unauthorized(ctx)
            }

            const serverJwt = await serverController.join(ctx.params.name)
            if (serverJwt) {
                return Success(ctx, serverJwt)
            }
            return NotFound(ctx)
        })
        .patch("/room", jwtAuth, async (ctx) => {
            if (!ctx.request.hasBody) {
                return BadRequest(ctx);
            }
            const {value} = await ctx.request.body();
            const payload = ExtractPayload(ctx.request.headers.get("Authorization"))
            const server = await serverController.getServerById(value.serverId)

            if (payload.id !== server.ownerId) {
                return Unauthorized(ctx)
            }
            const updatedRoom = await serverController.deleteRoom(server._id.$oid, value.roomName)
            if (updatedRoom.modifiedCount > 0) {
                return Success(ctx, {message: 'User successfully deleted'})
            }
            return NotFound(ctx)
        })
}
