package com.example.core

import com.example.plugins.core.calculateCartTotal
import com.example.plugins.models.Cart
import com.example.plugins.models.CartItem
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CoreTest {

    private lateinit var cart: Cart

    @Before
    fun setup() {
        cart = Cart(
            "1", hashMapOf(
                1 to CartItem(
                    1,
                    "Camera",
                    1,
                    100.0,
                    "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
                ),
                2 to CartItem(
                    2,
                    "Camera",
                    1,
                    100.0,
                    "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
                ),
                3 to CartItem(
                    3,
                    "Camera",
                    2,
                    100.0,
                    "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
                )
            ),
            400.0
        )
    }

    @After
    fun teardown() {
    }

    @Test
    fun `calculate cart total correctly`() {
        val total = calculateCartTotal(cart)
        assertEquals(total, 400.0)
    }
}