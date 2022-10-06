package com.example.noteapp.Room


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp.Room.Note


@Dao
interface NotesDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note : Note)

    @Delete
    suspend fun delete(note: Note)


    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>


    @Update
    suspend fun update(note: Note)

}