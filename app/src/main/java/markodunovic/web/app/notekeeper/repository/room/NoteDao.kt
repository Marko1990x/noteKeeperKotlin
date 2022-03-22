package markodunovic.web.app.notekeeper.repository.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Query("SELECT * from notes")
    abstract fun getAll(): LiveData<List<Note>>

    @Insert
    fun insertNote(note: Note)

    @Query("DELETE FROM notes where id = :id")
    fun deleteNoteById(id: Int)

    @Query("SELECT * FROM notes ORDER BY id DESC LIMIT 1")
    fun getLastNote(): Note

    @Update
    fun updateNote(note: Note)

    @Query("DELETE FROM notes")
    fun nukeNotes()

}