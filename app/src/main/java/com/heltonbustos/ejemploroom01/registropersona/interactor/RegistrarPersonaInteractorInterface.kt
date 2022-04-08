package com.heltonbustos.ejemploroom01.registropersona.interactor

import com.heltonbustos.ejemploroom01.registropersona.presenter.RegistrarPersonaPresenterInterface
import com.heltonbustos.ejemploroom01.room.PersonaDataBase

interface RegistrarPersonaInteractorInterface {
    fun registrarPersona(
        rut: String,
        nombre: String,
        edad: Int,
        presenter: RegistrarPersonaPresenterInterface,
        database: PersonaDataBase
    )
}