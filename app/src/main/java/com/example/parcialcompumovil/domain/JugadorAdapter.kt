package com.example.parcialcompumovil.domain

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.parcialcompumovil.R

class JugadorAdapter(context: Context, jugadorList: List<Jugador>) :
    ArrayAdapter<Jugador>(context, 0, jugadorList.toTypedArray()) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.jugador_adapter, parent, false)
        }

        val currentItem = getItem(position)

        val tvNombre = itemView?.findViewById<TextView>(R.id.tvNombre)
        val tvNacimiento = itemView?.findViewById<TextView>(R.id.tvNacimiento)

        tvNombre?.text = currentItem?.nombre
        tvNacimiento?.text = currentItem?.anio_nacimiento.toString()

        return itemView!!
    }
}
