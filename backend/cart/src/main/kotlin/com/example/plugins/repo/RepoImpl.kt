package com.example.plugins.repo

import com.example.plugins.models.Cart
import com.example.plugins.models.CartItem

class RepoImpl : Repo {
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
