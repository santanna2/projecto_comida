package tacora.ronald.tacoraronaldo_o.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.dataBase.BiteDataBaseHelper

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

            val userId = checkUserCredentials(username, password)
            if (userId != null) {
                // Cambiado: Almacena el ID del usuario en SharedPreferences
                val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putInt("user_code", userId)
                editor.apply()

                // Inicia la actividad de inicio después de iniciar sesión
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
        registerButton.setOnClickListener{
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    private fun checkUserCredentials(username: String, password: String): Int? {
        val db = dbHelper.readableDatabase

        val selection = "${BiteDataBaseHelper.COLUMN_USERNAME} = ? AND ${BiteDataBaseHelper.COLUMN_PASSWORD} = ?"
        val selectionArgs = arrayOf(username, password)

        val cursor = db.query(
            BiteDataBaseHelper.TABLE_NAME,
            arrayOf(BiteDataBaseHelper.COLUMN_ID),
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        var userId: Int? = null
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndexOrThrow(BiteDataBaseHelper.COLUMN_ID))
        }
        cursor.close()
        return userId
    }
    //checkUserCredentials
    private fun cambiar(username: String, password: String): Boolean {
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