package com.heltonbustos.ejemploroom01.view

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.heltonbustos.ejemploroom01.R
import com.heltonbustos.ejemploroom01.registropersona.presenter.RegistrarPersonaPresenterImpl
import com.heltonbustos.ejemploroom01.registropersona.presenter.RegistrarPersonaPresenterInterface
import com.heltonbustos.ejemploroom01.room.Persona
import com.heltonbustos.ejemploroom01.room.PersonaDataBase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class MainActivity : AppCompatActivity(), CoroutineScope, RegistrarPersonaViewInterface {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job

    private lateinit var database: PersonaDataBase

    //dialogo
    //dialogo
    private lateinit var dialogo: Dialogo
    //dialogo
    //dialogo

    //presenter
    //presenter
    lateinit var presenter: RegistrarPersonaPresenterInterface
    //presenter
    //presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = SupervisorJob()

        dialogo = Dialogo(this)

        database = Room.databaseBuilder(
            application, PersonaDataBase::class.java, PersonaDataBase.DATABASE_NAME
        ).allowMainThreadQueries().build()

        /*
        database.fonoDAO.insertar(Fono(0, 133, 1))
        database.fonoDAO.insertar(Fono(0, 134, 1))
        database.fonoDAO.insertar(Fono(0, 138, 1))
        */

        //presenter
        //presenter
        presenter = RegistrarPersonaPresenterImpl(this)
        //presenter
        //presenter

        //var progressBar: ProgressBar = findViewById(R.id.progressBar)
        var txtRut: EditText = findViewById(R.id.txtRut)
        var txtNombre: EditText = findViewById(R.id.txtNombre)
        var txtEdad: EditText = findViewById(R.id.txtEdad)
        var btnGuardar: Button = findViewById(R.id.btnGuardar)
        var btnMostrar: Button = findViewById(R.id.btnMostrar)
        var txtResultados: TextView = findViewById(R.id.txtResultados)

        //registrar persona
        //registrar persona
        btnGuardar.setOnClickListener {
            var rut: String = txtRut.text.toString()
            var nombre: String = txtNombre.text.toString()
            var edad: Int = txtEdad.text.toString().toInt()

            dialogo.mostrarCargando()
            launch{
                withContext(Dispatchers.IO){
                    presenter.registrarPersona(rut, nombre, edad, database)
                }
            }
        }
        //registrar persona
        //registrar persona
        btnMostrar.setOnClickListener {
            dialogo.mostrarCargando()
            txtResultados.text = "Resultados:\n\n"

            launch{
                var lista = withContext(Dispatchers.IO){
                    mostrarPersonas()
                }
                if(lista.size > 0){
                    for(i in lista){
                        txtResultados.append("id: ${i.id} rut: ${i.rut} nombre: ${i.nombre} edad: ${i.edad} \n")
                    }
                }
                else{
                    txtResultados.append("Sin Resultados...")
                }
            }
            dialogo.ocultarCargando()
        }


    }

    suspend fun mostrarPersonas(): List<Persona>{
        delay(timeMillis = 2000)
        return database.personaDAO.obtenerListaPersonas()
    }

    /*
    suspend fun guardarPersona(rut: String, nombre: String, edad: Int): Long{
        delay(timeMillis = 4000)
        var p1 = Persona(0, rut, nombre, edad)
        return database.personaDAO.insertar(p1)
    }
    */

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    ///////////////////////////////////////////
    override fun exitoRegistroPersona() {
        runOnUiThread(java.lang.Runnable {
            Toast.makeText(baseContext, "Registrado OK", Toast.LENGTH_SHORT).show()
            dialogo.ocultarCargando()
        })
    }

    ///////////////////////////////////////////
    override fun errorRegistroPersona() {
        runOnUiThread(java.lang.Runnable {
            Toast.makeText(baseContext, "Error", Toast.LENGTH_SHORT).show()
            dialogo.ocultarCargando()
        })
    }


}