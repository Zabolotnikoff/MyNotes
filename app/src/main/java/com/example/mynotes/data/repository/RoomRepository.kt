package com.example.mynotes.data.repository

import com.example.mynotes.data.converter.Converter
import com.example.mynotes.data.datasource.RoomDao
import com.example.mynotes.domain.entities.Note
import com.example.mynotes.domain.repository.DatabaseRepository


class RoomRepository(private val roomDao: RoomDao) : DatabaseRepository {

    override suspend fun getAllNotes() = roomDao.getAllNotes().map { Converter.fromModelToNote(it) }


    override suspend fun insert(note: Note, callback: () -> Unit) {
        roomDao.insert(Converter.fromNoteToModel(note))
        callback()
    }

    override suspend fun update(note: Note, callback: () -> Unit) {
        roomDao.update(Converter.fromNoteToModel(note))
        callback()
    }

    override suspend fun delete(note: Note, callback: () -> Unit) {
        roomDao.delete(Converter.fromNoteToModel(note))
        callback()
    }

}