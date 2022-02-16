package com.example.plugins.repository

import RepoResult
import com.example.plugins.models.Cart
import com.example.plugins.models.CartItem

interface ApiService {
    //    suspend fun connect()
    suspend fun addNewItemToCart(cid: String, newCartItem: CartItem): RepoResult<Cart>
    suspend fun createNewCart(cid: String): RepoResult<Cart>

    suspend fun getCarts(): RepoResult<List<Cart>>
    suspend fun getCartById(cid: String): RepoResult<Cart>

    suspend fun updateCartById(cid: String, updatedCart: Cart): RepoResult<Cart>

    suspend fun removeItemFromCart(cid: String, id: String): RepoResult<Cart>
    suspend fun deleteCartById(cid: String): RepoResult<Unit>
    suspend fun removeAllCarts(): RepoResult<Unit>
}