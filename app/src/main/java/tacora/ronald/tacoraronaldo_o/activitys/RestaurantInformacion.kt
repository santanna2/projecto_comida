package tacora.ronald.tacoraronaldo_o.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.dataBase.BiteDataBaseHelper

class RestaurantInformacion : AppCompatActivity() {
    private lateinit var biteDataBaseHelper: BiteDataBaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_restaurant_informacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        biteDataBaseHelper = BiteDataBaseHelper(this)

        val restaurantId = intent.getIntExtra("RESTAURANT_ID", -1)
        if (restaurantId != -1) {
            // Obtener información del restaurante usando el ID
            val restaurant = biteDataBaseHelper.ObtenerRestaurants().find { it.id == restaurantId }
            val btnCalificar = findViewById<Button>(R.id.btnCalificar)
            val btnOrdenar = findViewById<Button>(R.id.btnOrder)

            btnCalificar.setOnClickListener{
                val intent = Intent(this, CalificarActivity::class.java)
                startActivity(intent)
                finish()
            }
            btnOrdenar.setOnClickListener{
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}