package com.example.plugins

import com.example.plugins.models.CartItem
import com.example.plugins.models.cartItems
import com.example.plugins.repo.Repo
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

fun Application.configureRouting(kodein: Kodein) {
    val db: Repo by kodein.instance()
    routing {
        cartRouting(db)
    }
}

fun Route.cartRouting(db: Repo) {
    route("/api/v1/cart") {
        get("") {
            db.connect()
            call.respond(HttpStatusCode(200, "OK"), "Welcome to the cart service version 1!")
        }
        get("{uid}") {
            // check for the id params in the api end point
            val uid = call.parameters["uid"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            if (cartItems.isNotEmpty()) {
                val cart = cartItems[uid.toInt()]
                if (cart != null) {
                    call.respond(HttpStatusCode(200, "OK"), cart)
                } else {
                    call.respondText("No cart items found", status = HttpStatusCode.NotFound)
                }
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

            if (cartItems.isNotEmpty()) {
                val cart = cartItems[uid.toInt()]
                if (cart != null) {
                    cart.contents[newCartItem.id] = newCartItem
                    call.respond(HttpStatusCode(200, "OK"), cart)
                } else {
                    call.respondText("Failed to add item to the cart", status = HttpStatusCode.InternalServerError)
                }
            } else {
                call.respondText("Failed to add item to the cart", status = HttpStatusCode.InternalServerError)
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
            val cart = cartItems[uid.toInt()]
            if (cart != null) {
                cart.contents[updatedItem.id] = updatedItem
            }
            call.respondText("Item added to the cart correctly", status = HttpStatusCode.OK)
        }

        delete("{uid}") {
            val uid = call.parameters["uid"] ?: return@delete call.respondText(
                "Missing uid",
                status = HttpStatusCode.BadRequest
            )
            cartItems.remove(uid.toInt())
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
//            cartItems[uid].contents.filter { it.id != id }
            call.respondText("Item removed successfully from the cart", status = HttpStatusCode.Created)
        }
    }
}
