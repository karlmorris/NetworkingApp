package edu.temple.networkingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var textView:TextView

    val downloadHandler = Handler(Looper.getMainLooper()) {
        textView.text = it.obj.toString()
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        Thread {
            val url = URL("https://www.temple.edu")

            url.openStream()
                .bufferedReader().apply {
                    val strBuilder = StringBuilder()
                    while(readLine().let{
                            strBuilder.append(it.plus("\n"))
                            it != null
                        });

                    Log.d("Website", strBuilder.toString())
                }
        }.start()
    }

}