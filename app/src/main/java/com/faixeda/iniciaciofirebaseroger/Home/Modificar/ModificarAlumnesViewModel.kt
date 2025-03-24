package com.faixeda.iniciaciofirebaseroger.Home.Modificar

import android.util.Log
import androidx.lifecycle.ViewModel
import com.faixeda.iniciaciofirebaseroger.Home.Data.AlumneModel
import com.google.firebase.firestore.FirebaseFirestore

class ModificarAlumnesViewModel : ViewModel() {
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun modificarAlumne(alumne: AlumneModel) {
        db.collection("alumnes").document(alumne.dni)
            .set(alumne)
            .addOnSuccessListener {
                Log.d("Firebase", "Alumno actualizado correctamente")
            }
            .addOnFailureListener { e ->
                Log.e("Firebase", "Error al actualizar el alumno", e)
            }
    }


}