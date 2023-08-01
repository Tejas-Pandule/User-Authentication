package com.tejas.authenticationapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.tejas.authenticationapplication.databinding.ActivityMainBinding
import com.tejas.authenticationapplication.databinding.ActivityOtpactivityBinding

class OTPActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOtpactivityBinding
    private lateinit var builder: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {
            if (binding.userOtp.text!!.isEmpty()) {
                Toast.makeText(this, "Please provide OTP", Toast.LENGTH_SHORT).show()
            }
            else{
                verifyUser(binding.userOtp.text.toString())
            }
        }
    }


    private fun verifyUser(OTP: String) {
        builder = AlertDialog.Builder(this)
            .setTitle("Loading...!")
            .setMessage("Please Wait")
            .setCancelable(false)
            .create()
        builder.show()

        val credential = PhoneAuthProvider.getCredential(intent.getStringExtra("verificationId")!!, OTP)

        signInWithPhoneAuthCredential(credential)

    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    builder.dismiss()
                    startActivity(Intent(this,MainActivity::class.java))
                    finish()


                } else {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                }
            }
    }
}