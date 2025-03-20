package it.liceonewton.todolist

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

private const val ARG_PARAM1 = "task_checkbox"
private const val ARG_PARAM2 = "task_text"


class TaskFragment : Fragment() {
    var chechbox: Boolean? = null
        private set
    var taskText: String? = null
        private set

    var listener: OnTaskDeleteListener? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            chechbox = it.getBoolean(ARG_PARAM1,false)
            taskText = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)
        var taskText = arguments?.getString("task_text") ?: ""
        val taskView = view.findViewById<TextView>(R.id.taskText)
        taskView.text = taskText
        val deleteButton = view?.findViewById<Button>(R.id.deleteTask)
        deleteButton?.setOnClickListener {
            listener?.onDeleteTask(this)
        }

        taskView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                taskText = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                taskText = s.toString()
            }
        })
        return view
    }

    companion object {

        fun newInstance(check:Boolean, task: String, onTaskDeleteListener: OnTaskDeleteListener): TaskFragment {
            val fragment = TaskFragment()
            val args = Bundle()
            args.putBoolean(ARG_PARAM1, check)
            args.putString(ARG_PARAM2, task)
            fragment.listener = onTaskDeleteListener
            fragment.arguments = args
            return fragment
        }
    }
}