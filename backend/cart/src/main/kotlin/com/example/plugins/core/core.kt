package com.example.plugins.core

import com.example.plugins.models.Cart
import java.math.RoundingMode
import java.text.DecimalFormat

fun calculateCartTotal(cart: Cart): Double {
    var total: Double = 0.0
    cart.items.forEach { (k, v) ->
        total += v.quantity * v.price
    }
    val df = DecimalFormat("#.##")
    df.roundingMode = RoundingMode.CEILING

    return df.format(total).toDouble()
}