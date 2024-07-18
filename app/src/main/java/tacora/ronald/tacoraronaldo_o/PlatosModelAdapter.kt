package tacora.ronald.tacoraronaldo_o

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlatosModelAdapter: RecyclerView.Adapter<PlatosModelAdapter.ViewHolder>() {

    var lstContactos:List<PlatoModel> = emptyList()

    fun actualizarLista(lst:List<PlatoModel>){
        lstContactos = lst
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre = view.findViewById<TextView>(R.id.tvNombre)
        val tvEdad = view.findViewById<TextView>(R.id.tvEdad)
        val tvGenero = view.findViewById<TextView>(R.id.tvGenero)
        val tvDireccion = view.findViewById<TextView>(R.id.tvDireccion)
        val tvLogo = view.findViewById<TextView>(R.id.ivLogo)

        fun setValues(model: PlatoModel){
            tvNombre.setText(model.nombre)
            tvEdad.setText(model.edad.toString() + " años")
            if(model.genero == true)
                tvGenero.setText("Masculino")
            else
                tvGenero.setText("Femenino")
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