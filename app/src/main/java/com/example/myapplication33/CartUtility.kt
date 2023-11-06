package com.example.myapplication33

class CartUtility {
    // A list to store the items in the cart
    private val cartItems: MutableList<CartItem> = mutableListOf()

    // Add an item to the cart
    fun addItemToCart(item: CartItem) {
        cartItems.add(item)
    }

    // Clear the cart
    fun clearCart() {
        cartItems.clear()
    }

    // Get the items in the cart
    fun getCartItems(): List<CartItem> {
        return cartItems
    }

    // Calculate the total price of items in the cart
    fun calculateTotalPrice(): Double {
        var totalPrice = 0.0
        for (item in cartItems) {
            totalPrice += item.price * item.quantity
        }
        return totalPrice
    }

    companion object {
        // Singleton instance for CartUtility
        private var instance: CartUtility? = null

        fun getInstance(): CartUtility {
            if (instance == null) {
                instance = CartUtility()
            }
            return instance as CartUtility
        }
    }
}
