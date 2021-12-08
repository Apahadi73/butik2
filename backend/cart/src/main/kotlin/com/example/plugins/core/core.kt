package com.example.plugins.core

import com.example.plugins.models.Cart

fun calculateCartTotal(cart: Cart): Double {
    var total: Double = 0.0
    cart.items.forEach { (k, v) ->
        total += v.quantity * v.price
    }
    return total
}