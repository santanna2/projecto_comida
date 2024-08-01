package tacora.ronald.tacoraronaldo_o.activitys

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.bottomnavigation.BottomNavigationView
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.dataBase.BiteDataBaseHelper
import tacora.ronald.tacoraronaldo_o.frangments.FirstFragment
import tacora.ronald.tacoraronaldo_o.frangments.SecondFragment
import tacora.ronald.tacoraronaldo_o.frangments.cenaFragment

class MenuCartaActivity : AppCompatActivity() {
  lateinit var navegation : BottomNavigationView
  private lateinit var biteDataBaseHelper: BiteDataBaseHelper
  private var restaurantId: Int = -1
  private val moNavMenu = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId){
            R.id.firstFragment -> {
                supportFragmentManager.commit{
                    replace<FirstFragment>(R.id.fragment_container)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.secondFragment -> {
                supportFragmentManager.commit{
                    replace<SecondFragment>(R.id.fragment_container)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.tirdFragment -> {
                supportFragmentManager.commit{
                    replace<cenaFragment>(R.id.fragment_container)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }
    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_carta)

        biteDataBaseHelper = BiteDataBaseHelper(this)
        // Recibir el ID del restaurante desde el Intent
        restaurantId = intent.getIntExtra("RESTAURANT_ID", -1)

        navegation = findViewById(R.id.navegacionMenu)
        navegation.setOnNavigationItemSelectedListener(moNavMenu)

        supportFragmentManager.commit {
            replace<FirstFragment>(R.id.fragment_container)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }

        // Cargar información del restaurante si es necesario
        if (restaurantId != -1) {
            cargarInformacionRestaurante(restaurantId)
        }

        val volver = findViewById<LinearLayout>(R.id.volver)
        val cartera = findViewById<LinearLayout>(R.id.btnCartera)
        val cuenta = findViewById<LinearLayout>(R.id.btnCuenta)

        volver.setOnClickListener{
            val intent = Intent(this, HomeActivity::class.java)
            finish()
        }
        cartera.setOnClickListener{
            val intent3 = Intent(this, CarritoActivity::class.java)
            startActivity(intent3)
            finish()
        }
        cuenta.setOnClickListener{
            val intent4 = Intent(this, CuentaActivity::class.java)
            startActivity(intent4)
            finish()
        }

    }
    private fun cargarInformacionRestaurante(restaurantId: Int) {
        // Aquí puedes cargar información del restaurante desde la base de datos
        val restaurant = biteDataBaseHelper.ObtenerRestaurants().find { it.id == restaurantId }

        val bundle = Bundle()
        bundle.putParcelable("RESTAURANT_DATA", restaurant) // Asumiendo que ModelRestaurant implementa Parcelable

        val fragment = FirstFragment() // O el fragmento que desees
        fragment.arguments = bundle

        // Reemplazar el fragmento con la información del restaurante
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }
    }}