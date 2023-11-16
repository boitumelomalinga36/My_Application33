package com.example.myapplication33

import OrderAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*


class ADMINPAGE : AppCompatActivity() {
    private lateinit var orderList: MutableList<Order>
    private lateinit var adapter: OrderAdapter
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminpage)

        orderList = mutableListOf()
        adapter = OrderAdapter(orderList, this::processOrder, this::completeOrder)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        database = FirebaseDatabase.getInstance().reference.child("orders")
        retrieveOrdersFromFirebase()
    }

    private fun retrieveOrdersFromFirebase() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                orderList.clear()

                for (orderSnapshot in dataSnapshot.children) {
                    val order = orderSnapshot.getValue(Order::class.java)
                    order?.let {
                        orderList.add(it)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("ADMINPAGE", "loadPost:onCancelled", databaseError.toException())
            }
        })
    }

    private fun processOrder(order: Order) {
        order.status = OrderStatus.PROCESSING
        // Update the order status in the database
        updateOrderStatusInDatabase(order)
    }

    private fun completeOrder(order: Order) {
        order.status = OrderStatus.COMPLETED
        // Update the order status in the database
        updateOrderStatusInDatabase(order)
    }

    private fun updateOrderStatusInDatabase(order: Order) {
        val orderRef = database.child(order.orderNumber)
        orderRef.setValue(order)
    }
}