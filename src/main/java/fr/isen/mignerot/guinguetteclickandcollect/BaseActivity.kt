package fr.isen.mignerot.guinguetteclickandcollect

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import fr.isen.mignerot.guinguetteclickandcollect.DetailActivity.Companion.APP_PREFS
import fr.isen.mignerot.guinguetteclickandcollect.DetailActivity.Companion.BASKET_COUNT

open class BaseActivity: AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        val menuView = menu?.findItem(R.id.shoppingCart)?.actionView
        val count = menuView?.findViewById<TextView>(R.id.nbItems)
        val sharedPrefrences = getSharedPreferences(APP_PREFS, MODE_PRIVATE)
        val quantity = sharedPrefrences.getInt(BASKET_COUNT, 0)

        if (quantity == 0) {
            count?.isVisible = false
        } else {
            count?.text = quantity.toString()
            count?.isVisible = true
        }

        menuView?.setOnClickListener{
            if(quantity > 0) {
                startActivity(Intent(this, BasketActivity::class.java))
            } else {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Votre panier est vide...")
                        .setCancelable(false)
                        .setPositiveButton("Ok", DialogInterface.OnClickListener {
                            dialog, _ -> dialog.cancel()
                        })
                val alert = dialogBuilder.create()
                alert.setTitle("Oups !")
                alert.show()
            }
        }

        return super.onCreateOptionsMenu(menu)
    }
}