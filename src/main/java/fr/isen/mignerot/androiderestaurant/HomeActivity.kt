package fr.isen.mignerot.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.mignerot.androiderestaurant.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.starterButton.setOnClickListener {
            //Toast.makeText(applicationContext, "Click sur entrées !", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra(CATEGORY, getString(R.string.starter_title))
            startActivity(intent)
        }

        binding.mainCourseButton.setOnClickListener {
            //Toast.makeText(applicationContext, "Click sur plats !", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra(CATEGORY, getString(R.string.main_course_title))
            startActivity(intent)
        }

        binding.dessertButton.setOnClickListener {
            //Toast.makeText(applicationContext, "Click sur déserts !", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra(CATEGORY, getString(R.string.dessert_title))
            startActivity(intent)
        }

        //mainTitle.typeface = ResourcesCompact.getFont(this, R.font.entsani)
        //mainTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50.toFloat())


    }

    companion object {
        const val CATEGORY = "category"
    }
}