package com.faixeda.iniciaciofirebaseroger

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.faixeda.iniciaciofirebaseroger.Home.HomeActivity
import com.faixeda.iniciaciofirebaseroger.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextNom.text.toString()
            val password = binding.editTextPassword.text.toString()

            iniciarSesion(email, password) { success, error ->
                if (success) {
                    Toast.makeText(this, "Login exitos", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                } else {
                    Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
                }
            }
        }


        binding.textViewRegistre.setOnClickListener{
            startActivity(Intent(applicationContext, RegistreActivity::class.java))
        }
    }

    private fun iniciarSesion(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)

                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }
}