package com.heltonbustos.ejemploroom01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.room.Room
import com.heltonbustos.ejemploroom01.room.Fono
import com.heltonbustos.ejemploroom01.room.Persona
import com.heltonbustos.ejemploroom01.room.PersonaDataBase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job

    private lateinit var database: PersonaDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = SupervisorJob()

        val dialogo = Dialogo(this)

        database = Room.databaseBuilder(
            application, PersonaDataBase::class.java, PersonaDataBase.DATABASE_NAME
        ).allowMainThreadQueries().build()

        database.fonoDAO.insertar(Fono(0, 133, 1))
        database.fonoDAO.insertar(Fono(0, 134, 1))
        database.fonoDAO.insertar(Fono(0, 138, 1))


        var progressBar: ProgressBar = findViewById(R.id.progressBar)
        var txtRut: EditText = findViewById(R.id.txtRut)
        var txtNombre: EditText = findViewById(R.id.txtNombre)
        var txtEdad: EditText = findViewById(R.id.txtEdad)
        var btnGuardar: Button = findViewById(R.id.btnGuardar)
        var btnMostrar: Button = findViewById(R.id.btnMostrar)
        var txtResultados: TextView = findViewById(R.id.txtResultados)

        btnGuardar.setOnClickListener {
            var rut: String = txtRut.text.toString()
            var nombre: String = txtNombre.text.toString()
            var edad: Int = txtEdad.text.toString().toInt()

            //progressBar.visibility = View.VISIBLE
            dialogo.mostrarCargando()
            launch{
                var x = withContext(Dispatchers.IO){
                    guardarPersona(rut, nombre, edad)
                }
                if(x > 0){
                    Toast.makeText(applicationContext, "Insertado OK!", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(applicationContext, "No se pudo registrar", Toast.LENGTH_SHORT).show()
                }
                dialogo.ocultarCargando()
                //progressBar.visibility = View.GONE
            }
        }

        btnMostrar.setOnClickListener {
            dialogo.mostrarCargando()
            txtResultados.text = "Resultados:\n\n"
            launch {
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

    fun mostrarPersonas(): List<Persona>{
        return database.personaDAO.obtenerListaPersonas()
    }

    suspend fun guardarPersona(rut: String, nombre: String, edad: Int): Long{
        delay(timeMillis = 4000)
        var p1 = Persona(0, rut, nombre, edad)
        return database.personaDAO.insertar(p1)
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }


}