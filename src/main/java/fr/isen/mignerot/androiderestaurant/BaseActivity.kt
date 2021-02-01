package fr.isen.mignerot.androiderestaurant

import android.content.Intent
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.isen.mignerot.androiderestaurant.DetailActivity.Companion.APP_PREFS
import fr.isen.mignerot.androiderestaurant.DetailActivity.Companion.BASKET_COUNT

open class BaseActivity: AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        val menuView = menu?.findItem(R.id.shoppingCart)?.actionView
        val count = menuView?.findViewById<TextView>(R.id.nbItems)
        val sharedPrefrences = getSharedPreferences(APP_PREFS, MODE_PRIVATE)
        count?.text = sharedPrefrences.getInt(BASKET_COUNT, 0).toString()

        menuView?.setOnClickListener{
            startActivity(Intent(this, BasketActivity::class.java))
        }

        return super.onCreateOptionsMenu(menu)
    }
}