package com.upao.geoquiz

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.upao.geoquiz.databinding.ActivityTrampaBinding

const val EXTRA_RPTA_MOSTRADA = "com.dapm.geoquiz.rpta.mostrada"
private const val EXTRA_RPTA_CORRECTA="com.dapm.mainactivity"
class TrampaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrampaBinding

    companion object {

        fun nuevoIntent(packageContext: Context, rptaCorrecta: Boolean): Intent {
            val intento = Intent(packageContext, TrampaActivity::class.java)
            intento.putExtra(EXTRA_RPTA_CORRECTA, rptaCorrecta)
            return intento
        }
    }
    private var RespuestaesVerdadera = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrampaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        RespuestaesVerdadera = intent.getBooleanExtra(EXTRA_RPTA_CORRECTA, false)
        binding.botonMostrarRespuesta.setOnClickListener {
            val textoRpta = when {
                RespuestaesVerdadera -> R.string.boton_verdadero
                else -> R.string.boton_falso
            }
            binding.respuestaTextoVista.setText(textoRpta)
            setResultPreguntaMostrada(true)
        }
    }

    private fun setResultPreguntaMostrada(fueMostrada: Boolean) {
        val data=Intent()
        data.putExtra(EXTRA_RPTA_MOSTRADA,fueMostrada)
        setResult(Activity.RESULT_OK,data)
    }
}