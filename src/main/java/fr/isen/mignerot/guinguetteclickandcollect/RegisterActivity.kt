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
import fr.isen.mignerot.guinguetteclickandcollect.databinding.ActivityRegisterBinding
import fr.isen.mignerot.guinguetteclickandcollect.model.Register
import fr.isen.mignerot.guinguetteclickandcollect.model.RegisterResponse
import org.json.JSONObject

private lateinit var binding: ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.registerButton.isEnabled = false
        //onChange ou Librarie

        binding.registerAlreadyButton.setOnClickListener {
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.registerButton.setOnClickListener {
            val firstName = binding.registerFirstName.text.toString()
            val lastName = binding.registerLastName.text.toString()
            val email = binding.registerEmail.text.toString()
            val password = binding.registerPassword.text.toString()
            val passwordConfirm = binding.registerPasswordConfirm.text.toString()
            val address = binding.registerAddress.text.toString()
            if(firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty() && address.isNotEmpty()) {
                if(password == passwordConfirm) {
                    val register = Register(lastName, firstName, address, email, password)
                    Log.i( TAG, "register -> $register")
                    sendRegister(register)
                } else
                    Toast.makeText(applicationContext, R.string.notSamePassword, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, R.string.fillAllInput, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun sendRegister( user: Register) {

        var jsonObject = JSONObject()
        jsonObject = user.toRegisterParameters( jsonObject.put(ID_SHOP, "1"))

        val stringRequest = JsonObjectRequest( Request.Method.POST, URL_API, jsonObject,
                { response ->
                    Gson().fromJson(response["data"].toString(), RegisterResponse::class.java).let {
                        val sharedPreferences = getSharedPreferences(DetailActivity.APP_PREFS, MODE_PRIVATE)
                        sharedPreferences.edit().putInt(ID_CLIENT, it.id).apply()
                        Log.i(TAG, "gson -> $it")
                        Toast.makeText(applicationContext, R.string.connected, Toast.LENGTH_SHORT).show()
                        finish()
                        startActivity(Intent(this, BasketActivity::class.java))
                    }
                },
                { error ->
                    Log.i(TAG, "Error $error")
                })
        Volley.newRequestQueue(this).add(stringRequest)
    }

    companion object {
        const val TAG = "RegisterActivity"
        const val URL_API = "http://test.api.catering.bluecodegames.com/user/register"
        const val ID_CLIENT = "id_client"
        const val ID_SHOP = "id_shop"
    }
}