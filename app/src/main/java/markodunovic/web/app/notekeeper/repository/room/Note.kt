package markodunovic.web.app.notekeeper.repository.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") var noteName: String,
    @ColumnInfo(name = "details") var noteDetails: String,
    @ColumnInfo(name = "extra") var noteExtra: String,
    @ColumnInfo(name = "priority") var notePriority: Int
)
