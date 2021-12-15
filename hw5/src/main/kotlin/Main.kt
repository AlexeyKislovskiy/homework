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

inline fun <reified T> add(n1: T, n2: T): T {
    return when (T::class) {
        Int::class -> (n1 as Int + n2 as Int) as T
        Float::class -> (n1 as Float + n2 as Float) as T
        else -> (n1 as Double + n2 as Double) as T
    }
}

inline fun <reified T> subtract(n1: T, n2: T): T {
    return when (T::class) {
        Int::class -> (n1 as Int - n2 as Int) as T
        Float::class -> (n1 as Float - n2 as Float) as T
        else -> (n1 as Double - n2 as Double) as T
    }
}

inline fun <reified T> multiply(n1: T, n2: T): T {
    return when (T::class) {
        Int::class -> (n1 as Int * n2 as Int) as T
        Float::class -> (n1 as Float * n2 as Float) as T
        else -> (n1 as Double * n2 as Double) as T
    }
}

inline fun <reified T> divide(n1: T, n2: T): T {
    return when (T::class) {
        Int::class -> (n1 as Int / n2 as Int) as T
        Float::class -> (n1 as Float / n2 as Float) as T
        else -> (n1 as Double / n2 as Double) as T
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


fun isDouble(arg: String): Boolean {
    return try {
        arg.toDouble()
        true
    } catch (e: NumberFormatException) {
        false
    }
}

fun readArgs(args: Array<String>): Either<CalculatorException.IncorrectNumOfArgs, Array<String>> =
    if (args.size != 3) Either.Left(CalculatorException.IncorrectNumOfArgs)
    else Either.Right(args)

fun checkOperator(args: Array<String>): Either<CalculatorException.IncorrectOperator, Array<String>> =
    if (args[1] == "+" || args[1] == "-" || args[1] == "/" || args[1] == "*") Either.Right(args)
    else Either.Left(CalculatorException.IncorrectOperator)

fun checkZeroDivision(args: Array<String>): Either<CalculatorException.ZeroDivision, Array<String>> =
    if (args[1] == "/" && isDouble(args[2]) && args[2].toDouble() == 0.0) Either.Left(CalculatorException.ZeroDivision)
    else Either.Right(args)

fun checkArgument(args: Array<String>): Either<CalculatorException.IncorrectArgument, Array<String>> =
    if (!(isDouble(args[0]) && isDouble(args[2]))) Either.Left(CalculatorException.IncorrectArgument)
    else Either.Right(args)

inline fun <reified T> calculate(n: Pair<T, T>, operator: String): Either<CalculatorException.IncorrectArgument, T> {
    return when (operator) {
        "+" -> Either.Right(add(n.first, n.second))
        "-" -> Either.Right(subtract(n.first, n.second))
        "/" -> Either.Right(divide(n.first, n.second))
        "*" -> Either.Right(multiply(n.first, n.second))
        else -> Either.Left(CalculatorException.IncorrectArgument)
    }
}

suspend fun returnAns(args: Array<String>): Either<CalculatorException, Number> {
    val value = either<CalculatorException, Number> {
        readArgs(args).bind()
        checkOperator(args).bind()
        checkArgument(args).bind()
        checkZeroDivision(args).bind()
        if (isInt(args[0]) && isInt(args[2]))
            calculate(Pair(args[0].toInt(), args[2].toInt()), args[1]).bind()
        else calculate(Pair(args[0].toDouble(), args[2].toDouble()), args[1]).bind()
    }
    return value
}


suspend fun main(args: Array<String>) {
    runBlocking {
        val value = returnAns(args)
        println(value.fold({ it.toString() }, { it }))
    }
}
