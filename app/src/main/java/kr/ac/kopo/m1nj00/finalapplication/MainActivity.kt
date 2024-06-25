package kr.ac.kopo.m1nj00.finalapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private var dp: DatePicker? = null
    private var edtDiary: EditText? = null
    private var btnWrite: Button? = null
    private var fileName: String? = null
    private var btnSecretDiary: Button? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Secret Diary"

        dp = findViewById(R.id.datePicker1)
        edtDiary = findViewById(R.id.edtDiary)
        btnWrite = findViewById(R.id.btnWrite)
        btnSecretDiary = findViewById(R.id.btnSecretDiary)

        val cal = Calendar.getInstance()
        val cYear = cal[Calendar.YEAR]
        val cMonth = cal[Calendar.MONTH]
        val cDay = cal[Calendar.DAY_OF_MONTH]
        fileName = "${cYear}.${cMonth + 1}.$cDay recording"
        val str = readDiary(fileName)
        edtDiary?.setText(str)

        dp?.init(cYear, cMonth, cDay, DatePicker.OnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            fileName = "${year}.${monthOfYear + 1}.$dayOfMonth recording"
            val str = readDiary(fileName)
            edtDiary?.setText(str)
            btnWrite?.isEnabled = true
        })

        btnWrite?.setOnClickListener {
            try {
                val outFs = openFileOutput(fileName, MODE_PRIVATE)
                val str = edtDiary?.text.toString()
                outFs.write(str.toByteArray())
                outFs.close()

                if (btnWrite?.text == "New Writing") {
                    Toast.makeText(applicationContext, "$fileName enrolled!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(applicationContext, "$fileName modified!", Toast.LENGTH_SHORT).show()
                }
                btnWrite?.text = "Written : modify?"
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        btnSecretDiary?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun readDiary(fName: String?): String? {
        var diaryStr: String? = null
        try {
            val inFs = openFileInput(fName)
            val txt = ByteArray(500)
            inFs.read(txt)
            inFs.close()
            diaryStr = String(txt).trim()
            btnWrite?.text = "Written : modify?"
        } catch (e: IOException) {
            edtDiary?.hint = "Write your own daylife"
            btnWrite?.text = "New Writing"
        }
        return diaryStr
    }
}
