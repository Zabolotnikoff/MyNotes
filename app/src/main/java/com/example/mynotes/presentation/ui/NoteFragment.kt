package com.example.mynotes.presentation.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mynotes.R
import com.example.mynotes.domain.entities.Note
import com.example.mynotes.presentation.presenters.NoteViewModel
import com.example.mynotes.utils.ACTIVITY
import kotlinx.android.synthetic.main.fragment_note.*

class NoteFragment : Fragment() {

    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(this).get(NoteViewModel::class.java)
    }
    private lateinit var selectedNote: Note

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedNote = arguments?.getSerializable("selected_note") as Note
        bind(selectedNote)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteNoteActionItem -> {
                noteViewModel.delete(selectedNote) {
                    ACTIVITY.navController.navigate(R.id.action_noteFragment_to_noteListFragment)
                }
            }
            R.id.editNoteActionItem -> {
                val bundle = Bundle()
                bundle.putSerializable("edited_note",selectedNote)
                ACTIVITY.navController.navigate(R.id.action_noteFragment_to_editNoteFragment,bundle)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun bind(note: Note) {
        headerNoteTextView.text = note.header
        textNoteTextView.text = note.text
    }
}