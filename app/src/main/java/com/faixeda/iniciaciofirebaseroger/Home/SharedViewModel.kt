package com.faixeda.iniciaciofirebaseroger.Home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    var dni: String = ""

    var nom: String = ""

    var edat: Int = 0

    fun setInfoUser(dni: String, nom: String, edat: Int) {
        this.dni = dni
        this.nom = nom
        this.edat = edat
    }
}