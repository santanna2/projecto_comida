package tacora.ronald.tacoraronaldo_o.recyclers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.recyclers.RestaurantCompany


class RestaurantAdapter: RecyclerView.Adapter<RestaurantAdapter.ViewHolder>(){

    var restaurant1:List<RestaurantCompany> = emptyList()
    fun actualizarLista(lst:List<RestaurantCompany>){
        restaurant1 = lst
        notifyDataSetChanged()
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rvRestaurant = view.findViewById<TextView>(R.id.modelRestaurant)
        val rvCategory = view.findViewById<TextView>(R.id.modelCategory)
        val rvImagine = view.findViewById<ImageView>(R.id.portadaRestaurant)

        fun setValues(model: RestaurantCompany){
            rvRestaurant.setText(model.restaurant)
            rvCategory.setText(model.category)

            Glide.with(itemView.context)
                .load(model.imagine)
                //.load(R.drawable.img) // Puedes usar un recurso drawable si es local, por ejemplo R.drawable.mi_imagen
                .into(rvImagine)
        }
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_model,parent,false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            //el tamaño de mi lista
            return restaurant1.size
        }

        override fun onBindViewHolder(holder:ViewHolder, position: Int) {
            holder.setValues(restaurant1[position])
        }
}