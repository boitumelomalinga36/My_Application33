package com.example.myapplication33

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication33.databinding.UserOrderItemBinding

class UserOrdersAdapter : RecyclerView.Adapter<UserOrdersAdapter.ViewHolder>() {

    private var userOrders: List<UserOrder> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: UserOrderItemBinding = UserOrderItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userOrder = userOrders[position]
        holder.bind(userOrder)
    }

    override fun getItemCount(): Int {
        return userOrders.size
    }

    fun setUserOrders(userOrders: List<UserOrder>) {
        this.userOrders = userOrders
    }

    inner class ViewHolder(private val binding: UserOrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userOrder: UserOrder) {
            binding.userOrder = userOrder
            binding.executePendingBindings()
        }
    }
}
