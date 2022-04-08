package com.heltonbustos.ejemploroom01.registropersona.presenter

import com.heltonbustos.ejemploroom01.room.PersonaDataBase

interface RegistrarPersonaPresenterInterface {
    fun registrarPersona(rut: String, nombre: String, edad: Int, database: PersonaDataBase)
    fun exitoRegistroPersona()
    fun errorRegistroPersona()
}