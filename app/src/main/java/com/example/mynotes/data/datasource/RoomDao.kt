package com.example.mynotes.data.datasource

import androidx.room.*
import com.example.mynotes.data.model.NoteModel

@Dao
interface RoomDao {

    @Query("SELECT * from table_of_notes")
    fun getAllNotes(): List<NoteModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notemodel: NoteModel)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(notemodel: NoteModel)

    @Delete
    suspend fun delete(notemodel: NoteModel)
}
