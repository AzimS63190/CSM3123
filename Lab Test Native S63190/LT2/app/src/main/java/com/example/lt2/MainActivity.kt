//Name      : Wan Abdul Azim Bin Wan Malek
//Matric No : S63190
//Date      : 4 January 2024
//Lab Test Native

package com.example.lt2

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    // Define variables
    private lateinit var levelRadioGroup: RadioGroup
    private lateinit var i3RadioButton: RadioButton
    private lateinit var i5RadioButton: RadioButton
    private lateinit var i7RadioButton: RadioButton
    private lateinit var questionTextView: TextView
    private lateinit var answerEditText: TextView
    private lateinit var checkButton: Button
    private lateinit var pointTextView: TextView

    private var currentLevel: Int = 0
    private var currentPoints: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        levelRadioGroup = findViewById(R.id.radio_group)
        i3RadioButton = findViewById(R.id.radiobtn1)
        i5RadioButton = findViewById(R.id.radiobtn2)
        i7RadioButton = findViewById(R.id.radiobtn3)
        questionTextView = findViewById(R.id.textview2)
        answerEditText = findViewById(R.id.editText)
        checkButton = findViewById(R.id.button)
        pointTextView = findViewById(R.id.textview8)

        // Set listener for the check button
        checkButton.setOnClickListener {
            checkAnswer()
            displayQuestion()
        }

        // Set listener for the level radio group
        levelRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radiobtn1 -> currentLevel = 1
                R.id.radiobtn2 -> currentLevel = 2
                R.id.radiobtn3 -> currentLevel = 3
            }
            displayQuestion()
        }

        // Initially, set the level to i3
        i3RadioButton.isChecked = true
    }

    private fun displayQuestion() {
        val random = Random()
        val operand1 = random.nextInt(10)
        val operand2 = if (currentLevel >= 2) random.nextInt(100) else random.nextInt(10)
        val operator = random.nextInt(3) // 0: '+', 1: '-', 2: '*'

        val operatorSymbol = when (operator) {
            0 -> "+"
            1 -> "-"
            else -> "*"
        }

        val question = "$operand1 $operatorSymbol $operand2"
        questionTextView.text = question
    }

    private fun checkAnswer() {
        val userAnswer = answerEditText.text.toString().toIntOrNull()
        if (userAnswer != null) {
            val questionParts = questionTextView.text.toString().split(" ")
            val operand1 = questionParts[0].toInt()
            val operator = questionParts[1]
            val operand2 = questionParts[2].toInt()

            val correctAnswer = when (operator) {
                "+" -> operand1 + operand2
                "-" -> operand1 - operand2
                "*" -> operand1 * operand2
                else -> 0 // Default case, should not happen
            }

            if (userAnswer == correctAnswer) {
                currentPoints++
            } else {
                currentPoints--
            }
            pointTextView.text = currentPoints.toString()
        }
    }
}