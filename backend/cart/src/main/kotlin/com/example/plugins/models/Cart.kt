package com.example.plugins.models

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class Cart(
    @BsonId
    val id: String,
    var items: HashMap<String, CartItem>? = null,
    var total: Double = 0.0
)

@Serializable
data class CartItem(
    @BsonId
    val id: String,
    val name: String? = "",
    val quantity: Int = 0,
    val price: Double = 0.0,
    val image: String = ""
)