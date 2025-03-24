package com.faixeda.iniciaciofirebaseroger.Home.Veure

import androidx.lifecycle.ViewModel
import com.faixeda.iniciaciofirebaseroger.Home.Data.AlumneModel
import com.google.firebase.firestore.FirebaseFirestore

class VeureAlumnesViewModel : ViewModel(){
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private var _alumnes: List<AlumneModel> = emptyList()
    val alumnes: List<AlumneModel>
        get() = _alumnes

    fun getAlumnes(){
        db.collection("alumnes").get().addOnSuccessListener { result ->
            _alumnes = result.map { it.toObject(AlumneModel::class.java) }
        }
    }
}