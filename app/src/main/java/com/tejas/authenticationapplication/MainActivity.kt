package com.tejas.authenticationapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.tejas.authenticationapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (FirebaseAuth.getInstance().currentUser==null){
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }

        binding.textView2.movementMethod= LinkMovementMethod.getInstance()


        val auth = FirebaseAuth.getInstance()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Log Out")
        builder.setMessage("Are you sure, You want to Logout?")
        builder.setIcon(R.drawable.logout)
        builder.create()
        builder.setPositiveButton("Yes"){ builder, _ ->
            auth.signOut()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()


        }
        builder.setNegativeButton("No"){ builder, _ ->
            builder.dismiss()
        }

        binding.button3.setOnClickListener {
            builder.show()
        }







    }
}