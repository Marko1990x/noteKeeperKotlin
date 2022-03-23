package markodunovic.web.app.notekeeper.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import markodunovic.web.app.notekeeper.R
import markodunovic.web.app.notekeeper.listener.DialogOnClickListener
import markodunovic.web.app.notekeeper.repository.room.Note


class MyDialog2(private val dialogListener: DialogOnClickListener, private val note:Note) : DialogFragment() {

    private lateinit var titleField: TextView
    private lateinit var titleDescription: TextView
    private var title:String = ""
    private var description:String= ""

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val layoutInflater = activity?.layoutInflater
        val view: View = layoutInflater!!.inflate(R.layout.dialog,null)
        titleField = view.findViewById(R.id.editTextTitleDialog)
        titleDescription = view.findViewById(R.id.editTextSubjectDialog)
        val dialog= context?.let { AlertDialog.Builder(it) }
        dialog?.setView(view)
        dialog?.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        })
        dialog?.setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
            title = titleField.text.toString()
            description = titleDescription.text.toString()
            if (title != "" && description != ""){
                dialogListener.dialogUpdateNoteOnClick(note,title,description)
            }else{
                Toast.makeText(context,"Can not be empty", Toast.LENGTH_SHORT).show()
            }
        })
        dialog?.setTitle("Edit Note")
        dialog?.setCancelable(false)
        return dialog!!.create()
    }
}