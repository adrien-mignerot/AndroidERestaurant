package fr.isen.mignerot.guinguetteclickandcollect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.mignerot.guinguetteclickandcollect.databinding.ActivityOrderResultBinding
import fr.isen.mignerot.guinguetteclickandcollect.model.Basket
import fr.isen.mignerot.guinguetteclickandcollect.model.OrderResponse
import org.json.JSONObject
import java.io.File

private lateinit var binding: ActivityOrderResultBinding

class OrderResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        doOrder()

        binding.orderProgress.visibility = View.GONE
        binding.orderTitle.visibility = View.VISIBLE
        binding.orderDesc.visibility = View.VISIBLE
        binding.orderHandPic.visibility = View.VISIBLE
        binding.orderToquePic.visibility = View.VISIBLE
        binding.goHomeButton.visibility = View.VISIBLE

        binding.goHomeButton.setOnClickListener {
            finish()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun readBasket(): Basket? {
        val file = File(cacheDir.absolutePath + BasketActivity.JSON_FILE)

        return if (file.exists()) {
            val orderJson = Gson().fromJson(file.readText(), Basket::class.java) as Basket
            orderJson
        } else {
            null
        }
    }

    private fun getUserId(): Int {
        val sharedPreferences = getSharedPreferences(DetailActivity.APP_PREFS, MODE_PRIVATE)
        return sharedPreferences.getInt(BasketActivity.ID_CLIENT, -1)
    }

    private fun doOrder() {
        val queue = Volley.newRequestQueue(this)
        val order = readBasket()

        var jsonObject = JSONObject()
        jsonObject.put("id_shop", "1")
        jsonObject.put("id_user", getUserId())
        jsonObject.put("msg", order)

        val stringRequest = JsonObjectRequest(Request.Method.POST, URL_API, jsonObject,
                { response ->
                    Log.i(BasketActivity.TAG, "response -> $response")
                    val gson: OrderResponse = Gson().fromJson(response.toString(), OrderResponse::class.java)
                    (gson.code == 200).apply {
                        val file = File(cacheDir.absolutePath + BasketActivity.JSON_FILE)
                        file.delete()
                        val sharedPreference = getSharedPreferences(DetailActivity.APP_PREFS, MODE_PRIVATE)
                        sharedPreference.edit().putInt(DetailActivity.BASKET_COUNT, 0).apply()
                        invalidateOptionsMenu()
                    }
                    Log.i(TAG, "response code  -> $gson")
                    binding.orderTitle.text = getString(R.string.thanks)
                    binding.orderDesc.text = getString(R.string.orderSuccess)
                },
                { error ->
                    Log.i(TAG, "Error $error")
                    binding.orderTitle.text = getString(R.string.oups)
                    binding.orderDesc.text = getString(R.string.orderError)
                })
        queue.add(stringRequest)
    }

    companion object {
        const val TAG = "OrderResultActivity"
        const val URL_API = "http://test.api.catering.bluecodegames.com/user/order"
    }
}