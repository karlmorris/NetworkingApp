package edu.temple.networkingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.webkit.WebView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var webView: WebView

    lateinit var requestQueue: RequestQueue

    val downloadHandler = Handler(Looper.getMainLooper()) {

        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestQueue = Volley.newRequestQueue(this)

        webView = findViewById(R.id.webView)


        requestQueue.add(
            StringRequest(Request.Method.GET, "https://www.temple.edu", {
                webView.loadDataWithBaseURL("", it, "text/html", "utf-8", null)
            }, {})
        )



        /*
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

         */
    }

}