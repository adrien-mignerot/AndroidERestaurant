package fr.isen.mignerot.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.mignerot.androiderestaurant.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding

class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.starterButton.setOnClickListener {
            //Toast.makeText(applicationContext, "Click sur entrées !", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra(CATEGORY, getString(R.string.starter_button))
            startActivity(intent)
        }

        binding.mainCourseButton.setOnClickListener {
            //Toast.makeText(applicationContext, "Click sur plats !", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra(CATEGORY, getString(R.string.main_course_button))
            startActivity(intent)
        }

        binding.dessertButton.setOnClickListener {
            //Toast.makeText(applicationContext, "Click sur déserts !", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra(CATEGORY, getString(R.string.dessert_button))
            startActivity(intent)
        }


        //mainTitle.typeface = ResourcesCompact.getFont(this, R.font.entsani)
        //mainTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50.toFloat())

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"destroyed")
    }

    companion object {
        const val TAG = "HomeActivity"
        const val CATEGORY = "category"
    }
}