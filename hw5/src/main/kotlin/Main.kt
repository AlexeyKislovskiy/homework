import arrow.core.Either
import arrow.core.computations.either
import kotlinx.coroutines.runBlocking

sealed class CalculatorException {
    object IncorrectNumOfArgs : CalculatorException()
    object IncorrectOperator : CalculatorException()
    object IncorrectArgument : CalculatorException()
    object ZeroDivision : CalculatorException()

    override fun toString(): String = when (this) {
        IncorrectNumOfArgs -> "Invalid number of arguments"
        IncorrectOperator -> "Operator is not supported"
        IncorrectArgument -> "First or third argument is not a number"
        ZeroDivision -> "Can't divide by 0"
    }
}

fun isInt(arg: String): Boolean {
    return try {
        arg.toInt()
        true
    } catch (e: NumberFormatException) {
        false
    }
}

fun isFloat(arg: String): Boolean {
    return try {
        arg.toFloat()
        arg.toFloat().toDouble() == arg.toDouble()
    } catch (e: NumberFormatException) {
        false
    }
}

fun isDouble(arg: String): Boolean {
    return try {
        arg.toDouble()
        true
    } catch (e: NumberFormatException) {
        false
    }
}

fun isNumber(arg: String): Boolean {
    return isInt(arg) || isFloat(arg) || isDouble(arg)
}

fun add(n1: String, n2: String): Number {
    return if (isInt(n1) && isInt(n2)) n1.toInt() + n2.toInt()
    else if (isFloat(n1) && isFloat(n2)) n1.toFloat() + n2.toFloat()
    else n1.toDouble() + n2.toDouble()
}

fun subtract(n1: String, n2: String): Number {
    return if (isInt(n1) && isInt(n2)) n1.toInt() - n2.toInt()
    else if (isFloat(n1) && isFloat(n2)) n1.toFloat() - n2.toFloat()
    else n1.toDouble() - n2.toDouble()
}

fun divide(n1: String, n2: String): Number {
    return if (isInt(n1) && isInt(n2)) n1.toInt() / n2.toInt()
    else if (isFloat(n1) && isFloat(n2)) n1.toFloat() / n2.toFloat()
    else n1.toDouble() / n2.toDouble()
}

fun multiply(n1: String, n2: String): Number {
    return if (isInt(n1) && isInt(n2)) n1.toInt() * n2.toInt()
    else if (isFloat(n1) && isFloat(n2)) n1.toFloat() * n2.toFloat()
    else n1.toDouble() * n2.toDouble()
}

fun readArgs(args: Array<String>): Either<CalculatorException.IncorrectNumOfArgs, Array<String>> =
    if (args.size != 3) Either.Left(CalculatorException.IncorrectNumOfArgs)
    else Either.Right(args)

fun checkOperator(args: Array<String>): Either<CalculatorException.IncorrectOperator, Array<String>> =
    if (args[1] == "+" || args[1] == "-" || args[1] == "/" || args[1] == "*") Either.Right(args)
    else Either.Left(CalculatorException.IncorrectOperator)

fun checkZeroDivision(args: Array<String>): Either<CalculatorException.ZeroDivision, Array<String>> =
    if (args[1] == "/" && args[2] == "0") Either.Left(CalculatorException.ZeroDivision)
    else Either.Right(args)

fun calculate(args: Array<String>): Either<CalculatorException.IncorrectArgument, Number> =
    if (!isNumber(args[0]) || !isNumber(args[2])) Either.Left(CalculatorException.IncorrectArgument)
    else when (args[1]) {
        "+" -> Either.Right(add(args[0], args[2]))
        "-" -> Either.Right(subtract(args[0], args[2]))
        "/" -> Either.Right(divide(args[0], args[2]))
        "*" -> Either.Right(multiply(args[0], args[2]))
        else -> Either.Left(CalculatorException.IncorrectArgument)
    }

suspend fun returnAns(args: Array<String>): Either<CalculatorException, Number> {
    val value = either<CalculatorException, Number> {
        readArgs(args).bind()
        checkOperator(args).bind()
        checkZeroDivision(args).bind()
        calculate(args).bind()
    }
    return value
}


suspend fun main(args: Array<String>) {
    runBlocking {
        val value = returnAns(args)
        println(value.fold({ it.toString() }, { it }))
    }
}