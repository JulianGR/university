import {MongoClient} from "https://deno.land/x/mongo@v0.8.0/mod.ts";
import "https://deno.land/x/dotenv/load.ts";


export class DatabaseController {
    dbName = Deno.env.get("DBNAME") || ""
    dbUser = Deno.env.get("MONGODBUSER") || ""
    dbPassword = Deno.env.get("MONGODBPASSWORD") || ""
    dbRoute = Deno.env.get("MONGODB") || ""
    dbHostUrl = "mongodb://" + this.dbUser + ':' + this.dbPassword + '@' + this.dbRoute;
    client: MongoClient;

    constructor() {
        this.client = new MongoClient();
        this.client.connectWithUri(this.dbHostUrl)
    }

    getCollection(collectionName: string) {
        let db = this.client.database(this.dbName);
        return db.collection(collectionName)
    }
}


