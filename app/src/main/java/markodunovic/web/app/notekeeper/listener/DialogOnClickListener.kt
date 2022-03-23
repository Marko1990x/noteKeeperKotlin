package markodunovic.web.app.notekeeper.listener

import markodunovic.web.app.notekeeper.repository.room.Note

interface DialogOnClickListener {
    fun dialogOnClick(title: String, desc: String)
    fun dialogUpdateNoteOnClick(note: Note, title: String, desc: String)
}