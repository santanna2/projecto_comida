package tacora.ronald.tacoraronaldo_o.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.dataBase.BiteDataBaseHelper

class CartaModelAdapter(
    private val dbHelper: BiteDataBaseHelper) : RecyclerView.Adapter<CartaModelAdapter.ViewHolder>() {

    var lstPlatos: List<PlatoModel> = emptyList()

    fun actualizarLista(lst: List<PlatoModel>) {
        lstPlatos = lst
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.modelPlato)
        val tvPrecio: TextView = view.findViewById(R.id.modelPrice)
        val tvEstado: TextView = view.findViewById(R.id.modelEstado)
        val imgPlato: ImageView = view.findViewById(R.id.portadaPlato)
        val btnCancelar: Button = view.findViewById(R.id.btnCancelar)
        val btnConfirmar: Button = view.findViewById(R.id.btnConfirmar)

        fun bind(plato: PlatoModel) {
            tvNombre.text = plato.name
            tvPrecio.text = plato.precio.toString()
            tvEstado.text = plato.estado

            imgPlato.setImageResource(R.drawable.plato)

            btnCancelar.setOnClickListener {
                try {
                    dbHelper.actualizarPlatoEstado(plato.id, "inactivo")
                    Toast.makeText(itemView.context, "Plato marcado como inactivo", Toast.LENGTH_SHORT).show()
                    actualizarLista(dbHelper.ObtenerPlatosPendientes())

                } catch (e: Exception) {
                    Toast.makeText(itemView.context, "Error al actualizar el estado", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }

            btnConfirmar.setOnClickListener {
                try {
                    dbHelper.actualizarPlatoEstado(plato.id, "activo")
                    Toast.makeText(itemView.context, "Plato confirmado como activo", Toast.LENGTH_SHORT).show()
                    // Actualiza la lista después de cambiar el estado
                    actualizarLista(dbHelper.ObtenerPlatosPendientes())
                } catch (e: Exception) {
                    Toast.makeText(itemView.context, "Error al actualizar el estado", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.carta_model, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lstPlatos[position])
    }

    override fun getItemCount(): Int {
        return lstPlatos.size
    }
}
