package com.example.mynotes.domain.repository

import com.example.mynotes.domain.entities.Note

interface DatabaseRepository {

    suspend fun getAllNotes(): List<Note>

    suspend fun insert(note: Note, callback: () -> Unit)

    suspend fun update(note: Note, callback: () -> Unit)

    suspend fun delete(note: Note, callback: () -> Unit)
}