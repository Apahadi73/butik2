package com.example.plugins.database

import com.example.plugins.models.Cart
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

interface Database {
    suspend fun connect()

    suspend fun find(): List<Cart>?
    suspend fun findOne(cid: String): Cart?
    suspend fun findOneAndReplace(cid: String, cart: Cart): Cart?

    suspend fun insertOne(cart: Cart): Cart?

    suspend fun deleteOne(cid: String)
    suspend fun dropCollection()
}

fun initDB(kodein: Kodein) {
    val db: Database by kodein.instance()
    CoroutineScope(Dispatchers.Default).launch { // context of the parent, main runBlocking coroutine
        db.connect()
    }
}