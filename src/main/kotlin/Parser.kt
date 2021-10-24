class Parser {
    fun parse(args: Array<String>) {
        if (args.size != 3) {
            print("Invalid number of arguments")
            throw IllegalArgumentException("Invalid number of arguments")
        }
        val operator = args[1]
        val calculator = Calculator()
        if (!calculator.isInt(args[0]) || !calculator.isInt(args[2])) {
            print("First or third argument is not a number")
            throw IllegalArgumentException("First or third argument is not a number")
        }
        if (operator != "+" && operator != "-" && operator != "*" && operator != "/") {
            print("Operator is not supported")
            throw IllegalArgumentException("Operator is not supported")
        }
    }
}