package markodunovic.web.app.notekeeper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import markodunovic.web.app.notekeeper.R
import markodunovic.web.app.notekeeper.listener.AdapterOnClickListener
import markodunovic.web.app.notekeeper.repository.room.Note

class AdapterNote(
    private val noteList: List<Note>,
    private val context: Context,
    private val adapterOnClickListener: AdapterOnClickListener
) : RecyclerView.Adapter<AdapterNote.AdapterNoteViewHolder>() {

    class AdapterNoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageButton: ImageButton = itemView.findViewById(R.id.cardButtonDone)
        val editNoteButton: ImageButton = itemView.findViewById(R.id.editNoteButton)
        val deleteNoteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
        val textTitle: TextView = itemView.findViewById(R.id.noteTitleText)
        val textSubject: TextView = itemView.findViewById(R.id.noteContentText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNoteViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.card_cycler, parent, false)
        val adapterView = AdapterNoteViewHolder(view)
        return adapterView
    }

    override fun onBindViewHolder(holder: AdapterNoteViewHolder, position: Int) {
        val note: Note = noteList[position]
        holder.textTitle.text = note.noteName
        holder.textSubject.text = note.noteDetails
        holder.deleteNoteButton.setOnClickListener(View.OnClickListener {
            adapterOnClickListener.onClickSendDelete(note)
        })
        holder.editNoteButton.setOnClickListener(View.OnClickListener {
            adapterOnClickListener.onClickSendEdit(note)
        })
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

}