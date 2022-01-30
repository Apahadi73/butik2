package com.example.plugins.database

import com.example.plugins.models.Cart
import io.ktor.application.*
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

class DatabaseImpl : Database {
    private var db: CoroutineCollection<Cart>? = null

    override suspend fun connect(environment: ApplicationEnvironment) {
        try {
            // initializes the MongoDB client
            val dbURL = environment.config.propertyOrNull("ktor.deployment.databaseURL")
                ?.getString() ?: "mongodb://localhost:27020"
            val dbClient = KMongo.createClient(dbURL).coroutine
            val database = dbClient.getDatabase("carts")
            db = database.getCollection()
            println("----------------------------------Connected to the database----------------------------------")
        } catch (e: Exception) {
            println(e.message)
        }
    }

    override suspend fun find(): List<Cart>? {
        return db?.find()?.toList()
    }

    override suspend fun findOne(cid: String): Cart? {
        return db?.findOne(Cart::cid eq cid)
    }

    override suspend fun findOneAndReplace(cid: String, cart: Cart): Cart? {
        return db?.findOneAndReplace(Cart::cid eq cid, cart)
    }

    override suspend fun insertOne(cart: Cart): Cart? {
        db?.insertOne(cart)
        return findOne(cart.cid)
    }

    override suspend fun deleteOne(cid: String) {
        db?.deleteOne(Cart::cid eq cid)
    }

    override suspend fun dropCollection() {
        db?.drop()
    }
}