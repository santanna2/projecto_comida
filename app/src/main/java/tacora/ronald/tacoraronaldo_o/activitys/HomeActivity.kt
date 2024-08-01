package tacora.ronald.tacoraronaldo_o.activitys

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.dataBase.BiteDataBaseHelper
import tacora.ronald.tacoraronaldo_o.dataBase.RegistroRestaurant
import tacora.ronald.tacoraronaldo_o.databinding.ActivityRegistroRestaurantBinding
import tacora.ronald.tacoraronaldo_o.frangments.VistaCarta
import tacora.ronald.tacoraronaldo_o.recyclers.RestaurantAdapter
import tacora.ronald.tacoraronaldo_o.recyclers.RestaurantCompany

class HomeActivity : AppCompatActivity() {
    private lateinit var biteDataBaseHelper: BiteDataBaseHelper
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        biteDataBaseHelper = BiteDataBaseHelper(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val salirzz = findViewById<LinearLayout>(R.id.salirzz)
        val cartera = findViewById<LinearLayout>(R.id.btnCartera)
        val cuenta = findViewById<LinearLayout>(R.id.btnCuenta)
        val necesario = findViewById<ImageButton>(R.id.necesario)
        salirzz.setOnClickListener{
            finish()
        }

        cartera.setOnClickListener{
            val intent3 = Intent(this, CarritoActivity::class.java)
            startActivity(intent3)
            finish()
        }
        cuenta.setOnClickListener{
            val intent4 = Intent(this, CuentaActivity::class.java)
            startActivity(intent4)
            finish()
        }

        necesario.setOnClickListener{
            val intent = Intent(this,RegistroRestaurant::class.java)
            startActivity(intent)
            finish()
        }
        val restaurantList = biteDataBaseHelper.ObtenerRestaurants()

        val restauAdapter = RestaurantAdapter(restaurantList,this)
        val rv = findViewById<RecyclerView>(R.id.pasarelaRestaurant)

        rv.apply {
            adapter = restauAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HomeActivity)
        }
    }

}