package com.heltonbustos.ejemploroom01.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FonoDAO {

    @Insert
    fun insertar(fono: Fono)

    @Query("Select * From fono")
    fun obtenerTodosFonos(): List<Fono>

    @Query("Select * From fono Where id_persona=:id")
    fun obtenerFonosPersona(id: Int): List<Fono>

}