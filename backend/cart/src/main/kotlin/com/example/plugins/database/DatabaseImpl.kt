package com.example.plugins.database

import com.example.plugins.models.Cart
import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo


class DatabaseImpl : Database {
    private var db_collection: CoroutineCollection<Cart>? = null

    init {
        val connectionString =
            ConnectionString("mongodb+srv://admin:aruna@cluster0.zlpob.mongodb.net/butik?retryWrites=true&w=majority&connectTimeOutMS=300000&socketTimeOutMS=300000")
        val settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .build()
        val mongoClient = KMongo.createClient(connectionString)
        val database = mongoClient.getDatabase("carts").coroutine
        db_collection = database.getCollection()
        if (db_collection != null) {
            print("connected to mongodb")
        }
    }


    override suspend fun getCartById(cid: String): Cart? {
        return db_collection?.findOneById(cid)
    }

    override suspend fun createNewCart(cid: String): Cart? {
        db_collection?.insertOne(Cart(cid))
        return getCartById(cid)
    }

    override suspend fun updateCart(cid: String, cart: Cart): Cart? {
        db_collection?.updateOne(
            Cart::id eq cid,
            cart,
        )

        return getCartById(cid)
    }


    override suspend fun deleteCart(cid: String): Boolean {
        db_collection?.deleteOneById(cid)
        return db_collection?.findOneById(cid) == null
    }

    override suspend fun dropCollection() {
        db_collection?.drop()
    }

    override suspend fun getAllCarts(): List<Cart>? {
        return db_collection?.find()?.toList()
    }

}