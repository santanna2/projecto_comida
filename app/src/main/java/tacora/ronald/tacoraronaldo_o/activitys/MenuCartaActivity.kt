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
  private lateinit var categoria: String

  private val moNavMenu = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId){
            R.id.firstFragment -> {
                val fragment = FirstFragment().apply {
                    arguments = Bundle().apply {
                        putInt("RESTAURANT_ID", restaurantId)
                        putString("CATEGORIA", categoria)
                    }
                }
                supportFragmentManager.commit {
                    replace(R.id.fragment_container, fragment)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.secondFragment -> {
                val fragment = SecondFragment().apply {
                    arguments = Bundle().apply {
                        putInt("RESTAURANT_ID", restaurantId)
                        putString("CATEGORIA", "Almuerzo")
                    }
                }
                supportFragmentManager.commit {
                    replace(R.id.fragment_container, fragment)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.tirdFragment -> {
                val fragment = cenaFragment().apply {
                    arguments = Bundle().apply {
                        putInt("RESTAURANT_ID", restaurantId)
                        putString("CATEGORIA", "Cena")
                    }
                }
                supportFragmentManager.commit {
                    replace(R.id.fragment_container, fragment)
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
        restaurantId = intent.getIntExtra("RESTAURANT_ID", -1)
        categoria = intent.getStringExtra("CATEGORIA") ?: "N/A"

        navegation = findViewById(R.id.navegacionMenu)
        navegation.setOnNavigationItemSelectedListener(moNavMenu)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragment_container, FirstFragment().apply {
                    arguments = Bundle().apply {
                        putInt("RESTAURANT_ID", restaurantId)
                        putString("CATEGORIA", categoria)
                    }
                })
                setReorderingAllowed(true)
                addToBackStack("replacement")
            }
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
            val intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
            finish()
        }
        cuenta.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    private fun cargarInformacionRestaurante(restaurantId: Int) {
        // Aquí puedes cargar información del restaurante desde la base de datos
        val restaurant = biteDataBaseHelper.ObtenerRestaurants().find { it.id == restaurantId }

        val bundle = Bundle()
        bundle.putParcelable("RESTAURANT_DATA", restaurant)

        val fragment = FirstFragment()
        fragment.arguments = bundle

        // Reemplazar el fragmento con la información del restaurante
        supportFragmentManager.commit {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }
    }}