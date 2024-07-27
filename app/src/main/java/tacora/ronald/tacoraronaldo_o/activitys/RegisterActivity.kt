package tacora.ronald.tacoraronaldo_o.activitys

import android.annotation.SuppressLint
import androidx.activity.enableEdgeToEdge
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.dataBase.BiteDataBaseHelper
import tacora.ronald.tacoraronaldo_o.dataBase.RegisterGuardadoActivity

class registerActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var apellidoEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var emailEditText: EditText

    private lateinit var dbHelper: BiteDataBaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nameEditText = findViewById(R.id.etNombre)
        apellidoEditText = findViewById(R.id.etApellido)
        usernameEditText = findViewById(R.id.etUsuario)
        passwordEditText = findViewById(R.id.etPassword)
        emailEditText = findViewById(R.id.etEmail)

        val registerButton: Button = findViewById(R.id.btnRegistrar)
        val cancelButton: Button = findViewById(R.id.btnCancelar)

        dbHelper = BiteDataBaseHelper(this)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val apellido = apellidoEditText.text.toString().trim()
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()

            if (name.isEmpty() ||apellido.isEmpty()|| username.isEmpty() || password.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (addUserToDatabase(
                    name,
                    apellido,
                    username,
                    password,
                    email
                )) {
                val intent = Intent(this, RegisterGuardadoActivity::class.java)
                startActivity(intent)
                finish()

            } else {
                Toast.makeText(this, "Error al registrar. Intente nuevamente.", Toast.LENGTH_SHORT).show()
            }
        }
        cancelButton.setOnClickListener {
            // Acción al hacer clic en Cancelar, por ejemplo, regresar a la actividad anterior
            finish()
        }
    }

    private fun addUserToDatabase(
        name: String,
        apellido: String,
        username: String,
        password: String,
        email: String,
    ): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(BiteDataBaseHelper.COLUMN_NAME, name)
            put(BiteDataBaseHelper.COLUMN_APELLIDO, apellido)
            put(BiteDataBaseHelper.COLUMN_USERNAME, username)
            put(BiteDataBaseHelper.COLUMN_PASSWORD, password)
            put(BiteDataBaseHelper.COLUMN_EMAIL, email)
        }

        val newRowId = db.insert(BiteDataBaseHelper.TABLE_NAME, null, values)
        return newRowId != -1L
    }
}

