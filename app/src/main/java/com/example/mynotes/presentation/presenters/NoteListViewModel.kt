package com.example.mynotes.presentation.presenters

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.viewModelScope
import com.example.mynotes.domain.entities.Note
import com.example.mynotes.utils.DATABASE_REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteListViewModel (application: Application) : AndroidViewModel(application) {

    val allNotes = MutableLiveData<List<Note>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            allNotes.postValue(DATABASE_REPOSITORY.getAllNotes())
        }
    }

    fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            allNotes.postValue(DATABASE_REPOSITORY.getAllNotes())
        }
    }
}