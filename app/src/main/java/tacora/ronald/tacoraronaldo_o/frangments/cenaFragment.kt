package tacora.ronald.tacoraronaldo_o.frangments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.dataBase.BiteDataBaseHelper
import tacora.ronald.tacoraronaldo_o.recyclers.PlatosModelAdapter

class cenaFragment : Fragment() {

    private lateinit var adapter: PlatosModelAdapter
    private lateinit var biteDataBaseHelper: BiteDataBaseHelper

    private var restaurantId: Int = -1
    private var categoria: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cena, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        biteDataBaseHelper = BiteDataBaseHelper(requireContext())
        adapter = PlatosModelAdapter(biteDataBaseHelper)

        val recyclerView = view.findViewById<RecyclerView>(R.id.itemCena)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        Log.d("FirstFragment", "Adapter assigned to RecyclerView")

        arguments?.let {
            restaurantId = it.getInt("RESTAURANT_ID", -1)
            categoria = "Cena"
        }
        loadPlatos()
    }

    private fun loadPlatos() {
        // Cargar datos desde la base de datos usando el id y la categoria
        val platosList = biteDataBaseHelper.menu(restaurantId, categoria ?: "N/A")
        Log.d("FirstFragment", "Platos List Size: ${platosList.size}")
        adapter.actualizarLista(platosList)
    }
}
