package markodunovic.web.app.notekeeper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import markodunovic.web.app.notekeeper.repository.room.Note
import markodunovic.web.app.notekeeper.repository.room.NoteSingleton

class NoteViewModel : ViewModel() {

    private lateinit var noteList: LiveData<List<Note>>

    fun getAllNotes(): LiveData<List<Note>> {
        noteList = NoteSingleton.getAllNotes()
        return noteList
    }

    fun insertNote(note: Note) {
        NoteSingleton.insert(note)
    }

    fun deleteNotesById(id:Int){
        NoteSingleton.deleteNoteById(id)
    }

    fun getLastNote():Note{
        return NoteSingleton.getLastNote()
    }

    fun nukeNoteTable(){
        NoteSingleton.nukeNotes()
    }

    fun deleteNoteByid(id:Int){
        NoteSingleton.deleteNoteById(id)
    }

    fun updateNote(note:Note){
        NoteSingleton.updateNote(note)
    }

}