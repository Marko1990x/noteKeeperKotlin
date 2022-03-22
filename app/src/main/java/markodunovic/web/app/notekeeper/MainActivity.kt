package markodunovic.web.app.notekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import markodunovic.web.app.notekeeper.databinding.ActivityMainBinding
import markodunovic.web.app.notekeeper.repository.room.Note
import markodunovic.web.app.notekeeper.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var noteViewModel: NoteViewModel?= null
    private lateinit var noteList:List<Note>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        noteViewModel = ViewModelProvider(this)
            .get(NoteViewModel::class.java)
        
        noteViewModel?.insertNote(Note(0,"test",
            "TestNote","asdas"))
        setContentView(view)
    }

    private fun setCycler(noteList: List<Note>?) {

    }
}