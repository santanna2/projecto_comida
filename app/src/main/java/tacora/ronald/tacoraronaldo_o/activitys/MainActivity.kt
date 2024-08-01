package tacora.ronald.tacoraronaldo_o.activitys

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.dataBase.BiteDataBaseHelper

class MainActivity : AppCompatActivity() {

    private lateinit var etPlatoName: EditText
    private lateinit var etPlatoPrecio: EditText
    private lateinit var etPlatoCategory: EditText
    private lateinit var etPlatoRank: EditText
    private lateinit var etPlatoImg: EditText
    private lateinit var etPlatoDescripcion: EditText
    private lateinit var btnRegisterPlato: Button

    private lateinit var dbHelper: BiteDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = BiteDataBaseHelper(this)

        etPlatoName = findViewById(R.id.etPlatoName)
        etPlatoPrecio = findViewById(R.id.etPlatoPrecio)
        etPlatoCategory = findViewById(R.id.etPlatoCategory)
        etPlatoRank = findViewById(R.id.etPlatoRank)
        etPlatoImg = findViewById(R.id.etPlatoImg)
        etPlatoDescripcion = findViewById(R.id.etPlatoDescripcion)
        btnRegisterPlato = findViewById(R.id.btnRegisterPlato)

        btnRegisterPlato.setOnClickListener {
            val name = etPlatoName.text.toString()
            val precio = etPlatoPrecio.text.toString().toDoubleOrNull()
            val category = etPlatoCategory.text.toString()
            val rank = etPlatoRank.text.toString().toDoubleOrNull()
            val img = etPlatoImg.text.toString()
            val descripcion = etPlatoDescripcion.text.toString()

            if (name.isNotEmpty() && precio != null && category.isNotEmpty() && rank != null && img.isNotEmpty() && descripcion.isNotEmpty()) {
                val success = dbHelper.insertarPlato(name, precio, category, rank, img, descripcion)
                if (success) {
                    Toast.makeText(this, "Plato registrado exitosamente", Toast.LENGTH_SHORT).show()
                    // Aquí puedes asociar el plato con un restaurante si es necesario
                    // dbHelper.asociarPlatoConRestaurant(restaurantId, platoId)
                } else {
                    Toast.makeText(this, "Error al registrar el plato", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}