package fr.isen.mignerot.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import fr.isen.mignerot.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.mignerot.androiderestaurant.model.Dish

private lateinit var binding: ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private var quantity = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra(CategoryActivity.DISH) as Dish
        binding.detailTitle.text = dish.title
        binding.detailIngredients.text = dish.ingredients.joinToString(", ") { it.name }

        binding.detailRemove.visibility = View.GONE

        val total = dish.getPrice().toFloat()*quantity

        binding.detailButton.text = "Total ".toUpperCase() + total.toString() + " €"

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
            if(quantity < 10){
                quantity++
                if(quantity > 1)
                    binding.detailRemove.visibility = View.VISIBLE
                binding.detailNumber.text = quantity.toString()
            } else {
                Toast.makeText(applicationContext, "Vous pouvez commander au maximum 10 ${dish.title}.", Toast.LENGTH_LONG).show()
                binding.detailAdd.visibility = View.GONE
            }
            val total = dish.getPrice().toFloat()*quantity
            binding.detailButton.text = "Total ".toUpperCase() + total.toString() + " €"
        }

        binding.detailRemove.setOnClickListener {
            if(quantity > 1){
                quantity--
                if(quantity == 1)
                    binding.detailRemove.visibility = View.GONE
                if(quantity < 10)
                    binding.detailAdd.visibility = View.VISIBLE
                binding.detailNumber.text = quantity.toString()
            }
            val total = dish.getPrice().toFloat()*quantity
            binding.detailButton.text = "Total ".toUpperCase() + total.toString() + " €"
        }
    }
}