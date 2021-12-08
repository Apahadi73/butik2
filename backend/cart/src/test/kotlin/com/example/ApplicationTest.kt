package com.example

import com.example.plugins.configureRouting
import com.example.plugins.repository.Repo
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.After
import org.junit.Before
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import org.mockito.Mockito
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    private lateinit var kodein: Kodein
    private lateinit var repo: Repo

    @Before
    fun setup() {
        repo = Mockito.mock(Repo::class.java)
        kodein = Kodein {
            bind<Repo>() with singleton { repo }
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
}