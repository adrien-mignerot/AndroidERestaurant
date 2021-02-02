package fr.isen.mignerot.androiderestaurant


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import fr.isen.mignerot.androiderestaurant.databinding.ActivityBasketBinding
import java.io.File

private lateinit var binding: ActivityBasketBinding

class BasketActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        readFile()
    }

    private fun readFile() {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val file = File(cacheDir.absolutePath + "UserCart.Json")
        if (file.exists()) {
            val basket = gson.fromJson(file.readText(), Basket::class.java)
            val basketRecycler = binding.basketList
            basketRecycler.adapter = BasketListAdapter(basket.items.toMutableList()) {
                basket.items.remove(it)
                resetBasket(basket)
                invalidateOptionsMenu()
            }
            binding.basketLoading.visibility = View.GONE
            basketRecycler.layoutManager = LinearLayoutManager(this)
        }
    }
    private fun resetBasket(basket: Basket) {
        val file = File(cacheDir.absolutePath + "UserCart.Json")
        saveInMemory(basket, file)
    }

    private fun saveInMemory(basket: Basket, file: File){
        saveDishCount(basket)
        file.writeText(GsonBuilder().create().toJson(basket))
    }

    private fun saveDishCount(basket: Basket){
        val count = basket.items.sumOf { it.quantity }
        val sharedPreferences = getSharedPreferences(APP_PREFS, MODE_PRIVATE)
        sharedPreferences.edit().putInt(BASKET_COUNT, count).apply()
        if(count <= 0)
            startActivity(Intent(this, HomeActivity::class.java))
    }

    companion object {
        const val APP_PREFS = "app_prefs"
        const val BASKET_COUNT = "basket_count"
    }
}