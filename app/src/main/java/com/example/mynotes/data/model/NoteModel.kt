package com.example.mynotes.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "table_of_notes")
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    val header: String = "",

    @ColumnInfo
    val text: String = ""
) : Serializable