package com.upao.geoquiz

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.upao.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var botonAnterior: ImageButton
    private lateinit var botonSiguiente: ImageButton

    companion object{
        val TAG="MainActivity"
        val KEY_INDEX_IA="indiceActual"
    }
    val preguntas= listOf<Pregunta>(Pregunta(R.string.pregunta_australia,true),
        Pregunta(R.string.pregunta_oceanos,true),
        Pregunta(R.string.pregunta_medio_oriente,false),
        Pregunta(R.string.pregunta_africa,false),
        Pregunta(R.string.pregunta_america,true),
        Pregunta(R.string.pregunta_asia,true))
    var indiceActual: Int = 0
    var porcentajeAciertosEntero: Int = 0
    private var respuestasCorrectas = 0
    private var respuestasDadas = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() fue llamado")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState != null) {
            indiceActual = savedInstanceState.getInt(KEY_INDEX_IA, 0)
        }

        botonAnterior = findViewById(R.id.botonAnterior)
        botonSiguiente = findViewById(R.id.botonSiguiente)

        binding.botonVerdadero.setOnClickListener {
            if (!preguntas[indiceActual].respondida) {
                verificarRespuesta(true)
                bloquearPreguntaActual()
            }
        }

        binding.botonFalso.setOnClickListener {
            if (!preguntas[indiceActual].respondida) {
                verificarRespuesta(false)
                bloquearPreguntaActual()
            }
        }

        binding.botonSiguiente.setOnClickListener {
            indiceActual += 1
            respuestasDadas++ // Incrementa el contador de respuestas dadas
            if (respuestasDadas == preguntas.size) {
                // Si todas las preguntas han sido respondidas, calcula el porcentaje de aciertos
                val porcentajeAciertos= (respuestasCorrectas.toDouble() / preguntas.size.toDouble()) * 100
                porcentajeAciertosEntero = porcentajeAciertos.toInt() // Declarar como var
                val mensaje = "Porcentaje de aciertos: $porcentajeAciertosEntero%"
                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
                respuestasDadas = 0 // Reinicia el contador de respuestas dadas
                 // Reinicia el porcentaje de aciertos entero
            }
            if (indiceActual >= preguntas.size) {
                indiceActual = 0
                respuestasCorrectas = 0
                desbloquearpreguntas()

            }
            actualizarPregunta()
        }

        binding.botonAnterior.setOnClickListener {
            indiceActual -= 1
            respuestasDadas--
            if (indiceActual < 0) {
                indiceActual = preguntas.size - 1
            }
            actualizarPregunta()
        }

        actualizarPregunta()
    }

    private fun actualizarPregunta() {
        val preguntaActual = preguntas[indiceActual]
        val preguntaText = getString(preguntaActual.IdTextoRpta)

        // Crear un SpannableString para cambiar el color de "Pregunta X"
        val spannableString = SpannableString(preguntaText)
        val colorRojo = ForegroundColorSpan(Color.RED)
        spannableString.setSpan(colorRojo, 0, 10, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) // Cambia el color de los primeros 10 caracteres

        // Establecer el texto SpannableString en el TextView
        binding.tvPregunta.text = spannableString

        // Cambia el color de fondo de la pregunta si ya ha sido respondida
        if (preguntaActual.respondida) {
            binding.tvPregunta.setBackgroundColor(Color.parseColor("#CCCCCC"))
        } else {
            binding.tvPregunta.setBackgroundColor(Color.WHITE)
        }
    }
    private fun bloquearPreguntaActual() {
        preguntas[indiceActual].respondida = true
    }
    private fun desbloquearpreguntas(){
        preguntas[0].respondida = false
        preguntas[1].respondida = false
        preguntas[2].respondida = false
        preguntas[3].respondida = false
        preguntas[4].respondida = false
        preguntas[5].respondida = false
    }
    private fun verificarRespuesta(rptaUsuario: Boolean) {
        val rptaCorrecta = preguntas[indiceActual].rpta
        val msgRpta = if (rptaUsuario == rptaCorrecta) R.string.toast_correcto else R.string.toast_incorrecto
        Toast.makeText(this, msgRpta, Toast.LENGTH_SHORT).show()

        if (rptaUsuario == rptaCorrecta) {
            respuestasCorrectas++
        }
    }

    fun onstart(){
        super.onStart()
        Log.d(TAG,"onStart() fue llamado")
    }
    override fun onResume(){
        super.onResume()
        Log.d(TAG,"onResume() fue llamado")
    }
    override fun onPause(){
        super.onPause()
        Log.d(TAG,"onPause() fue llamado")
    }
    override fun onStop(){
        super.onStop()
        Log.d(TAG,"onStop() fue llamado")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG,"onDestroy() fue llamado")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSsaveInstanceState() fue llamado")
        outState.putInt(KEY_INDEX_IA, indiceActual)
    }


}

