package com.example.myapplication33

import Order
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderAdapter(private val orders: List<Order>, private val processOrder: (Order) -> Unit, private val completeOrder: (Order) -> Unit) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.order_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(order: Order) {
            // Bind order details to the views
            // For example:
            val orderNumberTextView = itemView.findViewById<TextView>(R.id.orderNumberTextView)
            val totalPriceTextView = itemView.findViewById<TextView>(R.id.totalPriceTextView)

            orderNumberTextView.text = order.orderNumber
            totalPriceTextView.text = "Total: R${order.totalPrice}"

            // Set click listeners for process and complete buttons
            val processButton = itemView.findViewById<Button>(R.id.buttonProcess)
            processButton.setOnClickListener {
                processOrder(order)
            }

            val completeButton = itemView.findViewById<Button>(R.id.buttonComplete)
            completeButton.setOnClickListener {
                completeOrder(order)
            }
        }



    }

    override fun getItemCount(): Int {
        return orders.size
    }
}
