package tacora.ronald.tacoraronaldo_o.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.dataBase.BiteDataBaseHelper
import tacora.ronald.tacoraronaldo_o.recyclers.CartaModelAdapter

class CarritoActivity : AppCompatActivity() {
    private lateinit var dbHelper: BiteDataBaseHelper
    private lateinit var cartaModelAdapter: CartaModelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_carrito)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = BiteDataBaseHelper(this)
        cartaModelAdapter = CartaModelAdapter(dbHelper)

        val recyclerView = findViewById<RecyclerView>(R.id.pasarelaRestaurant)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = cartaModelAdapter

        // Carga los platos pendientes
        cargarPlatosPendientes()

        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnHistorial = findViewById<Button>(R.id.btnHistorial)
        btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish()
        }
        btnHistorial.setOnClickListener {
            val intent = Intent(this, HistorialActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish()
        }
    }

    private fun cargarPlatosPendientes() {
        try {
            val platosPendientes = dbHelper.ObtenerPlatosPendientes()
            cartaModelAdapter.actualizarLista(platosPendientes)
        } catch (e: Exception) {
            Toast.makeText(this, "Error al cargar los platos", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}
