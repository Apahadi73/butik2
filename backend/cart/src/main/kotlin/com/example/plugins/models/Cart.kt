package com.example.plugins.models

import kotlinx.serialization.Serializable

@Serializable
data class Cart(val cid: String, var items: HashMap<Int, CartItem>, var total: Double)

@Serializable
data class CartItem(val id: Int, val name: String, val quantity: Int, val price: Double, val image: String)
//
//// stores cart items
//val cartItems: HashMap<Int, Cart> = hashMapOf(
//    1 to Cart(
//        "1", hashMapOf(
//            1 to CartItem(
//                1,
//                "Camera",
//                1,
//                354.23,
//                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
//            ),
//            2 to CartItem(
//                2,
//                "Camera",
//                1,
//                354.23,
//                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
//            ),
//            3 to CartItem(
//                3,
//                "Camera",
//                1,
//                354.23,
//                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
//            )
//        )
//    ),
//    2 to Cart(
//        "2", hashMapOf(
//            1 to CartItem(
//                1,
//                "Camera",
//                1,
//                354.23,
//                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
//            ),
//            2 to CartItem(
//                2,
//                "Camera",
//                1,
//                354.23,
//                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
//            ),
//            3 to CartItem(
//                3,
//                "Camera",
//                1,
//                354.23,
//                "https://cdn.pixabay.com/photo/2016/01/09/18/27/camera-1130731_960_720.jpg"
//            )
//        )
//    )
//)