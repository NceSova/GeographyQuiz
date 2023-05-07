package com.example.geographyquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var currentQuestionIndex = 0
    private val userAnswers = IntArray(5)
    private val questionsArray = arrayOf(
        Question(
        "В каких регионах чаще всего встречаются сейсмоопасные районы?",
        "Районы вазимодействия литосферных плит", "Общирные восточные морские побережья", "Степи, полупустыни, пустыни",
        0),
        Question(
            "Какой из перечисленных островов находится в экваториальном климатическом поясе?",
            "Мадагаскар", "Калимантан", "Куба",
            1
        ),
        Question(
            "Какую карту открыть, чтобы увидеть центры машиностроения в России?",
            "Политическую карту", "Карту 'Машиностроение в России'", "Экономическую карту Росcии",
            2
        ),
        Question(
            "Какое из перечисленных озёр относится к числу глубочайших?",
            "Танганьика", "Чад", "Ладожское",
            0
        ),
        Question(
            "Самая глубокая впадина на суше находится на материке",
            "Австралия", "Евразия", "Северная Америка",
            1
        )
    )

    private val correctAnswers = intArrayOf(0, 1, 2, 0, 1)

    private lateinit var questionText: TextView
    private lateinit var answersGroup: RadioGroup
    private lateinit var answer1: RadioButton
    private lateinit var answer2: RadioButton
    private lateinit var answer3: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionText = findViewById(R.id.question_text)
        answersGroup = findViewById(R.id.answers_group)
        answer1 = findViewById(R.id.answer_1)
        answer2 = findViewById(R.id.answer_2)
        answer3 = findViewById(R.id.answer_3)

        val nextButton = findViewById<Button>(R.id.next_button)
        nextButton.setOnClickListener {
            val selectedAnswerIndex = answersGroup.indexOfChild(findViewById(answersGroup.checkedRadioButtonId))
            userAnswers[currentQuestionIndex] = selectedAnswerIndex
            currentQuestionIndex++
            if (currentQuestionIndex < questionsArray.size) {
                updateQuestion()
            } else {
                showResults()
            }
        }

        updateQuestion()
    }

    private fun updateQuestion() {
        questionText.text = questionsArray[currentQuestionIndex].text
        answer1.text = questionsArray[currentQuestionIndex].answer1
        answer2.text = questionsArray[currentQuestionIndex].answer2
        answer3.text = questionsArray[currentQuestionIndex].answer3
        answersGroup.clearCheck()
    }

    private fun showResults() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("userAnswers", userAnswers)
        intent.putExtra("correctAnswers", correctAnswers)
        startActivity(intent)
    }
}