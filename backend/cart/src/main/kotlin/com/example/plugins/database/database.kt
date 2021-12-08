package com.example.plugins.database

import com.example.plugins.models.Cart
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun initDb(): CoroutineCollection<Cart>? {
    try {
        // initializes the MongoDB client
        val dbClient = KMongo.createClient("mongodb://localhost:27020").coroutine
        val database = dbClient.getDatabase("carts")
        return database.getCollection()
    } catch (e: Exception) {
        println(e.message)
    }
    return null
}