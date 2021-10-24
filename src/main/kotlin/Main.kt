class Main {
    fun main(args: Array<String>) {
        val parser = Parser()
        parser.parse(args)
        val val1 = args[0].toInt()
        val operator = args[1]
        val val2 = args[2].toInt()
        val calculator = Calculator()
        calculator.calculate(val1, operator, val2)
    }
}