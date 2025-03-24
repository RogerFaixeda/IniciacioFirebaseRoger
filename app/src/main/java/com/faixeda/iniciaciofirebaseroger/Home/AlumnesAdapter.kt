package com.faixeda.iniciaciofirebaseroger.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.faixeda.iniciaciofirebaseroger.Home.Data.AlumneModel
import com.faixeda.iniciaciofirebaseroger.R

class AlumnesAdapter(private var alumnesList: List<AlumneModel>, private val itemClick: (AlumneModel) -> Unit) : RecyclerView.Adapter<AlumnesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val aluneModel = alumnesList[position]

        // sets the text to the textview from our itemHolder class
        holder.textViewNom.text = aluneModel.nom
        holder.textViewDni.text = aluneModel.dni
        holder.textViewEdat.text = aluneModel.edat.toString()

        holder.itemView.setOnClickListener{
            itemClick(aluneModel)
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return alumnesList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textViewDni: TextView = itemView.findViewById(R.id.tvDni)   // Actualizar a tvDni
        val textViewNom: TextView = itemView.findViewById(R.id.tvNombre) // Actualizar a tvNombre
        val textViewEdat: TextView = itemView.findViewById(R.id.tvEdad)  // Actualizar a tvEdad
    }
}