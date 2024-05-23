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
            var dialog = AlertDialog.Builder(this@MainActivity)
            dialog.setTitle("곧 갈 여행지")
            dialog.setIcon(R.drawable.icon1)
            dialog.setItems(placesArr){ d1, which ->
                btnDialog.text = placesArr[which]
            }
            dialog.setPositiveButton("닫기") {dialog, which ->
                Toast.makeText(this@MainActivity, "성공적으로 종료했습니당", Toast.LENGTH_SHORT).show()
            }
            dialog.show()
        }
    }
}