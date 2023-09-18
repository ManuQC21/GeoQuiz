package com.upao.geoquiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG="PreguntaViewModel"
class PreguntaViewModel: ViewModel() {
    init{
        Log.d(TAG,"instancia del ViewModel creada")
    }

    override fun onCleared(){
        super.onCleared()
        Log.d(TAG,"instancia de ViewModel por destruirse")
    }

    companion object {
        const val CLAVE_INDICE_ACTUAL = "clave-indice-actual"
        const val CLAVE_ES_TRAMPOSO="es_tramposo"
    }
    class PreguntaInnerViewModel(private val saveStateHandle: SavedStateHandle):ViewModel(){
        val preguntas = listOf(
            Pregunta("Camberra es la capital de Australia", true),
            Pregunta("El océano Pacífico es más grande que el océano Atlántico", true),
            Pregunta("El canal de Suez conecta el mar Rojo con el océano Índico", false),
            Pregunta("La fuente del Río Nilo es el más largo de América", false),
            Pregunta("El río Amazonas es el más largo de América", true),
            Pregunta("El lago Baikal es la fuente de agua dulce más antigua y profunda", true)
        )
        var indiceActual: Int
            get() = saveStateHandle.get(CLAVE_INDICE_ACTUAL) ?: 0
            set(value) = saveStateHandle.set(CLAVE_INDICE_ACTUAL, value)

        val respuestaActual: Boolean
            get() = preguntas[indiceActual].rpta

        var esTramposo: Boolean
            get() = saveStateHandle.get(CLAVE_ES_TRAMPOSO)?:false
            set(valor)=saveStateHandle.set(CLAVE_ES_TRAMPOSO,valor)

        val textoActual: String
            get() = preguntas[indiceActual].IdTextoRpta

        fun moverAlSiguiente() {
            indiceActual = (indiceActual + 1) % preguntas.size
        }
    }

}