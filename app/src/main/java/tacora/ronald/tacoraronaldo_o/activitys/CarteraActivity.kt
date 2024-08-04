package tacora.ronald.tacoraronaldo_o.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.dataBase.BiteDataBaseHelper
import tacora.ronald.tacoraronaldo_o.recyclers.CarteraModelAdapter

class CarteraActivity : AppCompatActivity() {

    private lateinit var dbHelper: BiteDataBaseHelper
    private lateinit var tvBalance: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CarteraModelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cartera)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = BiteDataBaseHelper(this)
        tvBalance = findViewById(R.id.tvBalance)
        recyclerView = findViewById(R.id.rvTransactions)
        adapter = CarteraModelAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val btnPedir = findViewById<Button>(R.id.btnOrderFood)

        btnPedir.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            }
            startActivity(intent)
            finish()
        }

        calcularYMostrarPrecioTotal()
    }

    private fun calcularYMostrarPrecioTotal() {
        val platosActivos = dbHelper.ObtenerPlatosActivos()
        val totalPrice = platosActivos.sumOf { it.precio }
        tvBalance.text = "$${String.format("%.2f", totalPrice)}"
        adapter.actualizarLista(platosActivos)
    }
}
