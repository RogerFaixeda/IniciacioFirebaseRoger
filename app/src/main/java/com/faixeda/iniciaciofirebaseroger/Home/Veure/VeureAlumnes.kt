package com.faixeda.iniciaciofirebaseroger.Home.Veure

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.faixeda.iniciaciofirebaseroger.Home.AlumnesAdapter
import com.faixeda.iniciaciofirebaseroger.Home.Data.AlumneModel
import com.faixeda.iniciaciofirebaseroger.Home.SharedViewModel
import com.faixeda.iniciaciofirebaseroger.R
import com.faixeda.iniciaciofirebaseroger.databinding.FragmentVeureAlumnesBinding
import com.google.firebase.firestore.FirebaseFirestore


class VeureAlumnes : Fragment() {
    private lateinit var db: FirebaseFirestore
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val viewModel: VeureAlumnesViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVeureAlumnesBinding.inflate(inflater, container, false)
        db = FirebaseFirestore.getInstance()

        //RecyclerView
        val recyclerview = binding.recyclerView

        recyclerview.layoutManager = LinearLayoutManager(this.activity)
        viewModel.getAlumnes()
        val alumnes = viewModel.alumnes


        val adapterRecycler = AlumnesAdapter(alumnes) { alumnesModel ->
            sharedViewModel.setInfoUser(alumnesModel.dni, alumnesModel.nom, alumnesModel.edat)
            findNavController().navigate(R.id.action_veureAlumnes_to_modificar_alumnes)
        }
        recyclerview.adapter = adapterRecycler

        binding.buttonAfegir.setOnClickListener{
            findNavController().navigate(R.id.action_veureAlumnes_to_afegir_alumnes)
        }
        binding.buttonEliminar.setOnClickListener{
            findNavController().navigate(R.id.action_veureAlumnes_to_eliminarAlumnes)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}