package com.example.geographyquiz

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultText = findViewById<TextView>(R.id.result_text)
        val userAnswers = intent.getIntArrayExtra("userAnswers") ?: IntArray(0)
        val correctAnswers = intent.getIntArrayExtra("correctAnswers") ?: IntArray(0)

        var correctCount = 0
        for (i in userAnswers.indices) {
            if (userAnswers[i] == correctAnswers[i]) {
                correctCount++
            }
        }

        val result =
            """Ваш результат:
            | $correctCount из ${correctAnswers.size}""".trimMargin()
        resultText.text = result
    }
}