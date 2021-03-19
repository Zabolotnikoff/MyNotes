package com.example.mynotes.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.R
import com.example.mynotes.domain.entities.Note
import com.example.mynotes.presentation.presenters.AddViewModel
import com.example.mynotes.utils.ACTIVITY
import kotlinx.android.synthetic.main.fragment_add_note.*

class AddNoteFragment : Fragment() {

    private val addViewModel: AddViewModel by lazy {
        ViewModelProvider(this).get(AddViewModel::class.java)
    }
    private lateinit var editedNote: Note

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        headerNoteEditText.requestFocus()

        createNoteButton.setOnClickListener {
            addViewModel.insert(
                Note(
                    id = 0,
                    header = headerNoteEditText.text.toString(),
                    text = textNoteEditText.text.toString()
                )
            ) {
                ACTIVITY.navController.navigate(R.id.action_addNoteFragment_to_noteListFragment)
            }
        }
    }
}