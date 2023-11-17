package com.example.myapplication33

import OrderAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
        adapter = OrderAdapter(orderList, this::processOrder, this::completeOrder, this::removeOrder)
        val logOut = findViewById<Button>(R.id.button6)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        database = FirebaseDatabase.getInstance().reference.child("orders")
        retrieveOrdersFromFirebase()

        logOut.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
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

    private fun removeOrder(order: Order) {
        Log.d("ADMINPAGE", "Removing order: ${order.orderNumber}")
        orderList.remove(order)
        adapter.notifyDataSetChanged()

        // Remove the order from the database
        val orderRef = database.child(order.orderNumber)
        orderRef.removeValue().addOnSuccessListener {
            Log.d("ADMINPAGE", "Order removed from database successfully")
        }.addOnFailureListener {
            Log.e("ADMINPAGE", "Failed to remove order from database", it)
        }
    }


}