package com.example.mynotes.presentation.presenters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.R
import com.example.mynotes.domain.entities.Note
import com.example.mynotes.presentation.ui.NoteListFragment
import kotlinx.android.synthetic.main.item_note.view.*

class NoteListAdapter : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    private val noteList: MutableList<Note> = mutableListOf()

    fun setNoteList(newNotes: List<Note>) {
        noteList.clear()
        noteList.addAll(newNotes)

        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        holder.itemView.setOnClickListener {
            NoteListFragment.click(noteList[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = noteList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note) {
            itemView.itemHeaderTextView.text = note.header
            itemView.itemBodyTextView.text = note.text
        }
    }
}