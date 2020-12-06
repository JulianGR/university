// app.ts
import {Application, Router} from "https://deno.land/x/oak/mod.ts";
import {oakCors} from "https://deno.land/x/cors/mod.ts";

import {UserRoutes} from "./routers/UserRoute.ts";
import {UserController} from "./controllers/UserController.ts";
import {ServerRoutes} from "./routers/ServerRoute.ts";
import {RoomRoutes} from "./routers/RoomRoute.ts";

// Initialise app
const app = new Application();

// Initialise router
const router = new Router();


//Add CORS
app.use(oakCors());
app.use(router.routes());
app.use(router.allowedMethods());


// User Route
const userRoutes = UserRoutes(router);
app.use(userRoutes.routes());
app.use(userRoutes.allowedMethods());

//Room Routes
const roomRoutes = RoomRoutes(router)
app.use(roomRoutes.routes());
app.use(roomRoutes.allowedMethods());

//Server Routes
const serverRoutes = ServerRoutes(router)
app.use(serverRoutes.routes());
app.use(serverRoutes.allowedMethods());

const controller = new UserController();
controller.create({
    _id: {$oid: "5ef250d7001dafe600a781c3"},
    userName: "admin",
    firstName: "Max",
    lastName: "Mustermann",
    password: "admin",
    isTenant: false
})
    .catch((err) => console.log('SKIPPING ALREADY EXISTING INITIAL USER'))

console.log("🚀 Deno start! ");
await  app.listen({ port: 3001 });
