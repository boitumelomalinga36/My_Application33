

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication33.Order
import com.example.myapplication33.databinding.OrderItemLayoutBinding

class OrderAdapter(
    private val orders: List<Order>,
    private val processOrder: (Order) -> Unit,
    private val completeOrder: (Order) -> Unit,
    private val removeOrder: (Order) -> Unit
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: OrderItemLayoutBinding = OrderItemLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    inner class ViewHolder(private val binding: OrderItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            binding.order = order
            binding.executePendingBindings()

            // Set click listeners for process, complete, and remove buttons
            binding.buttonProcess.setOnClickListener {
                processOrder(order)
            }

            binding.buttonComplete.setOnClickListener {
                completeOrder(order)
            }

            binding.buttonRemove.setOnClickListener {
                removeOrder(order)
            }
        }
    }

}
