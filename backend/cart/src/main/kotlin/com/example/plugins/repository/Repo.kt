package com.example.plugins.repository

import RepoResult
import com.example.plugins.models.Cart
import com.example.plugins.models.CartItem

interface Repo {
    suspend fun connect()
    suspend fun addNewItemToCart(cid: String, newCartItem: CartItem): RepoResult<Cart>

    suspend fun getCarts(): RepoResult<List<Cart>>
    suspend fun getCartById(cid: String): RepoResult<Cart>

    suspend fun updateCartById(cid: String, updatedCartItem: CartItem): RepoResult<Cart>

    suspend fun removeItemFromCart(cid: String, id: Int): RepoResult<Cart>
    suspend fun deleteCartById(cid: String): RepoResult<Unit>
    suspend fun removeAllCarts(): RepoResult<Unit>
}