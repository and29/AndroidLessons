package it.liceonewton.todolist

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

data class  Task (var check : ObservableField<Boolean>,
                  var name :  ObservableField<String>): ViewModel(){
    constructor(check: Boolean, name: String?) : this(
        ObservableField(check),
        ObservableField(name)
    )

    fun setName(newText: String) {
        name.set(newText)
    }

    fun setCheck(newCheck: Boolean) {
        check.set(newCheck)
    }
}