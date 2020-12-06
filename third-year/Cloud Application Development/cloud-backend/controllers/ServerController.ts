import {IServer, Server} from "./models/Server.ts";
import "./Database.ts";
import {DatabaseController} from "./Database.ts";
import {IRoom} from "./models/Room.ts";
import "https://deno.land/x/dotenv/load.ts";


const serverCollection = new DatabaseController().getCollection("servers");

export class ServerController {
    async create(values: IServer, tenantID: string) {
        return await serverCollection.insertOne({
            location: values.location,
            name: values.name,
            ownerId: tenantID,
            rooms: values.rooms,
        })
    }

    async getAll() {
        return await serverCollection.find();
    }

    async getAllOwnedServers(ownerId: string) {
        return await serverCollection.find({ "ownerId": ownerId })
    }

    //ToDo: Delete Room, Delete Server
    async addRoom(serverId: string, room: IRoom) {
        return await serverCollection.updateOne(
            {_id: {"$oid": serverId}},
            {$push: {rooms: room}}
        )
    }

    async addUserToRoom(serverId: string, roomName: string, user: any) {
        return await serverCollection.updateOne(
            {_id: {"$oid": serverId}, "rooms.roomName": roomName },
            {$push: {"rooms.$.users": user}}
        )
    }

    async deleteUserFromRoom(serverId: string, roomName: string, userId: string){
        return await serverCollection.updateOne(
            {_id: {$oid: serverId}, "rooms.roomName": roomName },
            {$pull: {"rooms.$.users": { _id: {$oid: userId}}}}
        )
    }

    async deleteRoom(serverId: string, roomName: string) {
        return await serverCollection.updateOne(
            {_id: {$oid: serverId}, "rooms.roomName": roomName },
            {$pull: {"rooms": { roomName: roomName}}}
        )
    }

    async deleteServer(serverId: string) {
        return await serverCollection.deleteOne(
            { "serverId": serverId }
        )
    }


    async getServerById(serverId: string) {
        return await serverCollection.findOne({_id: {"$oid": serverId}})
    }
    async getServerByIdWithPrivateFields(serverId: string) {
        //ToDo: Add secret fields to output
        return await serverCollection.findOne({_id: {"$oid": serverId}})
    }

    async getServerByOwnerID(ownerId: string) {
        return await serverCollection.findOne({ownerId: ownerId})
    }

    async getRoomsByUserId(userId: string) {
        return await serverCollection.aggregate([
            {$match: {'rooms.users._id': {"$oid": userId}}},
            {$unwind: '$rooms'},
            {$match: {'rooms.users._id': {"$oid": userId}}},
            {$group: {
                _id: "$_id",
                name: { "$first": "name" },
                location: { "$first": "$location" },
                rooms: { $addToSet: "$rooms"}}}
        ]);
    }

    async join(roomName: string) {
        const secret = Deno.env.get("JITSISECRET") || ""
        const aud = Deno.env.get("JITSIAPPID") || ""
        const iss = Deno.env.get("JITSIAPPID") || ""
        const jwt = Server.generateJitsiJwt(secret, iss, aud, roomName)
        return {
            "jwt": jwt
        }
    }
}
