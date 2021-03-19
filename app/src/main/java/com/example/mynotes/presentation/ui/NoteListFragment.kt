package com.example.mynotes.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mynotes.R
import com.example.mynotes.domain.entities.Note
import com.example.mynotes.presentation.presenters.NoteListAdapter
import com.example.mynotes.presentation.presenters.NoteListViewModel
import com.example.mynotes.utils.ACTIVITY
import kotlinx.android.synthetic.main.fragment_note_list.*

class NoteListFragment : Fragment() {

    private val noteListViewModel: NoteListViewModel by lazy {
        ViewModelProvider(this).get(NoteListViewModel::class.java)
    }
    private var noteListAdapter = NoteListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        recyclerView.adapter = noteListAdapter
        noteListViewModel.allNotes.observe(viewLifecycleOwner, Observer(::setNoteList))
    }

    private fun setNoteList(notesList: List<Note>) {
        noteListAdapter.setNoteList(notesList)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_list_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addNoteActionItem -> {
                ACTIVITY.navController.navigate(R.id.action_noteListFragment_to_addNoteFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        noteListViewModel.allNotes.removeObservers(viewLifecycleOwner)
        recyclerView.adapter = null
    }

    companion object {
        fun click(note: Note) {
            val bundle = Bundle()
            bundle.putSerializable("selected_note", note)
            ACTIVITY.navController.navigate(R.id.action_noteListFragment_to_noteFragment, bundle)
        }
    }
}
