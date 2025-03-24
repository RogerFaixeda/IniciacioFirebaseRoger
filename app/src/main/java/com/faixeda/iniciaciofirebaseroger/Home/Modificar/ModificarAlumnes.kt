package com.faixeda.iniciaciofirebaseroger.Home.Modificar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.faixeda.iniciaciofirebaseroger.Home.Data.AlumneModel
import com.faixeda.iniciaciofirebaseroger.Home.SharedViewModel
import com.faixeda.iniciaciofirebaseroger.Home.Veure.VeureAlumnesViewModel
import com.faixeda.iniciaciofirebaseroger.databinding.FragmentModificarAlumnesBinding
import com.google.firebase.firestore.FirebaseFirestore

class ModificarAlumnes : Fragment() {
    private lateinit var db: FirebaseFirestore
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel: ModificarAlumnesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentModificarAlumnesBinding.inflate(inflater, container, false)

        binding.editTextDni.setText(sharedViewModel.dni)
        binding.editTextNom.setText(sharedViewModel.nom)
        binding.editTextEdat.setText(sharedViewModel.edat.toString())

        binding.buttonModificar.setOnClickListener {
            val dni = binding.editTextDni.text.toString()
            val nom = binding.editTextNom.text.toString()
            val edat = binding.editTextEdat.text.toString().toIntOrNull() ?: 0

            val alumne : AlumneModel = AlumneModel(dni, nom, edat)

            viewModel.modificarAlumne(alumne)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}