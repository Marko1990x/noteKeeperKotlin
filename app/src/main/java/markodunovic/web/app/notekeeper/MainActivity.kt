package markodunovic.web.app.notekeeper

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
    private var noteViewModel: NoteViewModel? = null
    private lateinit var noteList: List<Note>
    private lateinit var adapter: AdapterNote
    private lateinit var recycler: RecyclerView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        toolbar = view.findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        noteViewModel = ViewModelProvider(this)
            .get(NoteViewModel::class.java)

        noteViewModel?.insertNote(
            Note(
                0, "test",
                "TestNote", "asdas", 1
            )
        )
        setContentView(view)

        noteViewModel?.getAllNotes()?.observe(this, Observer {
            noteList = it
            for (x in noteList) {
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

    override fun onClickSendEdit(note: Note) {
        Log.d(MainActivity::class.simpleName, "onClick: $note")
    }

    override fun onClickSendDelete(note: Note) {
        Log.d(MainActivity::class.simpleName, "onClick: $note")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.addNote ->
                addNote()
            R.id.nukeNotes ->
                deleteAllNotes()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {
        Log.d("Note", "deleteAllNotes: ")
    }

    private fun addNote() {
        Log.d("Note", "addNote: ")
    }
}