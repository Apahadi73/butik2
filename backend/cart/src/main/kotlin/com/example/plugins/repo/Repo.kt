package com.example.plugins.repo

import com.example.plugins.models.Cart
import com.example.plugins.models.CartItem

interface Repo {
    suspend fun addNewItemToCart(cartItem: CartItem): Cart
    suspend fun removeItemFromCart(cartItem: CartItem): Cart
    suspend fun deleteCart(uid: String): Boolean
    suspend fun updateCart(cartItem: CartItem): Cart
}