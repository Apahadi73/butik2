package com.example

import com.example.plugins.database.initDb
import com.example.plugins.models.Cart
import com.example.plugins.repository.ApiServiceImpl
import com.example.plugins.repository.Repo
import com.example.plugins.routes.configureRouting
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.After
import org.junit.Before
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import org.litote.kmongo.coroutine.CoroutineCollection
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    private lateinit var kodein: Kodein
    private var db: CoroutineCollection<Cart>? = null

    @Before
    fun setup() {
        db = initDb()
        kodein = Kodein {
            bind<Repo>() with singleton { ApiServiceImpl(db) }
        }
    }

    @After
    fun teardown() {
    }

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
}