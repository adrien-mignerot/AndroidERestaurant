package fr.isen.mignerot.androiderestaurant

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import fr.isen.mignerot.androiderestaurant.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.starterButton.setOnClickListener {
            Toast.makeText(applicationContext, "Click sur entrées !", Toast.LENGTH_SHORT).show()
        }

        binding.mainCourseButton.setOnClickListener {
            Toast.makeText(applicationContext, "Click sur plats !", Toast.LENGTH_SHORT).show()
        }

        binding.dessertButton.setOnClickListener {
            Toast.makeText(applicationContext, "Click sur déserts !", Toast.LENGTH_SHORT).show()
        }

        //mainTitle.typeface = ResourcesCompact.getFont(this, R.font.entsani)
        //mainTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50.toFloat())


    }
}