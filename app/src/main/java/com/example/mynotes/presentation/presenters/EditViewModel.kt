package com.example.mynotes.presentation.presenters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotes.domain.entities.Note
import com.example.mynotes.utils.DATABASE_REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditViewModel(application: Application) : AndroidViewModel(application) {
    fun update(note: Note, callback: () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            DATABASE_REPOSITORY.update(note) {
                callback()
            }
        }
}