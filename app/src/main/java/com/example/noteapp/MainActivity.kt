package com.example.noteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.Adapter.NoteAdapter
import com.example.noteapp.Adapter.NoteClickDeleteInterface
import com.example.noteapp.Adapter.NoteClickInterface
import com.example.noteapp.Room.Note
import com.example.noteapp.ViewModel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {


    lateinit var viewModel: NoteViewModel
    lateinit var notesRV: RecyclerView
    lateinit var addButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        notesRV = findViewById(R.id.notes_Recyclerview)
        addButton = findViewById(R.id.floatButton)


        notesRV.layoutManager = LinearLayoutManager(this)


        val noteRVAdapter = NoteAdapter(this, this, this)


        notesRV.adapter = noteRVAdapter


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)


        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {

                noteRVAdapter.updateList(it)
            }
        })
        addButton.setOnClickListener {

            val intent = Intent(this@MainActivity, EditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(note: Note) {

        val intent = Intent(this@MainActivity, EditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {

        viewModel.deleteNote(note)

        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }
}