package org.ada.school.notesapp.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import org.ada.school.notesapp.R
import org.ada.school.notesapp.data.Note

class NotesAdapter(private var notesList: List<Note>) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val cardView: CardView

        init {
            textView = view.findViewById(R.id.text)
            cardView = view.findViewById(R.id.card_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.stickynote, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notesList[position]
        holder.cardView.setCardBackgroundColor(getColor(note.color))
        holder.textView.text = note.text
    }

    fun getColor(color: String): Int {
        return when (color) {
            "Red" -> return Color.RED

            "Blue" -> return Color.BLUE

            "Yellow" -> return Color.YELLOW

            "Green" -> return Color.GREEN

            else -> Color.CYAN
        }
    }

    fun updateNotes(notes: List<Note>) {
        this.notesList = notes
        notifyDataSetChanged()
    }


}