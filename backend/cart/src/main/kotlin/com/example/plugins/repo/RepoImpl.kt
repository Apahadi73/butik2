package com.example.plugins.repo

import com.example.plugins.database.initDb
import com.example.plugins.models.Cart
import com.example.plugins.models.CartItem
import org.litote.kmongo.coroutine.CoroutineCollection

class RepoImpl : Repo {
    lateinit var db: CoroutineCollection<Cart>

    init {
        val connection = initDb()
        if (connection != null) {
            db = connection
            print("Connected to the database")
        } else {
            print("Failed to connect to the database")
        }
    }

    override suspend fun connect() {
        print("Connected to the database")
    }

    override suspend fun addNewItemToCart(cartItem: CartItem): Cart {
        TODO("Adds new item to the cart")
    }

    override suspend fun removeItemFromCart(cartItem: CartItem): Cart {
        TODO("remove item from the cart")
    }

    override suspend fun deleteCart(uid: String): Boolean {
        TODO("delete whole cart")
    }

    override suspend fun updateCart(cartItem: CartItem): Cart {
        TODO("update cart")
    }
}
