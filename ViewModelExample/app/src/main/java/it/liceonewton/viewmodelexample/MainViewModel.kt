package it.liceonewton.viewmodelexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val rate = 0.74f
    var dollarText = ""
        set(value) {
            field = value
            result.value = value.toFloat()*rate
        }
    var result : MutableLiveData<Float> = MutableLiveData()
        private set


}