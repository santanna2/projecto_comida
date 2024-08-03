package tacora.ronald.tacoraronaldo_o.dataBase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.activitys.HomeActivity

class RegistroRestaurant : AppCompatActivity() {

    private lateinit var RestaurantName: EditText
    private lateinit var RestaurantCategory: EditText
    private lateinit var RestaurantAddress: EditText
    private lateinit var RestaurantImg: EditText
    private lateinit var RestaurantRank: EditText
    private lateinit var RestaurantPhone: EditText


    private lateinit var dbHelper: BiteDataBaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_restaurant)

        RestaurantName = findViewById(R.id.etRestaurantName)
        RestaurantCategory = findViewById(R.id.etRestaurantCategory)
        RestaurantAddress = findViewById(R.id.etRestaurantAddress)
        RestaurantImg = findViewById(R.id.etRestaurantImg)
        RestaurantRank = findViewById(R.id.etRestaurantRank)
        RestaurantPhone = findViewById(R.id.etRestaurantPhone)


        val registerButton: Button = findViewById(R.id.btnSave)
        val cancelButton: Button = findViewById(R.id.btnCancel)

        dbHelper = BiteDataBaseHelper(this)

        registerButton.setOnClickListener {
            val name = RestaurantName.text.toString().trim()
            val category = RestaurantCategory.text.toString().trim()
            val adress = RestaurantAddress.text.toString().trim()
            val img = RestaurantImg.text.toString().trim()
            val rank = RestaurantRank.text.toString().trim()
            val phone = RestaurantPhone.text.toString().trim()

            if (name.isEmpty() ||category.isEmpty()||adress.isEmpty()|| img.isEmpty()||rank.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (addUserToDatabase(
                    name,
                    category,
                    adress,
                    img,
                    rank,
                    phone
                )) {
                val intent = Intent(this, HomeActivity::class.java)
                Toast.makeText(this, "Registrado con exito", Toast.LENGTH_SHORT).show()
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
        category: String,
        adress: String,
        img:String,
        rank: String,
        phone: String
    ): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(BiteDataBaseHelper.COLUMN_RESTAURANT_NAME, name)
            put(BiteDataBaseHelper.COLUMN_RESTAURANT_CATEGORY, category)
            put(BiteDataBaseHelper.COLUMN_RESTAURANT_ADRESS, adress)
            put(BiteDataBaseHelper.COLUMN_RESTAURANT_IMG, img)
            put(BiteDataBaseHelper.COLUMN_RESTAURANT_RANK, rank)
            put(BiteDataBaseHelper.COLUMN_RESTAURANT_PHONE, phone)
        }

        val newRowId = db.insert(BiteDataBaseHelper.TABLE_RESTAURANT, null, values)
        return newRowId != -1L
    }
}