package fr.isen.mignerot.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.mignerot.androiderestaurant.databinding.ActivityRegisterBinding

private lateinit var binding: ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //binding.registerButton.isEnabled = false

        //onChange ou Librarie

        binding.registerButton.setOnClickListener {
            val firstName = binding.registerFirstName.text.toString()
            val lastName = binding.registerLastName.text.toString()
            val email = binding.registerEmail.text.toString()
            val password = binding.registerPassword.text.toString()
            val address = binding.registerAddress.text.toString()
            if(firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && address.isNotEmpty())
                Toast.makeText(applicationContext, "Ok !", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext, R.string.fillAllInput, Toast.LENGTH_SHORT).show()
        }
    }
}