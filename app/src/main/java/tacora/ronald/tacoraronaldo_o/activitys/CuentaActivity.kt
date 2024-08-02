package tacora.ronald.tacoraronaldo_o.activitys

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.dataBase.BiteDataBaseHelper

class CuentaActivity : AppCompatActivity() {

    private lateinit var dbHelper: BiteDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cuenta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnSalir = findViewById<Button>(R.id.btnSalir)
        val btnCartera = findViewById<Button>(R.id.btnCartera)
        val btnActualizar = findViewById<Button>(R.id.btnActualizar)

        btnSalir.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnCartera.setOnClickListener {
            val intent = Intent(this, CarteraActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnActualizar.setOnClickListener {
            val intent = Intent(this, ActualizarCuentaActivity::class.java)
            startActivity(intent)
            finish()
        }

        dbHelper = BiteDataBaseHelper(this)
        val sharedPreferences: SharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val idusuario = sharedPreferences.getInt("user_code", -1)

        if (idusuario != -1) {
            val usuario = dbHelper.MostrarUsuario(idusuario)
            if (usuario != null) {
                findViewById<TextView>(R.id.CuentaNombre).text = usuario.NombreUsuario
                findViewById<TextView>(R.id.CuentaApellido).text = usuario.ApellidoUsuario
                findViewById<TextView>(R.id.CuentaUsuario).text = usuario.UsuarioUsuario
                findViewById<TextView>(R.id.CuentaPassword).text = usuario.ContraseñaUsuario
                findViewById<TextView>(R.id.CuentaTelefono).text = usuario.telefonoUsuario
                findViewById<TextView>(R.id.CuentaEmail).text = usuario.EmailUsuario
            } else {
                val notFoundText = "Usuario no encontrado"
                findViewById<TextView>(R.id.CuentaNombre).text = notFoundText
                findViewById<TextView>(R.id.CuentaApellido).text = notFoundText
                findViewById<TextView>(R.id.CuentaPassword).text = notFoundText
                findViewById<TextView>(R.id.CuentaUsuario).text = notFoundText
                findViewById<TextView>(R.id.CuentaTelefono).text = notFoundText
                findViewById<TextView>(R.id.CuentaEmail).text = notFoundText
            }
        }
    }
}
