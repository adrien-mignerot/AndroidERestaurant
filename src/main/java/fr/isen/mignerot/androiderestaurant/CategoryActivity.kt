package fr.isen.mignerot.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.mignerot.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.mignerot.androiderestaurant.databinding.ActivityHomeBinding
import org.json.JSONObject

private lateinit var binding: ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.categoryTitle.text = intent.getStringExtra(HomeActivity.CATEGORY)

        binding.listCategory.layoutManager = LinearLayoutManager(this)

        binding.listCategory.adapter = CategoryListAdapter(resources.getStringArray(R.array.dish_name_array).toList()) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("category", it)
            startActivity(intent)
        }
    }
    /*private fun loadDishesFromCategory(category: String) {
        val url = "http://test.api.catering.bluecodegames.com/" + "menu"

        val jsonData = JSONObject()
        jsonData.put("id_shop", "1")

        val stringRequest = JsonObjectRequest(
                Request.Method.POST, url, jsonData, { response ->
                    val menu = GsonBuilder().create().fromJson(response.toString(), MenuResult::class.java)
            menu.data.firstOrNull { it.name == category }?.dishes?.let {
                displayDishes(it)
            } ?: run {
                binding.categoryLoading.visibility = View.GONE
                binding.categoryErrorMessage.text = "Aucun plat proposé à la carte du restaurant pour le moment"
            }
        },
                {
                    Log.d("CategoryActivity", "That didn't work ! ${it}")
                    binding.categoryLoading.visibility = View.GONE
                    binding.categoryErrorMessage.text = "Impossible de récupérer la liste des {category}. Veuillez réessayez..."
                })
    }

    private fun displayDishes(dishes: List<Dish>) {
        binding.categoryLoading.visibility = View.GONE
        binding.categoryList.layoutManager = LinearLayoutManager(this)
        binding.categoryList.adapter = CategoryAdapter(dishes) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("Dish", it)
            startActivity(intent)
        }
    }*/

}