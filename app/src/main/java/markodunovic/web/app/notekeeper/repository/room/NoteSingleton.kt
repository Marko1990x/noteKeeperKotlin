package markodunovic.web.app.notekeeper.repository.room

import android.os.Handler
import android.os.HandlerThread
import androidx.lifecycle.LiveData
import markodunovic.web.app.notekeeper.App

object NoteSingleton {
    private var noteDatabase: NoteDatabase
    private val noteDao: NoteDao
    private var mHandler:Handler? = null

    init {
        noteDatabase = NoteDatabase.getInstance(context = App.appContext())
        noteDao = noteDatabase.noteDao()
    }

    fun getAllNotes():LiveData<List<Note>>{
        return noteDao.getAll()
    }

    fun insert(note:Note){
        getRepoHandler()!!.post(Runnable {
            noteDao.insertNote(note)
        })
    }

    fun deleteNoteById(id:Int){
        getRepoHandler()!!.post(Runnable {
            noteDao.deleteNoteById(id)
        })
    }

    fun getLastNote():Note{
        return noteDao.getLastNote()
    }

    fun updateNote(note: Note){
        getRepoHandler()!!.post(Runnable {
            noteDao.updateNote(note)
        })
    }

    fun nukeNotes(){
        getRepoHandler()!!.post(Runnable {
            noteDao.nukeNotes()
        })
    }

    private fun getRepoHandler(): Handler? {
        if (mHandler == null){
            val thread = HandlerThread("thread")
            thread.start()
            mHandler = Handler(thread.looper)
        }
        return mHandler
    }

}