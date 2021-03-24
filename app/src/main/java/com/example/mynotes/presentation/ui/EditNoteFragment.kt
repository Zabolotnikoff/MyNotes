package com.example.mynotes.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.R
import com.example.mynotes.domain.entities.Note
import com.example.mynotes.presentation.presenters.EditViewModel
import com.example.mynotes.utils.ACTIVITY
import kotlinx.android.synthetic.main.fragment_edit_note.*


class EditNoteFragment : Fragment() {

    private val editViewModel: EditViewModel by lazy {
        ViewModelProvider(this).get(EditViewModel::class.java)
    }
    private lateinit var editedNote: Note


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editedNote = arguments?.getSerializable("edited_note") as Note
        bind(editedNote)

        editNoteButton.setOnClickListener {
            editViewModel.update(
                Note(
                    id = editedNote.id,
                    header = editedNote.header,
                    text = textNoteEditText.text.toString()
                )
            ) {
                ACTIVITY.navController.navigate(R.id.action_editNoteFragment_to_noteListFragment)
            }
        }

    }

    private fun bind(note: Note) {
        headerNoteTextView.text = note.header
        textNoteEditText.setText(note.text)
        textNoteEditText.requestFocus()
    }
}