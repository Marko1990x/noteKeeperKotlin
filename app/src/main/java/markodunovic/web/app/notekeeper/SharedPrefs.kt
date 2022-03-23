package markodunovic.web.app.notekeeper

import android.content.SharedPreferences

object SharedPrefs {

    private val setting: SharedPreferences =
        App.appContext().getSharedPreferences("prefs", 0)

    fun addWelcomeNoteNumber(i: Int) {
        val editor: SharedPreferences.Editor = setting.edit()
        editor.putInt("numberShown", i)
        editor.apply()
    }

    fun getNoteNumber(): Int {
        return setting.getInt("numberShown", 0)
    }
}