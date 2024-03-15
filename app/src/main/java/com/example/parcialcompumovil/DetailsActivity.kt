package com.example.parcialcompumovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val nombreTextView = findViewById<TextView>(R.id.nombre)
        val anioTextView = findViewById<TextView>(R.id.anio)
        val descripcionTextView = findViewById<TextView>(R.id.descripcion)

        val jugadorBundle = intent.extras
        val nombre = jugadorBundle?.getString("nombre")
        val anio = jugadorBundle?.getInt("anio_nacimiento", 0)
        val descripcion = jugadorBundle?.getString("descripcion_corta")

        nombreTextView.text = nombre
        anioTextView.text = anio.toString()
        descripcionTextView.text = descripcion
    }
}
