package fr.isen.mignerot.guinguetteclickandcollect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.mignerot.guinguetteclickandcollect.databinding.ActivityLoginBinding
import fr.isen.mignerot.guinguetteclickandcollect.model.Login
import fr.isen.mignerot.guinguetteclickandcollect.model.RegisterResponse
import org.json.JSONObject

private lateinit var binding: ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginAlreadyButton.setOnClickListener {
            finish()
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.loginButton.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty()) {
                val user = Login(email, password)
                login(user)
            } else
                Toast.makeText(applicationContext, R.string.fillAllInput, Toast.LENGTH_SHORT).show()
        }
    }

    private fun login(user: Login) {
        var jsonObject = JSONObject()
        jsonObject = user.toLoginParameters( jsonObject.put(RegisterActivity.ID_SHOP, "1"))

        val stringRequest = JsonObjectRequest( Request.Method.POST, URL_API, jsonObject,
                { response -> val code = response["code"].toString()
                    if(code != "NOK") {
                        Gson().fromJson(response["data"].toString(), RegisterResponse::class.java).let {

                            val sharedPreferences = getSharedPreferences(DetailActivity.APP_PREFS, MODE_PRIVATE)
                            sharedPreferences.edit().putInt(RegisterActivity.ID_CLIENT, it.id).apply()
                            Log.i(TAG, "gson -> $it")
                            Toast.makeText(applicationContext, R.string.connected, Toast.LENGTH_SHORT).show()
                            finish()
                            startActivity(Intent(this, BasketActivity::class.java))
                        }
                    } else
                    Toast.makeText( this, R.string.ivalidLogin, Toast.LENGTH_SHORT).show()
                },
                { error ->
                    Log.i(TAG, "Error $error")
                })
        Volley.newRequestQueue(this).add(stringRequest)
    }

    companion object {
        const val TAG = "LoginActivity"
        const val URL_API = "http://test.api.catering.bluecodegames.com/user/login"
    }
}