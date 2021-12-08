package com.example.plugins.repo

import RepoResult
import com.example.plugins.core.calculateCartTotal
import com.example.plugins.database.initDb
import com.example.plugins.models.Cart
import com.example.plugins.models.CartItem
import com.mongodb.client.model.FindOneAndReplaceOptions
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.eq

class RepoImpl : Repo {
    private lateinit var db: CoroutineCollection<Cart>

    init {
        val connection = initDb()
        if (connection != null) {
            db = connection
            println("----------------------------------Connected to the database----------------------------------")
        } else {
            println("---------------------Failed to connect to the database---------------------")
        }
    }

    override suspend fun connect() {
//      TODO: Don't forget to remove the following expression
//        seedDummyCarts(db)
        print("Connected to the database")
    }

    override suspend fun addNewItemToCart(cid: String, newCartItem: CartItem): RepoResult<Cart> {
        return try {
            var cart = db.findOne(Cart::cid eq cid)
            // update the cart if it already exists
            if (cart != null) {
                if (cart.items.containsKey(newCartItem.id)) {
                    RepoResult.error("Item already added to the cart.", null)
                } else {
                    cart.items[newCartItem.id] = newCartItem
                    cart.total = calculateCartTotal(cart)
                    db.findOneAndReplace(Cart::cid eq cid, cart, FindOneAndReplaceOptions())
                    RepoResult.success("New Item successfully added to the cart.", cart)
                }
            }
            // or else create a new cart
            else {
                val total = newCartItem.price * newCartItem.quantity
                cart = Cart(cid, hashMapOf(newCartItem.id to newCartItem), total)
                db.insertOne(cart)
                RepoResult.success("New Item successfully added to the cart.", cart)
            }
        } catch (e: Exception) {
            RepoResult.error(e.localizedMessage, null)
        }
    }

    override suspend fun getCarts(): RepoResult<List<Cart>> {
        return try {
            val carts = db.find().toList()
            RepoResult.success("Fetched all carts", carts)
        } catch (e: Exception) {
            RepoResult.error(e.localizedMessage, null)
        }
    }

    override suspend fun getCartById(cid: String): RepoResult<Cart> {
        return try {
            val cart = db.findOne(Cart::cid eq cid)
            if (cart == null) {
                RepoResult.error("No such cart found.", null)
            } else {
                RepoResult.success("Fetched cart successfully.", cart)
            }
        } catch (e: Exception) {
            RepoResult.error(e.localizedMessage, null)
        }
    }

    override suspend fun updateCartById(cid: String, updatedCartItem: CartItem): RepoResult<Cart> {
        return try {
            val cart = db.findOne(Cart::cid eq cid)
            // update the cart if it already exists
            if (cart != null) {
                cart.items[updatedCartItem.id] = updatedCartItem
                cart.total = calculateCartTotal(cart)
                db.findOneAndReplace(Cart::cid eq cid, cart, FindOneAndReplaceOptions())
                RepoResult.success("Item successfully updated in the cart.", cart)
            }
            // or else create a new cart
            else {
                RepoResult.error("No cart found.", null)
            }
        } catch (e: Exception) {
            RepoResult.error(e.localizedMessage, null)
        }
    }

    override suspend fun deleteCartById(cid: String): RepoResult<Unit> {
        return try {
            db.deleteOne(Cart::cid eq cid)
            RepoResult.success("Successfully deleted cart.", null)
        } catch (e: Exception) {
            RepoResult.error(e.localizedMessage, null)
        }
    }

    override suspend fun removeItemFromCart(cid: String, id: Int): RepoResult<Cart> {
        return try {
            val cart = db.findOne(Cart::cid eq cid)
            if (cart != null) {
                if (cart.items.containsKey(id)) {
                    cart.items.remove(id)
                    cart.total = calculateCartTotal(cart)
                    db.findOneAndReplace(Cart::cid eq cid, cart, FindOneAndReplaceOptions())
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
            db.drop()
            RepoResult.success("Successfully deleted all carts.", null)
        } catch (e: Exception) {
            RepoResult.error("Failed to delete all carts.", null)
        }
    }
}
