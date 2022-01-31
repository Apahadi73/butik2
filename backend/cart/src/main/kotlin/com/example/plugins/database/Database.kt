package com.example.plugins.database

import com.example.plugins.models.Cart
import io.ktor.application.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

interface Database {
//    suspend fun connect(environment: ApplicationEnvironment)

    suspend fun getCartById(cid: String): Cart?
    suspend fun updateCart(cid: String, cart: Cart): Cart?
    suspend fun deleteCart(cid: String): Boolean
    suspend fun getAllCarts(): ArrayList<Cart>
    suspend fun dropCollection()
}

fun initDB(kodein: Kodein, environment: ApplicationEnvironment) {
    val db: Database by kodein.instance()
    CoroutineScope(Dispatchers.Default).launch { // context of the parent, main runBlocking coroutine
//        db.connect(environment)
    }
}