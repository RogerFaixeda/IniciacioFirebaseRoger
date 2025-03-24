package com.faixeda.iniciaciofirebaseroger

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.faixeda.iniciaciofirebaseroger.Home.HomeActivity
import com.faixeda.iniciaciofirebaseroger.databinding.ActivityRegistreBinding
import com.google.firebase.auth.FirebaseAuth

class RegistreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding: ActivityRegistreBinding = DataBindingUtil.setContentView(this, R.layout.activity_registre)

        binding.buttonRegistre.setOnClickListener{
            val email = binding.editTextNom.text.toString()
            val password = binding.editTextPassword.text.toString()

            registrarUsuario(email, password) { success, error ->
                if (success) {
                    Toast.makeText(this, "Registre exitos", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                } else {
                    Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.textViewLogin.setOnClickListener{
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        }

    }
    private fun registrarUsuario(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.message)
                }
            }
    }
}