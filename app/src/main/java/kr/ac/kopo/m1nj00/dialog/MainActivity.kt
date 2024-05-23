package kr.ac.kopo.m1nj00.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnDialog = findViewById<Button>(R.id.btnDialog)
        btnDialog.setOnClickListener{
            var placesArr = arrayOf("제주도", "대만", "일본")
            var selectArr = booleanArrayOf()
            var dialog = AlertDialog.Builder(this@MainActivity)
            dialog.setTitle("곧 갈 여행지")
            dialog.setIcon(R.drawable.icon1)
            dialog.setMultiChoiceItems(placesArr, selectArr) { d1, which, isChecked ->
                btnDialog.text = placesArr[which]
            }
            dialog.show()
        }
    }
}