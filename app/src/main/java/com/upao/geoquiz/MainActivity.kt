package com.upao.geoquiz

import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.upao.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.botonVerdadero.setOnClickListener{
            mostrarToastPersonalizado(R.string.toast_correcto)
        }
        binding.botonFalso.setOnClickListener{
            mostrarToastPersonalizado(R.string.toast_incorrecto)
        }

    }
    private fun mostrarToastPersonalizado(messageResId: Int) {
        val toast = Toast(applicationContext)
        val toastView = layoutInflater.inflate(R.layout.toast_respuesta, null)
        val textView = toastView.findViewById<TextView>(R.id.custom_toast_text)
        textView.text = getString(messageResId)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = toastView
        toast.setGravity(Gravity.TOP, 0, 230) // Para mostrar el Toast en la parte superior
        toast.show()
    }


}
