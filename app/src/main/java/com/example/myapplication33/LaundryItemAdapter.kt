import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication33.CartItem
import com.google.firebase.database.DatabaseReference
import com.example.myapplication33.R
import com.example.myapplication33.ShoppingCart

class LaundryItemAdapter(
    private val laundryItems: List<CartItem>, // Change to CartItem
    private val shoppingCart: ShoppingCart,
    private val databaseReference: DatabaseReference // Add database reference parameter
) : RecyclerView.Adapter<LaundryItemAdapter.LaundryItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaundryItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item1, parent, false)
        return LaundryItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaundryItemViewHolder, position: Int) {
        val item = laundryItems[position]

        holder.textItemName.text = item.name
        holder.textItemPrice.text = "Price: R${item.price}"
        holder.textItemQuantity.text = item.quantity.toString()

        holder.buttonAdd.setOnClickListener {
            item.quantity++
            notifyItemChanged(position)
            shoppingCart.addItem(item)
            // Save to the database here if needed
            databaseReference.child(item.name).setValue(item)
        }

        holder.buttonRemove.setOnClickListener {
            if (item.quantity > 0) {
                item.quantity--
                notifyItemChanged(position)
                shoppingCart.removeItem(item)
                // Save to the database here if needed
                databaseReference.child(item.name).setValue(item)
            }
        }
    }

    override fun getItemCount() = laundryItems.size

    inner class LaundryItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textItemName: TextView = itemView.findViewById(R.id.textItemName1)
        val textItemPrice: TextView = itemView.findViewById(R.id.textItemPrice1)
        val buttonAdd: Button = itemView.findViewById(R.id.buttonAdd1)
        val textItemQuantity: TextView = itemView.findViewById(R.id.textItemQuantity1)
        val buttonRemove: Button = itemView.findViewById(R.id.buttonRemove1)
    }
}
