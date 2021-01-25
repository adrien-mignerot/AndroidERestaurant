package fr.isen.mignerot.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import fr.isen.mignerot.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.mignerot.androiderestaurant.databinding.ActivityHomeBinding

private lateinit var binding: ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.categoryTitle.text = intent.getStringExtra(HomeActivity.CATEGORY)
    }
}