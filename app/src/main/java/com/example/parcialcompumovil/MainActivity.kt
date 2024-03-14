package com.example.parcialcompumovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnJugar = findViewById<Button>(R.id.jugar)
        btnJugar.setOnClickListener {
            val intent = Intent(this, JugarActivity::class.java)
            startActivity(intent)
        }

        val btnCulturizate = findViewById<Button>(R.id.culturizate)
        btnCulturizate.setOnClickListener {
            val intent = Intent(this, CulturizateActivity::class.java)
            startActivity(intent)
        }
    }
}