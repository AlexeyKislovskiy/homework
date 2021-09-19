import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CalculatorTest {
    private val os = ByteArrayOutputStream()
    private val calculator = Calculator()

    @Before
    fun setUpStreams() {
        System.setOut(PrintStream(os))
    }

    @Test
    fun multiplicationTest() {
        calculator.calculate(17, "*", 2)
        Assert.assertEquals("Result is: 34", os.toString())
    }

    @Test
    fun divisionTest() {
        calculator.calculate(8, "/", 2)
        Assert.assertEquals("Result is: 4", os.toString())
    }

    @Test
    fun zeroDivisionTest() {
        calculator.calculate(8, "/", 0)
        Assert.assertEquals("Сan't divide by 0", os.toString())
    }

    @Test
    fun additionTest() {
        calculator.calculate(5, "+", 7)
        Assert.assertEquals("Result is: 12", os.toString())
    }

    @Test
    fun subtractionTest() {
        calculator.calculate(11, "-", 3)
        Assert.assertEquals("Result is: 8", os.toString())
    }

    @Test
    fun incorrectOperatorTest() {
        calculator.calculate(1, "+*", 2)
        Assert.assertEquals("Incorrect operator", os.toString())
    }

    @Test
    fun intTest() {
        Assert.assertTrue(calculator.isInt("34"))
    }

    @Test
    fun notIntTest() {
        Assert.assertFalse(calculator.isInt("0'="))
    }
}