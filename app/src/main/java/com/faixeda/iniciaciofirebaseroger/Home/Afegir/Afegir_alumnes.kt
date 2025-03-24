package com.faixeda.iniciaciofirebaseroger.Home.Afegir

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.faixeda.iniciaciofirebaseroger.Home.Data.AlumneModel
import com.faixeda.iniciaciofirebaseroger.R
import com.faixeda.iniciaciofirebaseroger.databinding.FragmentAfegirAlumnesBinding
import com.google.firebase.firestore.FirebaseFirestore

class Afegir_alumnes : Fragment() {
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAfegirAlumnesBinding.inflate(inflater, container, false)
        db = FirebaseFirestore.getInstance()

        binding.buttonAfegir.setOnClickListener {
            val dni = binding.editTextDni.text.toString()
            val nombre = binding.editTextNom.text.toString()
            val edad = binding.editTextEdat.text.toString().toIntOrNull() ?: 0

            if (dni.isNotEmpty() && nombre.isNotEmpty() && edad > 0) {
                db.collection("alumnes").whereEqualTo("dni", dni).get()
                    .addOnSuccessListener { querySnapshot ->
                        if (querySnapshot.isEmpty) {
                            val alumno = AlumneModel(dni, nombre, edad)
                            db.collection("alumnes").document(dni).set(alumno)
                                .addOnSuccessListener {
                                    Toast.makeText(context, "Alumno añadido", Toast.LENGTH_SHORT).show()
                                    findNavController().navigate(R.id.action_afegir_alumnes_to_veureAlumnes)
                                }
                                .addOnFailureListener {
                                    Toast.makeText(context, "Error al añadir alumno", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(context, "El DNI ya existe", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }

}