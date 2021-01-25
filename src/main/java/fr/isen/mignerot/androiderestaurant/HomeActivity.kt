package fr.isen.mignerot.androiderestaurant

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //mainTitle.typeface = ResourcesCompact.getFont(this, R.font.entsani)
        //mainTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50.toFloat())


    }
}