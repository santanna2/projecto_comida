package tacora.ronald.tacoraronaldo_o.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import tacora.ronald.tacoraronaldo_o.R

class HistorialActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnHome = findViewById<Button>(R.id.btnHome)
        val btnCartera = findViewById<Button>(R.id.btnWallet)
        val btnSeguir = findViewById<Button>(R.id.btnRastreo)

        btnHome.setOnClickListener{
            val intent3 = Intent(this, HomeActivity::class.java)
            startActivity(intent3)
            finish()
        }
        btnCartera.setOnClickListener{
            val intent = Intent(this,CarteraActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnSeguir.setOnClickListener{
            val intent = Intent(this,SeguimientoActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}