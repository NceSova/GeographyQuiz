package com.example.geographyquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class StartActivity : AppCompatActivity() {

    lateinit var button: Button
    var questions = mutableListOf<Question>()
    var text = "nothing"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        button = findViewById(R.id.Start)
        val thread = Thread {
            val client = OkHttpClient()

            val request: Request = Request.Builder()
                .url("https://raw.githubusercontent.com/NceSova/Questions/main/questions")
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                text = response.body!!.string()
                Log.e("Done", "done")
            }
        }
        thread.start()
        button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("json", text)
            startActivity(intent)
        }
    }
}