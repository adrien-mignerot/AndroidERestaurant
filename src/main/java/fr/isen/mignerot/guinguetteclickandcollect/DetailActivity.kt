package fr.isen.mignerot.guinguetteclickandcollect

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import fr.isen.mignerot.guinguetteclickandcollect.databinding.ActivityDetailBinding
import fr.isen.mignerot.guinguetteclickandcollect.model.Basket
import fr.isen.mignerot.guinguetteclickandcollect.model.Dish
import fr.isen.mignerot.guinguetteclickandcollect.model.ItemBasket
import java.io.File

private lateinit var binding: ActivityDetailBinding

class DetailActivity : BaseActivity() {
    private var quantity = 1
    private var orderMax = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra(CategoryActivity.DISH) as Dish
        binding.detailTitle.text = dish.title
        binding.detailIngredients.text = dish.ingredients.joinToString(", ") { it.name }

        binding.detailRemove.visibility = View.GONE

        val total = dish.getPrice().toFloat()*quantity

        binding.detailButton.text = getString(R.string.total).toUpperCase() + " $total " + getString(R.string.devise)

        dish.getAllPictures()?.let {
            binding.detailViewPager.adapter = DetailViewAdapter(this, it)
        }

        if(dish.getAllPictures() == null){
            val image = dish.getFirstPicture()
            if(image != null && image.isNotEmpty()){
                Picasso.get()
                        .load(image)
                        .into(binding.detailPicture)
            } else {
                Picasso.get()
                        .load(R.drawable.not_found)
                        .into(binding.detailPicture)
            }
        }

        binding.detailAdd.setOnClickListener {
            if(quantity < orderMax){
                quantity++
                if(quantity > 1)
                    binding.detailRemove.visibility = View.VISIBLE
                binding.detailNumber.text = quantity.toString()
            } else {
                Toast.makeText(applicationContext, getString(R.string.maxOrder) + " $orderMax ${dish.title}.", Toast.LENGTH_LONG).show()
                binding.detailAdd.visibility = View.GONE
            }
            val total = dish.getPrice().toFloat()*quantity
            binding.detailButton.text = getString(R.string.total).toUpperCase() + " $total " + getString(R.string.devise)
        }

        binding.detailRemove.setOnClickListener {
            if(quantity > 1){
                quantity--
                if(quantity == 1)
                    binding.detailRemove.visibility = View.GONE
                if(quantity < orderMax)
                    binding.detailAdd.visibility = View.VISIBLE
                binding.detailNumber.text = quantity.toString()
            }
            val total = dish.getPrice().toFloat()*quantity
            binding.detailButton.text = getString(R.string.total).toUpperCase() + " $total " + getString(R.string.devise)
        }

        binding.detailButton.setOnClickListener {
                saveInBasket(it, quantity, dish)
        }
    }

    override fun onResume() {
        invalidateOptionsMenu()
        super.onResume()
    }

    private fun saveInBasket(view: View, quantity: Int, dish: Dish) {
        val file = File(cacheDir.absolutePath + "UserCart.Json")

        if(file.exists()){
            val basket = GsonBuilder().create().fromJson(file.readText(), Basket::class.java)

            basket.items.firstOrNull { it.dish == dish }?.let {
                it.quantity += quantity
            } ?: run {
                basket.items.add(ItemBasket(quantity, dish))
            }
            saveInMemory(basket, file)
        } else {
            val basket = Basket(mutableListOf(ItemBasket(quantity, dish)))
            saveInMemory(basket, file)
        }
        if(quantity == 1)
            Snackbar.make( view, getString(R.string.one) + " ${dish.title} " + getString(R.string.addToBasket), Snackbar.LENGTH_SHORT).show()
        else
            Snackbar.make( view, "$quantity ${dish.title} " + getString(R.string.addToBasket), Snackbar.LENGTH_SHORT).show()
    }

    private fun saveInMemory(basket: Basket, file: File) {
        saveDishCount(basket)
        file.writeText(GsonBuilder().create().toJson(basket))
    }

    private fun saveDishCount(basket: Basket) {
        val count = basket.items.sumOf { it.quantity }

        val sharedPreferences = getSharedPreferences(APP_PREFS, MODE_PRIVATE)
        sharedPreferences.edit().putInt(BASKET_COUNT, count).apply()
        invalidateOptionsMenu()
    }

    companion object {
        const val APP_PREFS = "app_prefs"
        const val BASKET_COUNT = "basket_count"
    }
}