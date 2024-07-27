package tacora.ronald.tacoraronaldo_o.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.dataBase.BiteDataBaseHelper
import tacora.ronald.tacoraronaldo_o.dataBase.RegisterGuardadoActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var dbHelper: BiteDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.etUsuario)
        passwordEditText = findViewById(R.id.etPassword)
        val loginButton: Button = findViewById(R.id.btnIngresar)

        val registerButton: Button = findViewById(R.id.btnRegister)

        dbHelper = BiteDataBaseHelper(this)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (checkUserCredentials(username, password)) {
                val intent = Intent(this, homeActivity::class.java) // O la actividad que desees iniciar
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
        registerButton.setOnClickListener{
            val intent = Intent(this, registerActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun checkUserCredentials(username: String, password: String): Boolean {
        val db = dbHelper.readableDatabase
        val selection = "${BiteDataBaseHelper.COLUMN_USERNAME} = ? AND ${BiteDataBaseHelper.COLUMN_PASSWORD} = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor = db.query(
            BiteDataBaseHelper.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val userExists = cursor.count > 0
        cursor.close()
        return userExists
    }
}