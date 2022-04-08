package com.heltonbustos.ejemploroom01.registropersona.presenter

import com.heltonbustos.ejemploroom01.registropersona.interactor.RegistrarPersonaInteractorImpl
import com.heltonbustos.ejemploroom01.registropersona.interactor.RegistrarPersonaInteractorInterface
import com.heltonbustos.ejemploroom01.room.PersonaDataBase
import com.heltonbustos.ejemploroom01.view.RegistrarPersonaViewInterface

class RegistrarPersonaPresenterImpl(private var vista: RegistrarPersonaViewInterface): RegistrarPersonaPresenterInterface {

    private var interactor: RegistrarPersonaInteractorInterface = RegistrarPersonaInteractorImpl()

    override fun registrarPersona(rut: String, nombre: String, edad: Int, database: PersonaDataBase) {
        interactor.registrarPersona(rut, nombre, edad, this, database)
    }

    override fun exitoRegistroPersona() {
        vista.exitoRegistroPersona()
    }

    override fun errorRegistroPersona() {
        vista.errorRegistroPersona()
    }

}