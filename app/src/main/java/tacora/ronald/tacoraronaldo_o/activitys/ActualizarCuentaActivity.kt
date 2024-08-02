package tacora.ronald.tacoraronaldo_o.activitys

import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.dataBase.BiteDataBaseHelper

class ActualizarCuentaActivity : AppCompatActivity() {

    private lateinit var dbHelper: BiteDataBaseHelper
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_actualizar_cuenta)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = BiteDataBaseHelper(this)
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        val idusuario = sharedPreferences.getInt("user_code", -1)

        val nombreEditText = findViewById<EditText>(R.id.etNombre)
        val apellidoEditText = findViewById<EditText>(R.id.etApellido)
        val usuarioEditText = findViewById<EditText>(R.id.etUsuario)
        val passwordEditText = findViewById<EditText>(R.id.etPassword)
        val telefonoEditText = findViewById<EditText>(R.id.etTelefono)
        val emailEditText = findViewById<EditText>(R.id.etEmail)
        val guardarButton = findViewById<Button>(R.id.btnGuardar)

        if (idusuario != -1) {
            val usuario = dbHelper.MostrarUsuario(idusuario)
            if (usuario != null) {
                nombreEditText.setText(usuario.NombreUsuario)
                apellidoEditText.setText(usuario.ApellidoUsuario)
                usuarioEditText.setText(usuario.UsuarioUsuario)
                passwordEditText.setText(usuario.ContraseñaUsuario)
                telefonoEditText.setText(usuario.telefonoUsuario)
                emailEditText.setText(usuario.EmailUsuario)
            } else {
                Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
            }
        }

        guardarButton.setOnClickListener {
            val nuevoNombre = nombreEditText.text.toString()
            val nuevoApellido = apellidoEditText.text.toString()
            val nuevoUsuario = usuarioEditText.text.toString()
            val nuevaContraseña = passwordEditText.text.toString()
            val nuevoTelefono = telefonoEditText.text.toString()
            val nuevoEmail = emailEditText.text.toString()

            if (idusuario != -1) {
                val result = dbHelper.ActualizarUsuario(idusuario, nuevoNombre, nuevoApellido, nuevoUsuario, nuevaContraseña, nuevoTelefono, nuevoEmail)
                if (result > 0) {
                    Toast.makeText(this, "Datos actualizados con éxito", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, CuentaActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Error al actualizar datos", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
