package markodunovic.web.app.notekeeper.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id:Int,
    @ColumnInfo(name = "name")val noteName: String,
    @ColumnInfo(name = "details") val noteDetails: String,
    @ColumnInfo(name = "extra") val noteExtra: String
)
