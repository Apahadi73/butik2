package com.example

import com.example.plugins.database.Database
import com.example.plugins.database.DatabaseImpl
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
import org.mockito.Mock
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
    fun teardown() {}

//    val carts: List<Cart> = listOf<Cart>()
//    Mockito.`when`(db?.find()?.toList() ?: Unit).thenReturn(carts)

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
    fun `return empty list of cart`(): Unit = runBlocking {
        Mockito.`when`(db.find()).thenReturn(null)
        withTestApplication({ configureRouting(kodein) }) {
            handleRequest(HttpMethod.Get, "/api/v1/cart/list").apply {
                assertEquals(HttpStatusCode.NotFound, response.status())
                assertEquals("No cart found.", response.content)
            }
        }
    }
}