// CartAdapter.kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication33.CartActivity
import com.example.myapplication33.CartItem
import com.example.myapplication33.R

class CartAdapter(private val cartItems: MutableList<CartItem>, private val context: CartActivity) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textItemName: TextView = itemView.findViewById(R.id.textItemName)
        val textItemPrice: TextView = itemView.findViewById(R.id.textItemPrice)
        val textItemQuantity: TextView = itemView.findViewById(R.id.textItemQuantity)
        val buttonRemove: Button = itemView.findViewById(R.id.buttonRemove)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item_layout, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = cartItems[position]
        holder.textItemName.text = currentItem.name
        holder.textItemPrice.text = "Price: R${currentItem.price}"
        holder.textItemQuantity.text = currentItem.quantity.toString()

        // Handle the "Remove" button click here
        holder.buttonRemove.setOnClickListener {
            cartItems.removeAt(position)
            notifyDataSetChanged()
            context.updateTotalViews()
        }
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }
}
