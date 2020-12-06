import * as bcrypt from "https://deno.land/x/bcrypt/mod.ts";
import {IUser, User} from "./models/User.ts";
import "./Database.ts";
import {DatabaseController} from "./Database.ts";

const userCollection = new DatabaseController().getCollection('users');

export class UserController {
    async create(values: IUser) {
        const isUsernameTaken = await this.getOneByName(values.userName)
        if (isUsernameTaken) {
            return false
        }
        const password = await User.hashPassword(values.password);
        return await userCollection.insertOne({
            userName: values.userName,
            firstName: values.firstName,
            lastName: values.lastName,
            password: password,
            isTenant: values.isTenant,
        })
    }

    async deleteById(userIdToDelete: string) {
        return await userCollection.deleteOne({_id: userIdToDelete});
    }

    async deleteByName(userNameToDelete: string) {
        return await userCollection.deleteOne({userName: userNameToDelete});
    }

    async getAll() {
        return await userCollection.find();
    }

    async getAllUsernames() {
        return await userCollection.aggregate([
            {$match: {}},
            {$project: { "firstName": 0, "lastName": 0, "password": 0, "isTenant": 0  }}
        ]);
    }

    async getOneById(userID: string) {
        return await userCollection.findOne({_id: {"$oid": userID}});
    }

    async getOneByName(userName: string) {
        return await userCollection.findOne({userName: userName})
    }

    async getProjectedOneByName(userName: string) {
        return await userCollection.aggregate([
            {$match: {userName: userName}},
            {$project: { "_id": 1, "userName": 1, "firstName": 1, "lastName": 1, "isTenant": 1  }}
        ]);
    }

    async update(userID: string, userObject: IUser) {
        const password = await User.hashPassword(userObject.password);

        return await userCollection.updateOne(
            {
                _id: {"$oid": userID}
            },
            {$set: userObject});
    }

    async login(username: string, password: string) {
        const u = await this.getOneByName(username);
        const id = u._id.$oid
        if (!u || !(await bcrypt.compare(password, u.password))) {
            return false;
        } else {
            return User.generateJwt(id);
        }
    }
}
