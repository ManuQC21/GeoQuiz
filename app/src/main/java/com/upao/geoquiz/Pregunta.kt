package com.upao.geoquiz

import androidx.annotation.StringRes

class Pregunta (@StringRes val IdTextoRpta:Int, val rpta:Boolean, var respondida: Boolean = false) {
}