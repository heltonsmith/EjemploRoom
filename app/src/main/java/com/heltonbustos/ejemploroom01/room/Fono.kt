package com.heltonbustos.ejemploroom01.room

import androidx.room.*
import org.jetbrains.annotations.NotNull

@Entity(tableName = "fono",
    foreignKeys = [
        ForeignKey(
            entity = Persona::class,
            parentColumns = ["id"],
            childColumns = ["id_persona"]
        )
    ],
    indices = [Index(value = ["id_persona"]), Index(value = ["numero"], unique = true)]
)
data class Fono(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "numero")
    @NotNull
    var numero: Int,
    @ColumnInfo(name = "id_persona")
    @NotNull
    var id_persona: Int
)
