package tacora.ronald.tacoraronaldo_o.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tacora.ronald.tacoraronaldo_o.R

class PlatosModelAdapter: RecyclerView.Adapter<PlatosModelAdapter.ViewHolder>() {

    var lstContactos: List<PlatoModel> = emptyList()

    fun actualizarLista(lst: List<PlatoModel>) {
        lstContactos = lst
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.modelPlato)
        val tvCategory: TextView = view.findViewById(R.id.modelComidaCategory)
        val tvPrecio: TextView = view.findViewById(R.id.modelPrice)

        init {
            if (tvNombre == null || tvCategory == null || tvPrecio == null) {
                throw NullPointerException("One or more TextViews are null in ViewHolder")
            }
        }

        fun setValues(model: PlatoModel) {
            tvNombre.text = model.name
            tvCategory.text = model.category
            tvPrecio.text = model.precio.toString()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plato_model, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        // el tamaño de la lista
        return lstContactos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setValues(lstContactos[position])
    }
}
