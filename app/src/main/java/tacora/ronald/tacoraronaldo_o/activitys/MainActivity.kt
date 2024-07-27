package tacora.ronald.tacoraronaldo_o.activitys

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tacora.ronald.tacoraronaldo_o.R

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val plateADA = PlatosModelAdapter()
        val rv = findViewById<RecyclerView>(R.id.viewUno)

        rv.apply {
            adapter = plateADA
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
        val ListaPlato: MutableList<PlatoModel> = mutableListOf()
        ListaPlato.add(PlatoModel("juan",22,"su_kasa",true))
        ListaPlato.add(PlatoModel("hol",22,"su_kasa",true))
        ListaPlato.add(PlatoModel("juwean",22,"su_kasa",true))
        ListaPlato.add(PlatoModel("juwewan",22,"su_kasa",true))
        ListaPlato.add(PlatoModel("juwean",22,"su_kasa",true))

        plateADA.actualizarLista(ListaPlato)
    }
}