package tacora.ronald.tacoraronaldo_o.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tacora.ronald.tacoraronaldo_o.R

class PlatosModelAdapter: RecyclerView.Adapter<PlatosModelAdapter.ViewHolder>() {

    var lstContactos:List<PlatoModel> = emptyList()

    fun actualizarLista(lst:List<PlatoModel>){
        lstContactos = lst
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre = view.findViewById<TextView>(R.id.modelPlato)
        val tvCategory = view.findViewById<TextView>(R.id.modelComidaCategory)
        val tvGenero = view.findViewById<TextView>(R.id.modelPrice)

        val tvDireccion = view.findViewById<TextView>(R.id.tvDireccion)
        val tvLogo = view.findViewById<TextView>(R.id.ivLogo)

        fun setValues(model: PlatoModel){
            tvNombre.setText(model.name)
            tvCategory.setText(model.category)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_platos,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //el tamaño de mi lista
        return lstContactos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setValues(lstContactos[position])
    }
}