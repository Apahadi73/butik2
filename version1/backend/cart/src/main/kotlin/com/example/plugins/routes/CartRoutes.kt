package com.example.plugins.routes

import Status
import com.example.plugins.models.Cart
import com.example.plugins.repository.ApiService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

fun Application.configureRouting(kodein: Kodein) {
    val apiService: ApiService by kodein.instance()
    routing {
        cartRouting(apiService)
    }
}

fun Route.cartRouting(apiService: ApiService) {
    route("/api/v1/cart") {
        get("") {
            call.respond(status = HttpStatusCode.OK, "Welcome to the cart service version 1!")
        }

        get("/list") {
            val result = apiService.getCarts()
            if (result.status == Status.SUCCESS) {
                call.respond(HttpStatusCode(200, "OK"), result.data!!)
            } else {
                call.respondText(result.message, status = HttpStatusCode.NotFound)
            }
        }

        get("{cid}") {
            val cid = call.parameters["cid"] ?: return@get call.respondText(
                "Missing cid",
                status = HttpStatusCode.BadRequest
            )
            val result = apiService.getCartById(cid)
            if (result.status == Status.SUCCESS) {
                call.respond(HttpStatusCode(200, "OK"), result.data!!)
            } else {
                call.respondText(result.message, status = HttpStatusCode.BadRequest)
            }
        }

        post("{cid}") {
            val cid = call.parameters["cid"] ?: return@post call.respondText(
                "Missing cid",
                status = HttpStatusCode.BadRequest
            )
            val result = apiService.createNewCart(cid)
            if (result.status == Status.SUCCESS) {
                call.respond(HttpStatusCode(200, "OK"), result.data!!)
            } else {
                call.respondText(result.message, status = HttpStatusCode.BadRequest)
            }
        }

        put("{cid}") {
            val cid = call.parameters["cid"] ?: return@put call.respondText(
                "Missing cid",
                status = HttpStatusCode.BadRequest
            )
            val updatedCart = call.receive<Cart>()
            val result = apiService.updateCartById(cid, updatedCart)
            if (result.status == Status.SUCCESS) {
                if (result.data != null) {
                    call.respond(HttpStatusCode(200, "OK"), result.data!!)
                } else {
                    call.respond(HttpStatusCode(200, "OK"), "No data")

                }
            } else {
                call.respondText(result.message, status = HttpStatusCode.BadRequest)
            }
        }

        delete("{cid}") {
            val cid = call.parameters["cid"] ?: return@delete call.respondText(
                "Missing cid",
                status = HttpStatusCode.BadRequest
            )

            val result = apiService.deleteCartById(cid)
            if (result.status == Status.SUCCESS) {
                call.respond(HttpStatusCode(200, "OK"), result.message)
            } else {
                call.respondText(result.message, status = HttpStatusCode.BadRequest)
            }
        }

        delete("{cid}/{id}") {
            val cid = call.parameters["cid"] ?: return@delete call.respondText(
                "Missing cid",
                status = HttpStatusCode.BadRequest
            )
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val result = apiService.removeItemFromCart(cid, id)
            if (result.status == Status.SUCCESS) {
                call.respond(HttpStatusCode(200, "OK"), result.data!!)
            } else {
                call.respondText(result.message, status = HttpStatusCode.BadRequest)
            }
        }

        delete("/list") {
            val result = apiService.removeAllCarts()
            if (result.status == Status.SUCCESS) {
                call.respondText(result.message, status = HttpStatusCode.OK)
            } else {
                call.respondText(result.message, status = HttpStatusCode.BadRequest)
            }
        }
    }
}
