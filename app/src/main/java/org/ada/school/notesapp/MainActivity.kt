package org.ada.school.notesapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.ada.school.notesapp.data.AppDatabase
import org.ada.school.notesapp.data.Note
import org.ada.school.notesapp.data.NoteDao
import org.ada.school.notesapp.ui.adapter.NotesAdapter

class MainActivity : AppCompatActivity() {

    lateinit var noteDao: NoteDao

    lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinner: Spinner = findViewById(R.id.spinner)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        configureRecyclerView(recyclerView)
        setClickListener(spinner)
        configureSpinner(spinner)
        initDatabase()
        loadNotes()
    }

    private fun configureRecyclerView(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        notesAdapter = NotesAdapter(listOf())
        recyclerView.adapter = notesAdapter
    }

    private fun initDatabase() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-notes"
        ).build()
        noteDao = db.noteDao()
    }

    private fun setClickListener(spinner: Spinner) {
        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val editText = findViewById<EditText>(R.id.editText)
            val text = editText.text.toString()
            editText.text.clear()
            val color = spinner.selectedItem.toString()
            val note = Note(null, text, color)
            GlobalScope.launch {
                noteDao.insertAll(note)
                loadNotes()
            }
        }
    }

    private fun loadNotes() {
        GlobalScope.launch(Dispatchers.IO) {
            val notes = noteDao.getAll()
            runOnUiThread {
                notesAdapter.updateNotes(notes)
            }
        }
    }

    private fun configureSpinner(spinner: Spinner) {
        ArrayAdapter.createFromResource(
            this,
            R.array.colors_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinner.adapter = adapter
        }
    }
}