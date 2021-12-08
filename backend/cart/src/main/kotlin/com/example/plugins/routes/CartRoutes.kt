package com.example.plugins

import Status
import com.example.plugins.models.CartItem
import com.example.plugins.repo.Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

fun Application.configureRouting(kodein: Kodein) {
    val repo: Repo by kodein.instance()
    routing {
        cartRouting(repo)
    }
}

fun Route.cartRouting(repo: Repo) {
    route("/api/v1/cart") {
        get("") {
            repo.connect()
            call.respond(HttpStatusCode(200, "OK"), "Welcome to the cart service version 1!")
        }

        get("/list") {
            val result = repo.getCarts()
            if (result.status == Status.SUCCESS) {
                call.respond(HttpStatusCode(200, "OK"), result.data!!)
            } else {
                call.respondText(result.message, status = HttpStatusCode.OK)
            }
        }

        get("{cid}") {
            val cid = call.parameters["cid"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val result = repo.getCartById(cid)
            if (result.status == Status.SUCCESS) {
                call.respond(HttpStatusCode(200, "OK"), result.data!!)
            } else {
                call.respondText(result.message, status = HttpStatusCode.OK)
            }
        }

        post("{cid}") {
            val cid = call.parameters["cid"] ?: return@post call.respondText(
                "Missing uid",
                status = HttpStatusCode.BadRequest
            )
            val newCartItem = call.receive<CartItem>()
            val result = repo.addNewItemToCart(cid, newCartItem)
            if (result.status == Status.SUCCESS) {
                call.respond(HttpStatusCode(200, "OK"), result.data!!)
            } else {
                call.respondText(result.message, status = HttpStatusCode.OK)
            }
        }

        put("{uid}/{id}") {
            val uid = call.parameters["uid"] ?: return@put call.respondText(
                "Missing uid",
                status = HttpStatusCode.BadRequest
            )
            val id = call.parameters["id"] ?: return@put call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val updatedItem = call.receive<CartItem>()
//            val cart = cartItems[uid.toInt()]
//            if (cart != null) {
//                cart.contents[updatedItem.id] = updatedItem
//            }
            call.respondText("Item added to the cart correctly", status = HttpStatusCode.OK)
        }

        delete("{uid}") {
            val uid = call.parameters["uid"] ?: return@delete call.respondText(
                "Missing uid",
                status = HttpStatusCode.BadRequest
            )
//            cartItems.remove(uid.toInt())
            call.respondText("All items removed successfully from the cart", status = HttpStatusCode.OK)
        }

        delete("{uid}/{id}") {
            val uid = call.parameters["uid"] ?: return@delete call.respondText(
                "Missing uid",
                status = HttpStatusCode.BadRequest
            )
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            call.respondText("Item removed successfully from the cart", status = HttpStatusCode.OK)
        }

        //----------------------------------------------developer routes------------------------------------------------


        delete("/list") {
            val result = repo.removeAllCarts()
            if (result.status == Status.SUCCESS) {
                call.respondText(result.message, status = HttpStatusCode.OK)
            } else {
                call.respondText(result.message, status = HttpStatusCode.OK)
            }
        }
    }
}
