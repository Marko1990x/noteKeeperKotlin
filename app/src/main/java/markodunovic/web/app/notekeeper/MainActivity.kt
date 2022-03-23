package markodunovic.web.app.notekeeper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import markodunovic.web.app.notekeeper.adapter.AdapterNote
import markodunovic.web.app.notekeeper.databinding.ActivityMainBinding
import markodunovic.web.app.notekeeper.listener.AdapterOnClickListener
import markodunovic.web.app.notekeeper.repository.room.Note
import markodunovic.web.app.notekeeper.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity(), AdapterOnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var noteViewModel: NoteViewModel?= null
    private lateinit var noteList:List<Note>
    private lateinit var adapter: AdapterNote
    private lateinit var recycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        noteViewModel = ViewModelProvider(this)
            .get(NoteViewModel::class.java)
        
        noteViewModel?.insertNote(Note(0,"test",
            "TestNote","asdas",1))
        setContentView(view)

        noteViewModel?.getAllNotes()?.observe(this, Observer {
            noteList = it
            for (x in noteList){
                Log.d(MainActivity::class.simpleName, "onCreate: $x")
            }
            setCycler(noteList)
        })
    }

    private fun setCycler(noteList: List<Note>?) {
        recycler = binding.cycler
        adapter = noteList?.let { AdapterNote(it, context = applicationContext, this) }!!
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
    }

    override fun onClickSend(note: Note) {
        TODO("Not yet implemented")
    }
}