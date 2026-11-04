package com.example.mycalculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var currentInput = ""
    private var firstOperand = 0.0
    private var operator = ""
    private var isNewInput = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        // 設定數字按鈕事件
        val numberIds = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in numberIds) {
            findViewById<Button>(id).setOnClickListener { view ->
                val btn = view as Button
                appendNumber(btn.text.toString())
            }
        }

        // 運算符按鈕事件
        findViewById<Button>(R.id.btnAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperator("×") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperator("÷") }

        // 功能按鈕事件
        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.btnAC).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.btnC).setOnClickListener { clearCurrent() }
    }

    private fun appendNumber(num: String) {
        if (isNewInput) {
            currentInput = num
            isNewInput = false
        } else {
            currentInput += num
        }
        tvDisplay.text = currentInput
    }

    private fun setOperator(op: String) {
        firstOperand = currentInput.toDoubleOrNull() ?: 0.0
        operator = op
        isNewInput = true
    }

    private fun calculateResult() {
        val secondOperand = currentInput.toDoubleOrNull() ?: 0.0
        var result = 0.0

        when (operator) {
            "+" -> result = firstOperand + secondOperand
            "-" -> result = firstOperand - secondOperand
            "×" -> result = firstOperand * secondOperand
            "÷" -> result = if (secondOperand != 0.0) firstOperand / secondOperand else 0.0
        }

        currentInput = if (result % 1.0 == 0.0) {
            result.toLong().toString()
        } else {
            result.toString()
        }

        tvDisplay.text = currentInput
        isNewInput = true
    }

    private fun clearAll() {
        currentInput = ""
        firstOperand = 0.0
        operator = ""
        isNewInput = true
        tvDisplay.text = "0"
    }

    private fun clearCurrent() {
        currentInput = ""
        tvDisplay.text = "0"
        isNewInput = true
    }
}