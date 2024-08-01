package tacora.ronald.tacoraronaldo_o.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import tacora.ronald.tacoraronaldo_o.R

class CarritoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_carrito)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnHistorial = findViewById<Button>(R.id.btnHistorial)
        btnHome.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnHistorial.setOnClickListener{
            val intent = Intent(this, HistorialActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}