package edu.temple.networkingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.webkit.WebView
import android.widget.TextView
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var webView: WebView

    val downloadHandler = Handler(Looper.getMainLooper()) {
        webView.loadDataWithBaseURL("", it.obj.toString(), "text/html", "utf-8", null)
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        Thread {
            val url = URL("https://www.temple.edu")

            url.openStream()
                .bufferedReader().apply {
                    val strBuilder = StringBuilder()
                    while(readLine().let{
                            strBuilder.append(it.plus("\n"))
                            it != null
                        });

                    downloadHandler.sendMessage(Message.obtain().apply { obj = strBuilder.toString() })

                }
        }.start()
    }

}