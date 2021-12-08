package com.example.plugins.repo

import RepoResult
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
            println("reached here")
            // update the cart if it already exists
            if (cart != null) {
                cart.contents[newCartItem.id] = newCartItem
                cart.total += newCartItem.price * newCartItem.quantity
                db.findOneAndReplace(Cart::cid eq cid, cart, FindOneAndReplaceOptions())
            }
            // or else create a new cart
            else {
                val total = newCartItem.price * newCartItem.quantity
                cart = Cart(cid, hashMapOf(newCartItem.id to newCartItem), total)
                db.insertOne(cart)
            }
            RepoResult.success("New Item successfully added to the cart.", cart)
        } catch (e: Exception) {
            RepoResult.error(e.localizedMessage, null)
        }
    }

    override suspend fun getCarts(): RepoResult<List<Cart>> {
        return try {
            val carts = db.find().toList()
            RepoResult.success("Fetched all carts", carts)
        } catch (e: Exception) {
            RepoResult.error("Failed to fetch carts.", null)
        }
    }

    override suspend fun getCartById(cid: String): RepoResult<Cart> {
        return try {
            val cart = db.findOne(Cart::cid eq cid)
            RepoResult.success("Fetched cart successfully.", cart)
        } catch (e: Exception) {
            RepoResult.error("Failed to fetch cart.", null)
        }
    }

    override suspend fun updateCartById(cid: String, updatedCartItem: CartItem): RepoResult<Cart> {
        TODO("Not yet implemented")
    }

//    override suspend fun updateCart(cartItem: CartItem): Cart {
//        TODO("update cart")
//    }

    override suspend fun removeItemFromCart(cartItem: CartItem): Cart {
        TODO("remove item from the cart")
    }

    override suspend fun deleteCart(uid: String): Boolean {
        TODO("delete whole cart")
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
