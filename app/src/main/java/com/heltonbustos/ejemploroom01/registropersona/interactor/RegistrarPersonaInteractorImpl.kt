package com.heltonbustos.ejemploroom01.registropersona.interactor

import com.heltonbustos.ejemploroom01.registropersona.presenter.RegistrarPersonaPresenterInterface
import com.heltonbustos.ejemploroom01.room.Persona
import com.heltonbustos.ejemploroom01.room.PersonaDataBase


class RegistrarPersonaInteractorImpl: RegistrarPersonaInteractorInterface {

    override fun registrarPersona(
        rut: String,
        nombre: String,
        edad: Int,
        presenter: RegistrarPersonaPresenterInterface,
        database: PersonaDataBase) {

        var p1 = Persona(0, rut, nombre, edad)
        /////////////////////////
        Thread.sleep(2000)
        /////////////////////////
        var x: Long = database.personaDAO.insertar(p1)
        if(x > 0){
            presenter.exitoRegistroPersona()
        }
        else{
            presenter.errorRegistroPersona()
        }

    }
}