package com.example.myapplication33

import Order
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ADMINPAGE : AppCompatActivity() {
    private lateinit var orderList: MutableList<Order>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminpage)

        // Initialize orderList and populate it with orders
        orderList = mutableListOf() // Initialize an empty list

        // Use a RecyclerView to display orders in the format similar to the cart page
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = OrderAdapter(orderList, this::processOrder, this::completeOrder)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    // Implement the logic to retrieve orders from your data source
    private fun retrieveOrders(): List<Order> {
        // In a real-world application, you'd retrieve orders from a database or server
        // For now, you can use your in-memory array or list
        return orderList
    }

    // Implement the logic for processing an order
    private fun processOrder(order: Order) {
        order.status = OrderStatus.PROCESSING
        // You can update the order status in your data source (e.g., database or server)

        // Refresh the RecyclerView to reflect the updated order status
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        (recyclerView.adapter as OrderAdapter).notifyDataSetChanged()
    }

    // Implement the logic for completing an order
    private fun completeOrder(order: Order) {
        order.status = OrderStatus.COMPLETED
        // You can update the order status in your data source (e.g., database or server)

        // Refresh the RecyclerView to reflect the updated order status
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        (recyclerView.adapter as OrderAdapter).notifyDataSetChanged()
    }
}
