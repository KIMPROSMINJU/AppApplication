package kr.ac.kopo.m1nj00.tabhost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.URLUtil
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var editUrl : EditText
    lateinit var btnGo : Button
    lateinit var btnPrev : Button
    lateinit var webView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editUrl = findViewById<EditText>(R.id.editUrl)
        btnGo = findViewById<Button>(R.id.btnGo)
        btnPrev = findViewById<Button>(R.id.btnprev)
        webView = findViewById(R.id.webView)

        webView.webViewClient = PolyWebViewClient()

        var webSet = webView.settings
        webSet.builtInZoomControls = true
        webSet.javaScriptEnabled = true

        btnGo.setOnClickListener{
            webView.loadUrl(editUrl.text.toString())
        }

        btnPrev.setOnClickListener{
            webView.goBack();
        }
    }

    class PolyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            return super.shouldOverrideUrlLoading(view, url)
        }
    }
}