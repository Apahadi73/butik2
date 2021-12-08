package com.example.plugins.repo

import RepoResult
import com.example.plugins.models.Cart
import com.example.plugins.models.CartItem

interface Repo {
    suspend fun connect()
    suspend fun addNewItemToCart(cid: String, newCartItem: CartItem): RepoResult<Cart>

    suspend fun getCarts(): RepoResult<List<Cart>>
    suspend fun getCartById(cid: String): RepoResult<Cart>

    suspend fun updateCartById(cid: String, updatedCartItem: CartItem): RepoResult<Cart>

    suspend fun removeItemFromCart(cartItem: CartItem): Cart
    suspend fun deleteCart(uid: String): Boolean
    suspend fun removeAllCarts(): RepoResult<Unit>
}