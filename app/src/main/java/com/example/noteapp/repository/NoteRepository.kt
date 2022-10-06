package com.example.noteapp.repository


import androidx.lifecycle.LiveData
import com.example.noteapp.Room.Note
import com.example.noteapp.Room.NotesDao

class NoteRepository(private val notesDao: NotesDao) {

    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }


    suspend fun delete(note: Note){
        notesDao.delete(note)
    }


    suspend fun update(note: Note){
        notesDao.update(note)
    }
}