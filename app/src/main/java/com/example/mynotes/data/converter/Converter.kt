package com.example.mynotes.data.converter

import com.example.mynotes.data.model.NoteModel
import com.example.mynotes.domain.entities.Note

abstract class Converter() {

    companion object {
        fun fromNoteToModel(from: Note): NoteModel =
            NoteModel(
                id = from.id?: 0,
                header = from.header ?: "",
                text = from.text ?: ""
            )

        fun fromModelToNote(from: NoteModel): Note =
            Note(
                id = from.id ?: -1,
                header = from.header ?: "",
                text = from.text ?: ""
            )
    }
}
