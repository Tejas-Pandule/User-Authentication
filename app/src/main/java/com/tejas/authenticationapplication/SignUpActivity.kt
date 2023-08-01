package com.tejas.authenticationapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tejas.authenticationapplication.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "SignUp"

        binding.button2.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        binding.button.setOnClickListener {
            validateUser()
        }
    }

    private fun validateUser() {
        if (binding.userNumber.text!!.isEmpty() || binding.userName.text!!.isEmpty()){
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
        }else{

            val preferences = this.getSharedPreferences("user", MODE_PRIVATE)
            val editor = preferences.edit()
            editor.putString("number",binding.userNumber.text.toString())
            editor.apply()

            Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }



    }
}