package com.upao.geoquiz

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.upao.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
        val KEY_INDEX_IA = "indiceActual"
    }

    private lateinit var binding: ActivityMainBinding
    private val preguntaViewModel: PreguntaViewModel.PreguntaInnerViewModel by viewModels()
    var indiceActual: Int = 0
    var hizoTrampa: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "se obtuvo una preguntaViewModel: $preguntaViewModel")
        Log.d(TAG,"onCreate() fue llamado")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botonVerdadero.setOnClickListener{

        }
        binding.botonFalso.setOnClickListener{

        }
        binding.botonSiguiente.setOnClickListener {
            preguntaViewModel.moverAlSiguiente()
            actualizarPregunta()
        }
        binding.botonHacerTrampa.setOnClickListener{
            val rptaCorrecta=preguntaViewModel.respuestaActual
            val intento= TrampaActivity.nuevoIntent(this,rptaCorrecta)
            iniciadorTrampa.launch(intento)
        }
        actualizarPregunta()
    }

    private fun actualizarPregunta() {
        val preguntaResId: String = preguntaViewModel.textoActual
        binding.tvPregunta.text = preguntaResId
    }

    private fun verificarRespuesta(rptaUsuario: Boolean) {
        val rptaCorrecta = preguntaViewModel.respuestaActual
        val msgRpta=when{
            preguntaViewModel.esTramposo->R.string.toast_juicio
            rptaUsuario==rptaCorrecta->R.string.toast_correcto
            else->R.string.toast_incorrecto
        }

        Toast.makeText(this,msgRpta,Toast.LENGTH_LONG).show()
    }

    private val iniciadorTrampa=registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){ resultado: ActivityResult ->
        if(resultado.resultCode == Activity.RESULT_OK){
            resultado.data?.getBooleanExtra(EXTRA_RPTA_MOSTRADA,false)?:false
        }
    }

}