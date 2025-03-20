package it.liceonewton.todolist

import androidx.fragment.app.Fragment

interface OnTaskDeleteListener {
    fun onDeleteTask(fragment: Fragment) // o posizione nella lista
}