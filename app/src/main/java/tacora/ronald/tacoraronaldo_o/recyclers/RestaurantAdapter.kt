package tacora.ronald.tacoraronaldo_o.recyclers

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
import tacora.ronald.tacoraronaldo_o.activitys.CuentaActivity
import tacora.ronald.tacoraronaldo_o.activitys.InformacionActivity
import tacora.ronald.tacoraronaldo_o.activitys.MenuCartaActivity
import tacora.ronald.tacoraronaldo_o.activitys.RestaurantInformacion


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

        val btnIngresar = view.findViewById<Button>(R.id.rv_res_ingresar)
        val btnInformacion = view.findViewById<Button>(R.id.rv_res_informacion)

        fun setValues(model: RestaurantCompany){
            rvRestaurant.setText(model.restaurant)
            rvCategory.setText(model.category)

            Glide.with(itemView.context)
                .load(model.imagine)
                //.load(R.drawable.img) // Puedes usar un recurso drawable si es local, por ejemplo R.drawable.mi_imagen
                .into(rvImagine)

            btnIngresar.setOnClickListener {
                // Puedes pasar información específica si es necesario
                val context = itemView.context
                val intent = Intent(context, MenuCartaActivity::class.java)
                context.startActivity(intent)
            }

            btnInformacion.setOnClickListener {
                // Puedes pasar información específica si es necesario
                val context = itemView.context
                val intent = Intent(context, RestaurantInformacion::class.java)
                context.startActivity(intent)
            }
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
