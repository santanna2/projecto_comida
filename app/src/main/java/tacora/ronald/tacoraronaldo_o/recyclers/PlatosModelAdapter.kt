package tacora.ronald.tacoraronaldo_o.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.dataBase.BiteDataBaseHelper

class PlatosModelAdapter(private val dbHelper: BiteDataBaseHelper): RecyclerView.Adapter<PlatosModelAdapter.ViewHolder>() {

    var lstContactos: List<PlatoModel> = emptyList()

    fun actualizarLista(lst: List<PlatoModel>) {
        lstContactos = lst
        notifyDataSetChanged()
    }

    // ViewHolder modificado para recibir dbHelper
    class ViewHolder(view: View, private val dbHelper: BiteDataBaseHelper) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.modelPlato)
        val tvCategory: TextView = view.findViewById(R.id.modelComidaCategory)
        val tvPrecio: TextView = view.findViewById(R.id.modelPrice)
        val btnAdd: Button = view.findViewById(R.id.btnADD)

        init {
            if (tvNombre == null || tvCategory == null || tvPrecio == null || btnAdd == null) {
                throw NullPointerException("One or more views are null in ViewHolder")
            }
        }

        fun setValues(model: PlatoModel) {
            tvNombre.text = model.name
            tvCategory.text = model.category
            tvPrecio.text = model.precio.toString()

            btnAdd.setOnClickListener {
                btnAdd.setBackgroundColor(itemView.context.getColor(R.color.green))

                try {
                    dbHelper.actualizarPlatoEstado(model.id, "pendiente")
                } catch (e: Exception) {
                    Toast.makeText(itemView.context, "Error al actualizar el estado", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plato_model, parent, false)
        return ViewHolder(view, dbHelper) // Pasar dbHelper al ViewHolder
    }

    override fun getItemCount(): Int {
        return lstContactos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setValues(lstContactos[position])
    }
}
