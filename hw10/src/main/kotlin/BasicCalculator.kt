package org.example

import com.example.calcdemo.Calculator

open class BasicCalculator : Calculator {
    override fun calculate(a: Double, b: Double, operator: String): String {
        return if (operator == "+") (a + b).toString()
        else if (operator == "-") (a - b).toString()
        else if (operator == "*") (a * b).toString()
        else if (operator == "/" && b == 0.0) "Can't divide by 0"
        else (a / b).toString()
    }
}