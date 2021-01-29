package fr.isen.mignerot.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import fr.isen.mignerot.androiderestaurant.databinding.ActivityDetailBinding
import fr.isen.mignerot.androiderestaurant.model.Dish

private lateinit var binding: ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dish = intent.getSerializableExtra(CategoryActivity.DISH) as Dish
        binding.detailTitle.text = dish.title
        binding.detailIngredients.text = dish.ingredients.joinToString(", ") { it.name }

        binding.detailButton.text = "Total ".toUpperCase() + dish.getFormattedPrice()

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
    }
}