package com.faixeda.iniciaciofirebaseroger.Home.Eliminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.faixeda.iniciaciofirebaseroger.R
import com.faixeda.iniciaciofirebaseroger.databinding.FragmentEliminarAlumnesBinding
import com.google.firebase.firestore.FirebaseFirestore


class EliminarAlumnes : Fragment() {

    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEliminarAlumnesBinding.inflate(inflater, container, false)
        db = FirebaseFirestore.getInstance()

        binding.button.setOnClickListener {
            val dni = binding.editTextDni.text.toString()

            if (dni.isNotEmpty()) {
                db.collection("alumnes").document(dni).delete()
                    .addOnSuccessListener {
                        Toast.makeText(context, "Alumne eliminat", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_eliminarAlumnes_to_veureAlumnes)
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Error en eliminar l'alumne", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(context, "Introdueix un DNI", Toast.LENGTH_SHORT).show()
            }
        }



        // Inflate the layout for this fragment
        return binding.root
    }

}