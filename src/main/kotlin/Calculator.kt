class Calculator {
    fun calculate(val1: Int, operator: String, val2: Int) {
        if (operator == "/" && val2 == 0) print("Сan't divide by 0")
        else {
            val result = when (operator) {
                "+" -> val1 + val2
                "-" -> val1 - val2
                "*" -> val1 * val2
                "/" -> val1 / val2
                else -> null
            }
            if (result == null) print("Incorrect operator")
            else print("Result is: $result")
        }
    }

    fun isInt(arg: String): Boolean {
        return try {
            Integer.parseInt(arg)
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}