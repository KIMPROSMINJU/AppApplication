package kr.ac.kopo.m1nj00.autocompeletetextviewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.MultiAutoCompleteTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var items = arrayOf("apple", "appstore", "abstract", "barely", "between", "bind", "fake", "fold")

        var adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)

        var auto = findViewById<AutoCompleteTextView>(R.id.auto)
        auto.setAdapter(adapter)

        var multiauto = findViewById<MultiAutoCompleteTextView>(R.id.multiauto)
        var token = MultiAutoCompleteTextView.CommaTokenizer()
        multiauto.setAdapter(adapter)
    }
}