package tacora.ronald.tacoraronaldo_o.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.bottomnavigation.BottomNavigationView
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.frangments.FirstFragment
import tacora.ronald.tacoraronaldo_o.frangments.SecondFragment
import tacora.ronald.tacoraronaldo_o.frangments.cenaFragment

class MenuCartaActivity : AppCompatActivity() {
  lateinit var navegation : BottomNavigationView
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

        navegation = findViewById(R.id.navegacionMenu)
        navegation.setOnNavigationItemSelectedListener(moNavMenu)

        supportFragmentManager.commit{
            replace<FirstFragment>(R.id.fragment_container)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
    }
}