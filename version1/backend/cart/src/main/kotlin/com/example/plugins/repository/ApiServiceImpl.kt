package com.example.plugins.repository

import RepoResult
import com.example.plugins.core.calculateCartTotal
import com.example.plugins.database.Database
import com.example.plugins.models.Cart
import com.example.plugins.models.CartItem
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class ApiServiceImpl(kodein: Kodein) : ApiService {
    private val db: Database by kodein.instance()

    override suspend fun addNewItemToCart(cid: String, newCartItem: CartItem): RepoResult<Cart> {
        return try {
            val total = newCartItem.price * newCartItem.quantity
            val cart = Cart(cid, hashMapOf(newCartItem.id to newCartItem), total)
            db.updateCart(cid, cart)
            RepoResult.success("Cart updated successfully.", cart)
        } catch (e: Exception) {
            RepoResult.error(e.localizedMessage, null)
        }
    }

    override suspend fun createNewCart(cid: String): RepoResult<Cart> {
        try {
            val alreadyExistingCart = db.getCartById(cid)
            if (alreadyExistingCart != null) {
                return RepoResult.error("Cart Already exists.", null)
            }
            val cart = db.createNewCart(cid)
            return if (cart == null) {
                RepoResult.error("No cart found.", null)
            } else {
                RepoResult.success("Successfully created a cart.", cart)
            }
        } catch (e: Exception) {
            return RepoResult.error(e.localizedMessage, null)
        }
    }

    override suspend fun getCarts(): RepoResult<List<Cart>> {
        return try {
            val carts = db.getAllCarts()
            if (carts == null) {
                RepoResult.error("No cart found.", null)
            } else {
                RepoResult.success("Fetched all carts", carts)
            }
        } catch (e: Exception) {
            RepoResult.error(e.localizedMessage, null)
        }
    }

    override suspend fun getCartById(cid: String): RepoResult<Cart> {
        return try {
            val cart = db.getCartById(cid)
            if (cart == null) {
                RepoResult.error("No such cart found.", null)
            } else {
                RepoResult.success("Fetched cart successfully.", cart)
            }
        } catch (e: Exception) {
            RepoResult.error(e.localizedMessage, null)
        }
    }

    override suspend fun updateCartById(cid: String, updatedCart: Cart): RepoResult<Cart> {
        return try {
            db.updateCart(cid, updatedCart)
            print("update: $updatedCart")
            val insertedCart = getCartById(cid).data
            print("update: $insertedCart")
            RepoResult.success("Item successfully updated in the cart.", insertedCart)
        } catch (e: Exception) {
            RepoResult.error(e.localizedMessage, null)
        }
    }

    override suspend fun deleteCartById(cid: String): RepoResult<Unit> {
        return try {
            db.deleteCart(cid)
            RepoResult.success("Successfully deleted cart.", null)
        } catch (e: Exception) {
            RepoResult.error(e.localizedMessage, null)
        }
    }

    override suspend fun removeItemFromCart(cid: String, id: String): RepoResult<Cart> {
        return try {
            val cart = db.getCartById(cid)
            if (cart != null) {
                if (cart.items != null && cart.items!!.containsKey(id)) {
                    cart.items?.remove(id)
                    cart.total = calculateCartTotal(cart)

                    db.updateCart(cid, cart)
                    RepoResult.success("Item successfully removed from the cart.", cart)
                } else {
                    RepoResult.error("No such item found in the cart.", null)
                }
            }
            // or else create a new cart
            else {
                RepoResult.error("No cart found.", null)
            }
        } catch (e: Exception) {
            RepoResult.error(e.localizedMessage, null)
        }
    }

    override suspend fun removeAllCarts(): RepoResult<Unit> {
        return try {
            db.dropCollection()
            RepoResult.success("Successfully deleted all carts.", null)
        } catch (e: Exception) {
            RepoResult.error("Failed to delete all carts.", null)
        }
    }
}
