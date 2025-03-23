package it.liceonewton.todolist

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() , OnTaskDeleteListener {

    lateinit var container :LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        container = findViewById(R.id.fragmentContainer)
        val tasks = loadTasks()
        for (task in tasks) {
            addTask(task)
        }

    }
    fun loadTasks(): List<Task> {
        val prefs = getSharedPreferences("tasks", Context.MODE_PRIVATE)
        val jsonString = prefs.getString("taskList", null)

        val taskList = mutableListOf<Task>()
        if (jsonString != null) {
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                val check = obj.getBoolean("check")
                val name = obj.getString("name")
                taskList.add(Task(check,name))
            }

        }
        return taskList
    }

    override fun onPause() {
        super.onPause()

        var taskList = mutableListOf<Task>()
        val fragments = supportFragmentManager.fragments
        for (fragment in fragments) {
            if (fragment is TaskFragment) {
                val check = fragment.view?.findViewById<CheckBox>(R.id.checkBoxDone)?.isActivated.let { false }
                val text = fragment.view?.findViewById<EditText>(R.id.taskText)?.text.toString()
                taskList.add(Task(check,text))
            }
        }
        saveTasks(taskList)
    }


    fun saveTasks(taskList: List<Task>) {
        val prefs = getSharedPreferences("tasks", Context.MODE_PRIVATE)
        val editor = prefs.edit()

        val jsonArray = JSONArray()

        for (task in taskList) {
            val jsonObject = JSONObject()
            jsonObject.put("name", task.name)
            jsonObject.put("check", task.check)
            jsonArray.put(jsonObject)
        }

        editor.putString("taskList", jsonArray.toString())
        editor.apply()
    }

    fun addTask(view: View){
        addTask(Task(false,""))
    }

    fun addTask(task: Task){
        val fragment = TaskFragment.newInstance(task.check,task.name,this)
        fragment.listener = this
        supportFragmentManager.beginTransaction()
            .add(container.id, fragment)
            .commit()
    }
    override fun onDeleteTask(fragment: Fragment) {
        val thisContainer = fragment.view?.parent as? View
        supportFragmentManager.beginTransaction()
            .remove(fragment)
            .commit()
        container.removeView(thisContainer)
    }


}