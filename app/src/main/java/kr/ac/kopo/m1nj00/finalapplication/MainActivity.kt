package kr.ac.kopo.m1nj00.finalapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.DatePicker.OnDateChangedListener
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.io.IOException
import java.util.Calendar


class MainActivity : AppCompatActivity() {
    var dp: DatePicker? = null
    var edtDiary: EditText? = null
    var btnWrite: Button? = null
    var fileName: String? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "간단 일기장"
        dp = findViewById(R.id.datePicker1)
        edtDiary = findViewById(R.id.edtDiary)
        btnWrite = findViewById(R.id.btnWrite)
        val cal = Calendar.getInstance()
        val cYear = cal[Calendar.YEAR]
        val cMonth = cal[Calendar.MONTH]
        val cDay = cal[Calendar.DAY_OF_MONTH]
        fileName = cYear.toString() + "_" + (cMonth + 1) + "_" + cDay + ".txt"
        val str = readDiary(fileName)
        edtDiary?.setText(str)
        (dp as? DatePicker)?.init(cYear, cMonth, cDay,
            DatePicker.OnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
                fileName = year.toString() + "_" + (monthOfYear + 1) + "_" + dayOfMonth + ".txt"
                val str = readDiary(fileName)
                edtDiary?.setText(str)
                btnWrite?.isEnabled = true
            })
        btnWrite?.let { button ->
            button.setOnClickListener {
                try {
                    val outFs = openFileOutput(fileName, MODE_PRIVATE)
                    val str = edtDiary?.text.toString()
                    outFs.write(str.toByteArray())
                    outFs.close()
                    Toast.makeText(applicationContext, "$fileName 이 저장됨", Toast.LENGTH_SHORT).show()
                } catch (e: IOException) {
                    // Handle exception
                }
            }
        }
    }

    fun readDiary(fName: String?): String? {
        var diaryStr: String? = null
        val inFs: FileInputStream
        try {
            inFs = openFileInput(fName)
            val txt = ByteArray(500)
            inFs.read(txt)
            inFs.close()
            diaryStr = String(txt).trim { it <= ' ' }
            btnWrite!!.text = "수정 하기"
        } catch (e: IOException) {
            edtDiary!!.hint = "일기 없음"
            btnWrite!!.text = "새로 저장"
        }
        return diaryStr
    }
}