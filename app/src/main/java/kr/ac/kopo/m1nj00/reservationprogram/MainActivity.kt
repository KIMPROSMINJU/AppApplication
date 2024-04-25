package kr.ac.kopo.m1nj00.reservationprogram

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.Chronometer
import android.widget.DatePicker
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import java.util.Calendar
import java.util.Date

class MainActivity : AppCompatActivity() {
    lateinit var chrono : Chronometer
    lateinit var rg : RadioGroup
    lateinit var calendar : DatePicker
    lateinit var timePick : TimePicker
    lateinit var textResult : TextView
    var selectedYear : Int = 0
    var selectedMonth : Int = 0
    var selectedDay : Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chrono = findViewById(R.id.chrono)
        rg = findViewById(R.id.rg)
        calendar = findViewById(R.id.calendar)
        timePick = findViewById(R.id.timePick)
        textResult = findViewById(R.id.textResult)

        calendar.visibility = View.INVISIBLE
        timePick.visibility = View.INVISIBLE

        rg.setOnCheckedChangeListener(rgListner)
        chrono.setOnClickListener{
            chrono.base = SystemClock.elapsedRealtime()
            chrono.start()
            chrono.setTextColor(Color.MAGENTA)
        }

        textResult.setOnClickListener{
            chrono.stop()
            chrono.setTextColor(Color.CYAN)
            textResult.setText("" + selectedYear + "년 " + selectedMonth + "월 " + selectedDay + "일")
            textResult.append(" " + timePick.currentHour + "시 ")
            textResult.append("" + timePick.currentMinute + "분 예약됨")
        }
        calendar.setOnDateChangedListener { view, year, month, dayOfMonth ->
            selectedYear = year
            selectedMonth = month + 1 // 월은 0부터 시작하므로 1을 더해줌
            selectedDay = dayOfMonth
        }

    }
    var rgListner = OnCheckedChangeListener{group, checkedId->
        calendar.visibility = View.INVISIBLE
        timePick.visibility = View.INVISIBLE
        when(rg.checkedRadioButtonId){
            R.id.rbDate -> calendar.visibility = View.VISIBLE
            R.id.rbTime -> timePick.visibility = View.VISIBLE
        }
    }
}