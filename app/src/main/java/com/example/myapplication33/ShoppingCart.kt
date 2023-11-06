package com.example.myapplication33

class ShoppingCart {
    val cartItems = mutableListOf<CartItem>()

    fun addItem(item: CartItem) {
        // Check if the item is already in the cart
        val existingCartItem = cartItems.find { it.name == item.name }
        if (existingCartItem != null) {
            existingCartItem.quantity++
        } else {
            cartItems.add(item)
        }
    }

    fun removeItem(item: CartItem) {
        val existingCartItem = cartItems.find { it.name == item.name }
        if (existingCartItem != null) {
            if (existingCartItem.quantity > 1) {
                existingCartItem.quantity--
            } else {
                cartItems.remove(existingCartItem)
            }
        }
    }

    fun getTotalPrice(): Double {
        return cartItems.sumByDouble { it.price * it.quantity }
    }

    // Calculate total price using CartItem properties
    fun calculateTotalPrice(): Double {
        var totalPrice = 0.0
        for (item in cartItems) {
            totalPrice += item.price * item.quantity
        }
        return totalPrice
    }

    // Clear the cart
    fun clearCart() {
        cartItems.clear()
    }
}
