package tacora.ronald.tacoraronaldo_o.recyclers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.activitys.MenuCartaActivity
import tacora.ronald.tacoraronaldo_o.activitys.RestaurantInformacion
import tacora.ronald.tacoraronaldo_o.dataModel.ModelRestaurant


class RestaurantAdapter(
    private val restaurantList: List<ModelRestaurant>,
    private val context: Context
) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    var restaurant1:List<RestaurantCompany> = emptyList()

    fun actualizarLista(lst:List<RestaurantCompany>){
        restaurant1 = lst
        notifyDataSetChanged()
    }
    class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rvRestaurant = view.findViewById<TextView>(R.id.modelRestaurant)
        val rvCategory = view.findViewById<TextView>(R.id.modelCategory)
        val rvImagine = view.findViewById<ImageView>(R.id.portadaRestaurant)

        val btnIngresar = view.findViewById<Button>(R.id.rv_res_ingresar)
        val btnInformacion = view.findViewById<Button>(R.id.rv_res_informacion)

        fun setValues(model: RestaurantCompany){

        }
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_model,parent,false)
            return RestaurantViewHolder(view)
        }

    override fun getItemCount() = restaurantList.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurantList[position]
        holder.rvRestaurant.text = restaurant.name
        holder.rvCategory.text = restaurant.category

        Glide.with(holder.rvImagine.context)
            .load(restaurant.img) // Cargar la imagen desde la URL
            .into(holder.rvImagine)

        holder.btnIngresar.setOnClickListener {
            val intent = Intent(context, MenuCartaActivity::class.java)
            intent.putExtra("RESTAURANT_ID", restaurant.id) // Pasar el ID del restaurante si es necesario
            context.startActivity(intent)
        }

        // Configurar clic en btnInformacion para abrir la información del restaurante
        holder.btnInformacion.setOnClickListener {
            val intent = Intent(context, RestaurantInformacion::class.java)
            intent.putExtra("RESTAURANT_ID", restaurant.id) // Pasar el ID del restaurante si es necesario
            context.startActivity(intent)
        }
    }
}

