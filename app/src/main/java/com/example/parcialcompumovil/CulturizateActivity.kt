package com.example.parcialcompumovil

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.parcialcompumovil.domain.Jugador
import com.example.parcialcompumovil.domain.JugadorAdapter
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.io.IOException

class CulturizateActivity : AppCompatActivity() {
    private var mlista: ListView? = null
    private var mJugadorAdapter: JugadorAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_culturizate)

        // Inicialización de la ListView
        mlista = findViewById(R.id.lista)

        // Cargar JSON desde los assets
        val jsonString = loadJSONFromAsset()

        // Parsear el JSON a una lista de objetos Jugador
        val jugadorList = parseJSON(jsonString)

        // Inicialización del adaptador con la lista parseada
        jugadorList?.let {
            mJugadorAdapter = JugadorAdapter(this, it)
            mlista?.adapter = mJugadorAdapter
        }

        // Configurar el click listener para la ListView
        mlista?.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            // Obtener el jugador seleccionado
            val selectedJugador = jugadorList?.get(position)

            // Crear un Bundle para almacenar los datos del jugador
            val jugadorBundle = Bundle().apply {
                putString("nombre", selectedJugador?.nombre)
                putInt("anio_nacimiento", selectedJugador?.anio_nacimiento ?: 0)
                putString("descripcion_corta", selectedJugador?.descripcion_corta)
            }

            // Crear un Intent para enviar la información a la otra actividad
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtras(jugadorBundle)
            }

            // Iniciar la otra actividad
            startActivity(intent)
        }
    }

    private fun loadJSONFromAsset(): String? {
        return try {
            val inputStream = assets.open("jugadores.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
    }

    private fun parseJSON(jsonString: String?): List<Jugador>? {
        return jsonString?.let {
            val gson = Gson()
            val jsonObject = gson.fromJson(it, JsonObject::class.java)
            val jugadorArray = jsonObject.getAsJsonArray("jugadores")
            val type = object : TypeToken<List<Jugador>>() {}.type
            gson.fromJson(jugadorArray, type)
        }
    }
}
