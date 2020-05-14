package com.colegium.newsapp.viewmodels.dataclasses

import androidx.annotation.StringDef

data class ErrorType(
    @ErrorTypes val type: String = "",
    val message: String = "",
    val request: String = ""
) {
    override fun toString(): String {
        return "Ha ocurrido un error: $type}"
    }
}

data class BasicError(
    val code: Int,
    val message: String
)

const val NETWORK_ERROR: String = "error. Revisa tu conexión"
const val HTTP_ERROR: String = " error HTTP. Contacta al soporte de Colegium"
const val UNKNOWN: String = "Error desconocido"

@Retention(AnnotationRetention.SOURCE)
@StringDef(NETWORK_ERROR, HTTP_ERROR, UNKNOWN)
annotation class ErrorTypes