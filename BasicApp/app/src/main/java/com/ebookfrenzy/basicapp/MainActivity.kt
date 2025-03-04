package com.ebookfrenzy.basicapp

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // insets is used to set resize app in order to not overlap to system elements, such as Keyboards or menu nav
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.currency_input)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    fun convertCurrency(view :View){
        val dollarText :EditText = findViewById(R.id.currency_input)
        val textView : TextView = findViewById(R.id.convert_string)

        if(dollarText.text.isNotEmpty()){
            val dollarValue = dollarText.text.toString().toFloatOrNull()
            val euro = dollarValue?.times( 0.85f)
            euro.toString().also { textView.text = it }
        }
    }
}