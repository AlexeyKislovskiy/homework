import arrow.core.Either
import arrow.core.computations.either
import kotlinx.coroutines.runBlocking

sealed class CalculatorException {
    object IncorrectNumOfArgs : CalculatorException()
    object IncorrectOperator : CalculatorException()
    object IncorrectArgument : CalculatorException()
    object ZeroDivision : CalculatorException()

    override fun toString(): String = when(this){
        IncorrectNumOfArgs -> "Invalid number of arguments"
        IncorrectOperator -> "Operator is not supported"
        IncorrectArgument -> "First or third argument is not an integer"
        ZeroDivision -> "Can't divide by 0"
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

fun readArgs(args: Array<String>): Either<CalculatorException.IncorrectNumOfArgs, Array<String>> =
    if (args.size != 3) Either.Left(CalculatorException.IncorrectNumOfArgs)
    else Either.Right(args)

fun checkOperator(args: Array<String>): Either<CalculatorException.IncorrectOperator, Array<String>> =
    if (args[1] == "+" || args[1] == "-" || args[1] == "/" || args[1] == "*") Either.Right(args)
    else Either.Left(CalculatorException.IncorrectOperator)

fun checkZeroDivision(args: Array<String>): Either<CalculatorException.ZeroDivision, Array<String>> =
    if (args[1] == "/" && args[2] == "0") Either.Left(CalculatorException.ZeroDivision)
    else Either.Right(args)

fun calculate(args: Array<String>): Either<CalculatorException.IncorrectArgument, Int> =
    if (!isInt(args[0]) || !isInt(args[2])) Either.Left(CalculatorException.IncorrectArgument)
    else when (args[1]) {
        "+" -> Either.Right(Integer.parseInt(args[0]) + Integer.parseInt(args[2]))
        "-" -> Either.Right(Integer.parseInt(args[0]) - Integer.parseInt(args[2]))
        "/" -> Either.Right(Integer.parseInt(args[0]) / Integer.parseInt(args[2]))
        "*" -> Either.Right(Integer.parseInt(args[0]) * Integer.parseInt(args[2]))
        else -> Either.Left(CalculatorException.IncorrectArgument)
    }

suspend fun main(args: Array<String>) {
    runBlocking {
        val value = either<CalculatorException, Int> {
            readArgs(args).bind()
            checkOperator(args).bind()
            checkZeroDivision(args).bind()
            calculate(args).bind()
        }
        println(value.fold({ it.toString() }, { it }))
    }
}