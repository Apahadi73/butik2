package com.example

import com.example.plugins.configureSerialization
import com.example.plugins.database.Database
import com.example.plugins.database.cartItems
import com.example.plugins.repository.ApiService
import com.example.plugins.repository.ApiServiceImpl
import com.example.plugins.routes.configureRouting
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import org.mockito.Mockito
import org.mockito.Mockito.mock
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    private lateinit var kodein: Kodein

    private val db: Database = mock(Database::class.java)

    @Before
    fun setup() {
        kodein = Kodein {
            bind<Database>() with singleton { db }
            bind<ApiService>() with singleton { ApiServiceImpl(kodein) }
        }
    }

    @After
    fun teardown() {
    }

    @Test
    fun `test welcome page`() {
        withTestApplication({ configureRouting(kodein) }) {
            handleRequest(HttpMethod.Get, "/api/v1/cart").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Welcome to the cart service version 1!", response.content)
            }
        }
    }

    @Test
    fun `get an empty list of cart`(): Unit = runBlocking {
        Mockito.`when`(db.getAllCarts()).thenReturn(null)
        withTestApplication({ configureRouting(kodein) }) {
            handleRequest(HttpMethod.Get, "/api/v1/cart/list").apply {
                assertEquals(HttpStatusCode.NotFound, response.status())
                assertEquals("No cart found.", response.content)
            }
        }
    }

    @Test
    fun `get a list of carts`(): Unit = runBlocking {
        Mockito.`when`(db.getAllCarts()).thenReturn(cartItems)
        withTestApplication({ configureRouting(kodein); configureSerialization() }) {
            handleRequest(HttpMethod.Get, "/api/v1/cart/list").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun `get a cart with invalid id`(): Unit = runBlocking {
        Mockito.`when`(db.getCartById("1")).thenReturn(null)
        withTestApplication({ configureRouting(kodein); configureSerialization() }) {
            handleRequest(HttpMethod.Get, "/api/v1/cart/1").apply {
                assertEquals(HttpStatusCode.BadRequest, response.status())
            }
        }
    }

    @Test
    fun `get a cart by id`(): Unit = runBlocking {
        Mockito.`when`(db.getCartById("1")).thenReturn(cartItems[1])
        withTestApplication({ configureRouting(kodein); configureSerialization() }) {
            handleRequest(HttpMethod.Get, "/api/v1/cart/1").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

//    @Test
//    fun `add a new item to a cart`(): Unit = runBlocking {
//        Mockito.`when`(db.findOne("1")).thenReturn(cartItems[1])
//        withTestApplication({ configureRouting(kodein); configureSerialization()  }) {
//            with(handleRequest(HttpMethod.Post, "/api/v1/cart/1") {
//                addHeader(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
//                setBody(
//                    Json.encodeToString(CartItem(
//                        1,
//                        "Camera",
//                        1,
//                        354.23,
//                        "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
//                    ),)
//                )
//            }) {
//                assertEquals(HttpStatusCode.OK, response.status())
//            }
//        }
//    }


    @Test
    fun `delete a cart by id`(): Unit = runBlocking {
        withTestApplication({ configureRouting(kodein); configureSerialization() }) {
            handleRequest(HttpMethod.Delete, "/api/v1/cart/1").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Successfully deleted cart.", response.content)
            }
        }
    }

    @Test
    fun `delete an invalid item from a cart`(): Unit = runBlocking {
        Mockito.`when`(db.getCartById("1")).thenReturn(cartItems[1])
        withTestApplication({ configureRouting(kodein); configureSerialization() }) {
            handleRequest(HttpMethod.Delete, "/api/v1/cart/1/-1").apply {
                assertEquals(HttpStatusCode.BadRequest, response.status())
                assertEquals("No such item found in the cart.", response.content)
            }
        }
    }

    @Test
    fun `delete an item from an invalid cart`(): Unit = runBlocking {
        Mockito.`when`(db.getCartById("11")).thenReturn(null)
        withTestApplication({ configureRouting(kodein); configureSerialization() }) {
            handleRequest(HttpMethod.Delete, "/api/v1/cart/11/1").apply {
                assertEquals(HttpStatusCode.BadRequest, response.status())
                assertEquals("No cart found.", response.content)
            }
        }
    }

    @Test
    fun `delete an item from a cart`(): Unit = runBlocking {
        Mockito.`when`(db.getCartById("1")).thenReturn(cartItems[1])
        withTestApplication({ configureRouting(kodein); configureSerialization() }) {
            handleRequest(HttpMethod.Delete, "/api/v1/cart/1/1").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun `remove all the carts`(): Unit = runBlocking {
        Mockito.`when`(db.dropCollection()).thenReturn(null)
        withTestApplication({ configureRouting(kodein); configureSerialization() }) {
            handleRequest(HttpMethod.Delete, "/api/v1/cart/list").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Successfully deleted all carts.", response.content)
            }
        }
    }
}