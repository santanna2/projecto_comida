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

    // Eliminar esta variable no utilizada
    // var restaurant1:List<RestaurantCompany> = emptyList()
    // Actualizar lista no se usa, si es necesario mantenerlo, déjalo
    // fun actualizarLista(lst:List<RestaurantCompany>){
    //     restaurant1 = lst
    //     notifyDataSetChanged()
    // }

    class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rvRestaurant: TextView = view.findViewById(R.id.modelRestaurant)
        val rvCategory: TextView = view.findViewById(R.id.modelCategory)
        val rvImagine: ImageView = view.findViewById(R.id.portadaRestaurant)

        val btnIngresar: Button = view.findViewById(R.id.rv_res_ingresar)
        val btnInformacion: Button = view.findViewById(R.id.rv_res_informacion)

        fun setValues(model: ModelRestaurant) {
            rvRestaurant.text = model.name
            rvCategory.text = model.category

            // Utilizar Glide para cargar la imagen
            Glide.with(rvImagine.context)
                .load(model.img) // Cargar la imagen desde la URL
                .into(rvImagine)
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
            val intent = Intent(context, MenuCartaActivity::class.java).apply {
                putExtra("RESTAURANT_ID", restaurant.id)
                putExtra("CATEGORIA", "Desayuno")
            }
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

