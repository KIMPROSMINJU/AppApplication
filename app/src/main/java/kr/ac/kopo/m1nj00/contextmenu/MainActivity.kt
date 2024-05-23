package kr.ac.kopo.m1nj00.contextmenu

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    lateinit var linear : LinearLayout
    lateinit var btnLinear : Button
    lateinit var btnRotation : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linear = findViewById<LinearLayout>(R.id.linear)
        btnLinear = findViewById<Button>(R.id.btnLinearBg)
        btnRotation = findViewById<Button>(R.id.btnRotation)

        registerForContextMenu(btnLinear)
        registerForContextMenu(btnRotation)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        var menuInflater = this.menuInflater

        if (v === btnLinear){
            menu!!.setHeaderTitle("배경색 변경")
            menuInflater.inflate(R.menu.menu1_context, menu)
        }

        if (v === btnRotation){
            menu!!.setHeaderTitle("버튼 회전 및 확대")
            menuInflater.inflate(R.menu.menu2_context, menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        super.onContextItemSelected(item)
        when(item.itemId){
            R.id.itemRed ->{
                linear.setBackgroundColor(Color.RED)
                return true
            }
            R.id.itemBlue ->{
                linear.setBackgroundColor(Color.BLUE)
                return true
            }
            R.id.itemYellow ->{
                linear.setBackgroundColor(Color.YELLOW)
                return true
            }
            R.id.itemRotation ->{
                btnRotation.rotation = 90f
                return true
            }
            R.id.itemZoom ->{
                btnRotation.scaleX = 2f
                btnRotation.scaleY = 2f
                return true
            }
        }
        return false
    }
}