// CartActivity.kt

package com.example.myapplication33

import CartAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication33.CartItem

class CartActivity : AppCompatActivity() {
    private lateinit var cartItems: MutableList<CartItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Retrieve cart items from the intent as a nullable list
        cartItems = intent.getParcelableArrayListExtra<CartItem>("cartItems").orEmpty().toMutableList()

        val cartRecyclerView = findViewById<RecyclerView>(R.id.cartRecyclerView)
        val cartAdapter = CartAdapter(cartItems, this)
        cartRecyclerView.adapter = cartAdapter
        cartRecyclerView.layoutManager = LinearLayoutManager(this)
        val buttonCheckout = findViewById<Button>(R.id.buttonCheckout)

        // Calculate and display the total price and total items
        updateTotalViews()

        buttonCheckout.setOnClickListener {
            // Implement the logic to navigate to the checkout page or perform checkout here
            // For example, you can start a new activity or display a dialog for checkout options.
            // Replace `CheckoutActivity::class.java` with the name of your checkout activity.
            val intent = Intent(this, Checkout::class.java)
            startActivity(intent)
        }
    }

    // Function to calculate the total price and total items
    fun updateTotalViews() {
        val totalPriceTextView = findViewById<TextView>(R.id.totalPriceTextView)
        val totalItemsTextView = findViewById<TextView>(R.id.totalItemsTextView)

        val (totalPrice, totalItems) = calculateTotalPriceAndItems(cartItems)
        totalPriceTextView.text = "Total: R$totalPrice"
        totalItemsTextView.text = "Total Items: $totalItems"
    }

    private fun calculateTotalPriceAndItems(cartItems: List<CartItem>): Pair<Double, Int> {
        var totalPrice = 0.0
        var totalItems = 0

        for (item in cartItems) {
            totalPrice += item.price * item.quantity
            totalItems += item.quantity
        }

        return Pair(totalPrice, totalItems)
    }
}
