package markodunovic.web.app.notekeeper

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.analytics.FirebaseAnalytics
import markodunovic.web.app.notekeeper.adapter.AdapterNote
import markodunovic.web.app.notekeeper.databinding.ActivityMainBinding
import markodunovic.web.app.notekeeper.dialog.MyDialog
import markodunovic.web.app.notekeeper.dialog.MyDialog2
import markodunovic.web.app.notekeeper.listener.AdapterOnClickListener
import markodunovic.web.app.notekeeper.listener.DialogOnClickListener
import markodunovic.web.app.notekeeper.repository.room.Note
import markodunovic.web.app.notekeeper.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity(), AdapterOnClickListener, DialogOnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var noteViewModel: NoteViewModel? = null
    private lateinit var noteList: List<Note>
    private lateinit var adapter: AdapterNote
    private lateinit var recycler: RecyclerView
    private lateinit var toolbar: Toolbar
    private lateinit var firebaseAnalytics:FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setBackgroundColor(resources.getColor(R.color.black))
        setSupportActionBar(toolbar)
        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        noteViewModel = ViewModelProvider(this)
            .get(NoteViewModel::class.java)

        val sharedPrefs = SharedPrefs.getNoteNumber()
        Log.d("sharedPrefs", "onCreate: $sharedPrefs")
        if (sharedPrefs == 0) {
            noteViewModel?.insertNote(
                Note(
                    0, "Welcome",
                    "Add Some Notes :)", "", 0,false
                )
            )
        }

        noteViewModel?.getAllNotes()?.observe(this, Observer {
            noteList = it
            for (x in noteList) {
                Log.d(MainActivity::class.simpleName, "onCreate: $x")
            }
            setCycler(noteList)
        })
        SharedPrefs.addWelcomeNoteNumber(1)
        setContentView(view)
    }


    private fun setCycler(noteList: List<Note>?) {
        recycler = binding.cycler
        adapter = noteList?.let { AdapterNote(it, context = applicationContext, this) }!!
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
    }

    override fun onClickSendEdit(note: Note) {
        val myDialog2 = MyDialog2(this, note)
        myDialog2.show(supportFragmentManager, "custom_diag2")
    }

    override fun onClickSendDelete(note: Note) {
        noteViewModel?.deleteNoteByid(note.id.toInt())
        adapter.notifyDataSetChanged()
    }

    override fun onNoteCompleted(note: Note) {
        noteViewModel?.updateNote(Note(note.id,note.noteName,note.noteDetails,note.noteExtra,note.notePriority,true))
        adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.addNote ->
                addNote()
            R.id.nukeNotes ->
                deleteAllNotes()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllNotes() {

        val dialog:AlertDialog.Builder = AlertDialog.Builder(this)
        dialog.setMessage("Delete All Notes ?")
        dialog.setCancelable(false)
        dialog.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        dialog.setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, i ->
            noteViewModel?.nukeNoteTable()
            Toast.makeText(this, "All Notes Deleted", Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
        })
        dialog.create()
        dialog.show()
    }

    private fun addNote() {
        val dialog: MyDialog = MyDialog(this)
        dialog.show(supportFragmentManager, "custom_dia")
    }

    override fun dialogOnClick(title: String, desc: String) {
        Log.d("Dialog", "dialogOnClick: $title $desc")
        val note: Note = Note(0, title, desc, "", 0,false)
        noteViewModel?.insertNote(note)
        adapter.notifyDataSetChanged()
    }

    override fun dialogUpdateNoteOnClick(note: Note, title: String, desc: String) {
        Log.d("SentNote", "dialogUpdateNoteOnClick: $note \n\n $title $desc")
        noteViewModel?.updateNote(Note(note.id, title, desc, "", 0,false))
        adapter.notifyDataSetChanged()
    }


}