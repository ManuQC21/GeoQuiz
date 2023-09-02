package com.upao.geoquiz

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.upao.geoquiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.botonVerdadero.setOnClickListener {
            mostrarSnackbar(R.string.toast_correcto)
        }

        binding.botonFalso.setOnClickListener {
            mostrarSnackbar(R.string.toast_incorrecto)
        }
    }

    private fun mostrarSnackbar(messageResId: Int) {
        val snackbar = Snackbar.make(binding.root, getString(messageResId), Snackbar.LENGTH_SHORT)
        snackbar.anchorView = binding.botonVerdadero
        snackbar.view.setBackgroundColor(resources.getColor(R.color.rojo))
        snackbar.show()
    }
}

