import android.os.Parcel
import android.os.Parcelable
import com.example.myapplication33.CartItem

data class Order(
    val orderNumber: String,
    val items: List<CartItem>,
    val totalPrice: Double,
    val userName: String,
    val cardNumber: String,
    val expiryDate: String,
    val cvv: String,
    val streetAddress: String,
    val city: String,
    val province: String,
    val postalCode: String,
    var status: OrderStatus
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        mutableListOf<CartItem>(), // You should read items from the parcel
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        OrderStatus.valueOf(parcel.readString() ?: "")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(orderNumber)
        // Write items to the parcel
        parcel.writeDouble(totalPrice)
        parcel.writeString(userName)
        parcel.writeString(cardNumber)
        parcel.writeString(expiryDate)
        parcel.writeString(cvv)
        parcel.writeString(streetAddress)
        parcel.writeString(city)
        parcel.writeString(province)
        parcel.writeString(postalCode)
        parcel.writeString(status.name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Order> {
        override fun createFromParcel(parcel: Parcel): Order {
            return Order(parcel)
        }

        override fun newArray(size: Int): Array<Order?> {
            return arrayOfNulls(size)
        }
    }
}

enum class OrderStatus {
    PROCESSING, // Order is being processed
    COMPLETED,  // Order has been completed
    // You can add more status values based on your needs
}
