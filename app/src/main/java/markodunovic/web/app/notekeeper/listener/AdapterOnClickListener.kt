package markodunovic.web.app.notekeeper.listener

import markodunovic.web.app.notekeeper.repository.room.Note

interface AdapterOnClickListener {
    fun onClickSend(note:Note)
}