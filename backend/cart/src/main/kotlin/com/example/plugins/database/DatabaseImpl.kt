package com.example.plugins.database

import com.example.plugins.models.Cart
import org.ehcache.Cache
import org.ehcache.CacheManagerBuilder
import org.ehcache.config.CacheConfigurationBuilder

class DatabaseImpl : Database {
    //    private var db: CoroutineCollection<Cart>? = null
    private var dbCache: Cache<String, Cart>

    init {
        /**
         * Creates a ehcache used for caching.
         */
        val cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true)

        /**
         * Ehcache used for caching the cart.
         */
        @Suppress("UNCHECKED_CAST")
        dbCache = cacheManager.createCache<String, Cart>(
            "carts",
            CacheConfigurationBuilder.newCacheConfigurationBuilder<String, Cart>()
                .buildConfig(Class.forName("java.lang.String") as Class<String>, Cart::class.java)
        )

        print("-----------------------Ehcache created-----------------------------")
    }

    override suspend fun updateCart(cid: String, cart: Cart): Cart? {
        dbCache.put(cid, cart)
        return getCartById(cid)
    }

    override suspend fun getCartById(cid: String): Cart? {
        return dbCache.get(cid)
    }

    override suspend fun deleteCart(cid: String): Boolean {
        dbCache.remove(cid)
        return dbCache.containsKey(cid)
    }

    override suspend fun dropCollection() {
        dbCache.drop(dbCache.count())
    }

    override suspend fun getAllCarts(): ArrayList<Cart> {
        val carts = ArrayList<Cart>()
        dbCache.forEach {
            carts.add(it.value)
        }
        return carts
    }


//    override suspend fun connect(environment: ApplicationEnvironment) {
//        try {
//            // initializes the MongoDB client
//            val databaseName = environment.config.propertyOrNull("ktor.deployment.dev.databaseName")
//                ?.getString() ?: "mongodb"
//            val databaseHost = environment.config.propertyOrNull("ktor.deployment.dev.databaseHost")
//                ?.getString() ?: "localhost"
//            val databasePort = environment.config.propertyOrNull("ktor.deployment.dev.databasePort")
//                ?.getString() ?: "27020"
//            val databaseURL = "${databaseName}://${databaseHost}:${databasePort}"
//            print("databaseURL: $databaseURL")
//
//            val dbClient = KMongo.createClient(databaseURL).coroutine
//
//
//            val database = dbClient.getDatabase("carts")
//            db = database.getCollection()
//        } catch (e: Exception) {
//            println(e.message)
//        }
//}

//    override suspend fun find(): List<Cart>? {
//
//        return db.find().toList()
//    }

//    override suspend fun getCartById(cid: String): Cart? {
//        return dbCache.get(cid)
////        return db.findOne(Cart::cid eq cid)
//    }

//    override suspend fun findOneAndReplace(cid: String, cart: Cart): Cart? {
//        val exists = dbCache.containsKey(cid)
//        if (exists) {
//            dbCache.replace(cid, cart)
//        }
//        return findOne(cid)
////        return db.findOneAndReplace(Cart::cid eq cid, cart)
//    }

//    override suspend fun insertOne(cart: Cart): Cart? {
////        db.insertOne(cart)
//        return findOne(cart.cid)
//    }

//    override suspend fun deleteOne(cid: String) {
//        db.deleteOne(Cart::cid eq cid)
//    }


}