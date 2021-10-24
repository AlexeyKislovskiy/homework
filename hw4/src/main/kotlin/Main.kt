import arrow.core.partially1

fun main(args: Array<String>) {
    parse(args)
    val val1 = args[0].toInt()
    val operator = args[1]
    val val2 = args[2].toInt()
    val ans = when (operator) {
        "+" -> calculate(Pair(add.partially1(val1), val2));
        "-" -> calculate(Pair(subtract.partially1(val1), val2));
        "/" -> calculate(Pair(divide.partially1(val1), val2));
        "*" -> calculate(Pair(multiply.partially1(val1), val2));
        else -> 0;
    }
    println(ans)
}