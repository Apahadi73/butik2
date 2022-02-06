package com.example.plugins.database

import com.example.plugins.models.Cart
import com.example.plugins.models.CartItem


// stores cart items
val cartItems: List<Cart> = mutableListOf<Cart>(
    Cart(
        "1", hashMapOf(
            "1" to CartItem(
                "1",
                "Camera",
                1,
                354.23,
                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
            ),
            "2" to CartItem(
                "2",
                "Camera",
                1,
                354.23,
                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
            ),
            "3" to CartItem(
                "3",
                "Camera",
                1,
                354.23,
                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
            )
        ),
        1023.21
    ),
    Cart(
        "2", hashMapOf(
            "1" to CartItem(
                "1",
                "Camera",
                1,
                354.23,
                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
            ),
            "2" to CartItem(
                "2",
                "Camera",
                1,
                354.23,
                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
            ),
            "3" to CartItem(
                "3",
                "Camera",
                1,
                354.23,
                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
            )
        ),
        2433.21
    )
)
