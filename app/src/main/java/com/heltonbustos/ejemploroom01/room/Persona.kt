package com.heltonbustos.ejemploroom01.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "persona")
data class Persona(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "rut")
    @NotNull
    val rut: String,
    @ColumnInfo(name = "nombre")
    val nombre: String,
    @ColumnInfo(name = "edad")
    val edad: Int
)