package com.example.plugins.models

import kotlinx.serialization.Serializable

@Serializable
data class Cart(val uid: String, var contents: List<CartItem>)

@Serializable
data class CartItem(val id: String, val name: String, val quantity: Int, val price: Double, val image: String)

// stores cart items
val cartItems = mutableListOf<Cart>(
    Cart(
        "1", listOf(
            CartItem(
                "1",
                "Camera",
                1,
                354.23,
                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
            ),
            CartItem(
                "1",
                "Camera",
                1,
                354.23,
                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
            ),
            CartItem(
                "1",
                "Camera",
                1,
                354.23,
                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
            )
        )
    ),
    Cart(
        "2", listOf(
            CartItem(
                "1",
                "Camera",
                1,
                354.23,
                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
            ),
            CartItem(
                "1",
                "Camera",
                1,
                354.23,
                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
            ),
            CartItem(
                "1",
                "Camera",
                1,
                354.23,
                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
            )
        )
    )
)