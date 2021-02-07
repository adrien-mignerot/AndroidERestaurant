package fr.isen.mignerot.guinguetteclickandcollect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import fr.isen.mignerot.guinguetteclickandcollect.databinding.ActivityCategoryBinding
import fr.isen.mignerot.guinguetteclickandcollect.model.DataResult
import fr.isen.mignerot.guinguetteclickandcollect.model.Dish
import org.json.JSONObject

private lateinit var binding: ActivityCategoryBinding

class CategoryActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.categoryTitle.text = intent.getStringExtra(HomeActivity.CATEGORY)

        binding.categoryList.layoutManager = LinearLayoutManager(this)

        loadDishesFromCategory(intent.getStringExtra("category") ?: "")

        /*binding.categoryList.adapter = CategoryListAdapter(resources.getStringArray(R.array.dish_name_array).toList()) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("category", it)
            startActivity(intent)
        }*/
    }

    override fun onResume() {
        invalidateOptionsMenu()
        super.onResume()
    }

    private fun loadDishesFromCategory(category: String) {
        val url = "http://test.api.catering.bluecodegames.com/" + "menu"

        val jsonData = JSONObject()
        jsonData.put("id_shop", "1")

        val stringRequest = JsonObjectRequest(
                Request.Method.POST, url, jsonData, { response ->
            val menu = GsonBuilder().create().fromJson(response.toString(), DataResult::class.java)
            menu.data.firstOrNull { it.name == category }?.dishes?.let {
                displayDishes(it)
            } ?: run {
                binding.categoryLoading.visibility = View.GONE
                binding.categoryErrorMessage.text = "Aucun(e) ${category.toLowerCase()} proposé(e)s pour le moment"
            }
        },
                {
                    Log.d("CategoryActivity", "Erreur Volley : $it")
                    binding.categoryLoading.visibility = View.GONE
                    binding.categoryErrorMessage.text = "Impossible de récupérer la liste des ${category.toLowerCase()}."
                })
        Volley.newRequestQueue(this).add(stringRequest)
    }

    private fun displayDishes(dishes: List<Dish>) {
        binding.categoryLoading.visibility = View.GONE
        binding.categoryList.layoutManager = LinearLayoutManager(this)
        binding.categoryList.adapter = CategoryListAdapter(dishes) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DISH, it)
            startActivity(intent)
        }
    }

    companion object {
        const val DISH = "Dish"
    }
}