import arrow.core.Either
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CalculatorTest {
    @Test
    fun incorrectNumberOfArgumentsTest() {
        runBlocking {
            val ans = returnAns(arrayOf("1", "+", "2", "3"))
            Assert.assertEquals(Either.Left(CalculatorException.IncorrectNumOfArgs), ans)
        }
    }

    @Test
    fun incorrectOperatorTest() {
        runBlocking {
            val ans = returnAns(arrayOf("1", "|", "2"))
            Assert.assertEquals(Either.Left(CalculatorException.IncorrectOperator), ans)
        }
    }

    @Test
    fun zeroDivisionTest() {
        runBlocking {
            val ans = returnAns(arrayOf("1", "/", "0"))
            Assert.assertEquals(Either.Left(CalculatorException.ZeroDivision), ans)
        }
    }

    @Test
    fun incorrectFirstArgumentTest() {
        runBlocking {
            val ans = returnAns(arrayOf("a", "+", "2"))
            Assert.assertEquals(Either.Left(CalculatorException.IncorrectArgument), ans)
        }
    }

    @Test
    fun incorrectSecondArgumentTest() {
        runBlocking {
            val ans = returnAns(arrayOf("1", "+", "a"))
            Assert.assertEquals(Either.Left(CalculatorException.IncorrectArgument), ans)
        }
    }

    @Test
    fun intAddTest() {
        runBlocking {
            val ans = returnAns(arrayOf("1", "+", "2"))
            Assert.assertEquals(Either.Right(3), ans)
        }
    }

    @Test
    fun floatSubtractTest() {
        runBlocking {
            val ans = returnAns(arrayOf("1.5", "-", "2"))
            Assert.assertEquals(Either.Right(-0.5F), ans)
        }
    }

    @Test
    fun doubleMultiplyTest() {
        runBlocking {
            val ans = returnAns(arrayOf("3.42888396391", "*", "7.2838387732729"))
            Assert.assertEquals(Either.Right(24.975437965381335), ans)
        }
    }

    @Test
    fun intDivideTest() {
        runBlocking {
            val ans = returnAns(arrayOf("5", "/", "2"))
            Assert.assertEquals(Either.Right(2), ans)
        }
    }

    @Test
    fun notIntDivideTest() {
        runBlocking {
            val ans = returnAns(arrayOf("5.0", "/", "2"))
            Assert.assertEquals(Either.Right(2.5F), ans)
        }
    }
}