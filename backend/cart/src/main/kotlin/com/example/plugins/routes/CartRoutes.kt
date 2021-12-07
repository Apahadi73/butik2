package com.example.plugins

import com.example.plugins.models.CartItem
import com.example.plugins.models.cartItems
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {

    routing {
        cartRouting()
    }
}

fun Route.cartRouting() {
    route("/api/v1/cart") {
        get("") {
            call.respond(HttpStatusCode(200, "OK"), "Welcome to the cart service version 1!")
        }
        get("{uid}") {
            // check for the id params in the api end point
            val uid = call.parameters["uid"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            if (cartItems.isNotEmpty()) {
                val cart = cartItems.filter { cart -> cart.uid == uid }
                call.respond(HttpStatusCode(200, "OK"), cart)
            } else {
                call.respondText("No cart items found", status = HttpStatusCode.NotFound)
            }
        }

        post("{uid}") {
            val uid = call.parameters["uid"] ?: return@post call.respondText(
                "Missing uid",
                status = HttpStatusCode.BadRequest
            )
            val newCartItem = call.receive<CartItem>()
            cartItems.forEach {
                if (it.uid == uid) {
                    it.contents += newCartItem
                }
            }
            call.respondText("Item added to the cart correctly", status = HttpStatusCode.Created)
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
            cartItems.forEach { cart ->
                if (cart.uid == uid) {
//                    cart.contents.map { item ->
//                        if (item.id == id) {
//                            item = updatedItem
//                        }
//                    }
//                    TODO: work here
                }
            }
            call.respondText("Item added to the cart correctly", status = HttpStatusCode.Created)
        }

        delete("{uid}") {
            val uid = call.parameters["uid"] ?: return@delete call.respondText(
                "Missing uid",
                status = HttpStatusCode.BadRequest
            )
            cartItems.removeIf { it.uid == uid }
            call.respondText("All items removed successfully from the cart", status = HttpStatusCode.Created)
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
            cartItems.forEach { cart ->
                if (cart.uid == uid) {
                    cart.contents = cart.contents.filter { it.id != id }
                }
            }
            call.respondText("Item removed successfully from the cart", status = HttpStatusCode.Created)
        }
    }
}
