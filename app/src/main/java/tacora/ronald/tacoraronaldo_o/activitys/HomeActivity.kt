package tacora.ronald.tacoraronaldo_o.activitys

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tacora.ronald.tacoraronaldo_o.R
import tacora.ronald.tacoraronaldo_o.recyclers.RestaurantAdapter
import tacora.ronald.tacoraronaldo_o.recyclers.RestaurantCompany

class homeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val restauAdapter = RestaurantAdapter()
        val rv = findViewById<RecyclerView>(R.id.pasarelaRestaurant)

        rv.apply {
            adapter = restauAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }


        val ListaRestaurant: MutableList<RestaurantCompany> = mutableListOf()
            ListaRestaurant.add(RestaurantCompany("Restaurant 1", "Category 1", "https://example.com/image1.jpg"))
            ListaRestaurant.add(RestaurantCompany("Restaurant 2", "Category 2", "https://example.com/image2.jpg"))
            ListaRestaurant.add(RestaurantCompany("Restaurant 3", "Category 3", "https://example.com/image3.jpg"))
            ListaRestaurant.add(RestaurantCompany("Restaurant 4", "Category 4", "https://example.com/image4.jpg"))
            ListaRestaurant.add(RestaurantCompany("Restaurant 5", "Category 5", "https://example.com/image5.jpg"))
            ListaRestaurant.add(RestaurantCompany("maravilla","su_kasa","https://example.com/image6.jpg"))
            ListaRestaurant.add(RestaurantCompany("buena","wii","https://example.com/image5.jpg"))
            ListaRestaurant.add(RestaurantCompany("honda","su_nosekasa","https://example.com/image5.jpg"))
            ListaRestaurant.add(RestaurantCompany("juwewa","su_kaswaaa","https://example.com/image5.jpg"))
            ListaRestaurant.add(RestaurantCompany("juwean","seww","img"))


    restauAdapter.actualizarLista(ListaRestaurant)
    }
}