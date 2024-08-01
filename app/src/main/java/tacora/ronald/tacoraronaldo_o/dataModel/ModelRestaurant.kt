package tacora.ronald.tacoraronaldo_o.dataModel

import android.os.Parcel
import android.os.Parcelable

data class ModelRestaurant(
    val id: Int,
    val name: String,
    val category: String,
    val rank: Float,
    val img: String,
    val address: String,
    val phone: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readFloat(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(category)
        parcel.writeFloat(rank)
        parcel.writeString(img)
        parcel.writeString(address)
        parcel.writeString(phone)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ModelRestaurant> {
        override fun createFromParcel(parcel: Parcel): ModelRestaurant {
            return ModelRestaurant(parcel)
        }

        override fun newArray(size: Int): Array<ModelRestaurant?> {
            return arrayOfNulls(size)
        }
    }
}
