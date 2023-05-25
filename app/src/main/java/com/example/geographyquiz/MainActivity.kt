package com.example.geographyquiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private var currentQuestionIndex = 0
    private val userAnswers = IntArray(5)


    private val correctAnswers = intArrayOf(0, 1, 2, 0, 1)

    private lateinit var questionText: TextView
    private lateinit var imageView: ImageView
    private lateinit var answersGroup: RadioGroup
    private lateinit var answer1: RadioButton
    private lateinit var answer2: RadioButton
    private lateinit var answer3: RadioButton
    var text = "nothing"
    var questions = mutableListOf<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = intent.getStringExtra("json").toString()

        questionText = findViewById(R.id.question_text)
        imageView = findViewById(R.id.imageView)
        answersGroup = findViewById(R.id.answers_group)
        answer1 = findViewById(R.id.answer_1)
        answer2 = findViewById(R.id.answer_2)
        answer3 = findViewById(R.id.answer_3)

            val gson = Gson()
            val listType = object : TypeToken<List<Question>>() {}.type
            val listofquest = gson.fromJson<List<Question>>(text, listType)
            Log.e("DONE", "Done")
            questions = listofquest.toMutableList()
            Log.e("Done", questions[1].text)


        val nextButton = findViewById<Button>(R.id.next_button)
        nextButton.setOnClickListener {
            val selectedAnswerIndex =
                answersGroup.indexOfChild(findViewById(answersGroup.checkedRadioButtonId))
            userAnswers[currentQuestionIndex] = selectedAnswerIndex
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                updateQuestion()
            } else {
                showResults()
            }
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        questionText.text = questions[currentQuestionIndex].text
//        imageView.setImageResource(questionsArray[currentQuestionIndex].resource)
        answer1.text = questions[currentQuestionIndex].answer1
        answer2.text = questions[currentQuestionIndex].answer2
        answer3.text = questions[currentQuestionIndex].answer3
        answersGroup.clearCheck()
    }

    private fun showResults() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("userAnswers", userAnswers)
        intent.putExtra("correctAnswers", correctAnswers)
        startActivity(intent)
    }
}