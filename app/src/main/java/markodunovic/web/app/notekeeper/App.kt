package markodunovic.web.app.notekeeper

import android.app.Application
import android.content.Context

class App: Application() {

    init {
        instance = this
    }

    companion object{
        private var instance: App? = null
        fun newInstance() = App()
        fun appContext(): Context{
            return instance!!.applicationContext
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}