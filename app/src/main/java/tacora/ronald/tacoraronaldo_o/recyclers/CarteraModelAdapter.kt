package tacora.ronald.tacoraronaldo_o.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tacora.ronald.tacoraronaldo_o.R

class CarteraModelAdapter : RecyclerView.Adapter<CarteraModelAdapter.ViewHolder>() {

    private var lstPlatos: List<PlatoModel> = emptyList()

    fun actualizarLista(lst: List<PlatoModel>) {
        lstPlatos = lst
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tvNombrePlato: TextView = view.findViewById(R.id.tvNombrePlato)
        val tvPrecioPlato: TextView = view.findViewById(R.id.tvPrecioPlato)
        val tvEstadoPlato: TextView = view.findViewById(R.id.tvEstadoPlato)

        fun bind(plato: PlatoModel) {
            tvNombrePlato.text = plato.name
            tvPrecioPlato.text = "Precio: $${plato.precio}"
            tvEstadoPlato.text = "Estado: ${plato.estado}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cartera_model, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lstPlatos[position])
    }

    override fun getItemCount(): Int {
        return lstPlatos.size
    }
}
