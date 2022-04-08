package com.heltonbustos.ejemploroom01.room

import androidx.room.*

@Dao
interface PersonaDAO {

    @Query("Select * From persona")
    fun obtenerListaPersonas(): List<Persona>

    @Query("Select * from persona Where id = :id")
    fun buscarPersona(id: Int): Persona

    @Update
    fun actualizar(persona: Persona)

    @Insert
    fun insertar(persona: Persona): Long

    @Delete
    fun borrar(persona: Persona)

}