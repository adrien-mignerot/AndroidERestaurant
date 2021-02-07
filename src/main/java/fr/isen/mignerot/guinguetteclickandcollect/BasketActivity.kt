package fr.isen.mignerot.guinguetteclickandcollect


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import fr.isen.mignerot.guinguetteclickandcollect.databinding.ActivityBasketBinding
import java.io.File

private lateinit var binding: ActivityBasketBinding

class BasketActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        readFile()

        binding.basketButton.setOnClickListener {
            if( getUserId() > -1) {
                Log.i(TAG, "command item with uid ${getUserId()}")
                finish()
                val intent = Intent(this, OrderResultActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun readFile() {
        val gson = GsonBuilder().setPrettyPrinting().create()
        val file = File(cacheDir.absolutePath + JSON_FILE)
        if (file.exists()) {
            val basket = gson.fromJson(file.readText(), Basket::class.java)
            val basketRecycler = binding.basketList
            basketRecycler.adapter = BasketListAdapter(basket.items.toMutableList()) { it ->
                basket.items.remove(it)
                resetBasket(basket)
                invalidateOptionsMenu()
                val total = basket.items.sumOf { it.quantity * it.dish.getPrice().toDouble() }
                binding.basketTotalText.text = getString(R.string.total) + " $total €"
            }
            val total = basket.items.sumOf { it.quantity * it.dish.getPrice().toDouble() }
            binding.basketTotalText.text = getString(R.string.total) + " $total €"
            binding.basketLoading.visibility = View.GONE
            basketRecycler.layoutManager = LinearLayoutManager(this)
        }
    }

    private fun getUserId(): Int {
        val sharedPreferences = getSharedPreferences(DetailActivity.APP_PREFS, MODE_PRIVATE)
        return sharedPreferences.getInt(ID_CLIENT, -1)
    }

    private fun resetBasket(basket: Basket) {
        val file = File(cacheDir.absolutePath + JSON_FILE)
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
        const val TAG = "BasketActivity"
        const val JSON_FILE = "UserCart.Json"
        const val APP_PREFS = "app_prefs"
        const val ID_CLIENT = "id_client"
        const val BASKET_COUNT = "basket_count"
    }
}