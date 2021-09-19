import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ParserTest {
    private val os = ByteArrayOutputStream()
    private val parser = Parser()

    @Before
    fun setUpStreams() {
        System.setOut(PrintStream(os))
    }

    @Test
    fun incorrectNumberOfArgumentsTest() {
        val args = arrayOf("1", "+", "2", "3")
        try {
            parser.parse(args)
        } catch (e: IllegalArgumentException) {
            Assert.assertEquals(e.message, "Invalid number of arguments")
        }
    }

    @Test
    fun incorrectTypeOfArgumentsTest1() {
        val args = arrayOf("1", "+", "k")
        try {
            parser.parse(args)
        } catch (e: IllegalArgumentException) {
            Assert.assertEquals(e.message, "First or third argument is not a number")
        }
    }

    @Test
    fun incorrectTypeOfArgumentsTest2() {
        val args = arrayOf("k", "+", "1")
        try {
            parser.parse(args)
        } catch (e: IllegalArgumentException) {
            Assert.assertEquals(e.message, "First or third argument is not a number")
        }
    }

    @Test
    fun incorrectOperatorTest() {
        val args = arrayOf("1", "**", "2")
        try {
            parser.parse(args)
        } catch (e: IllegalArgumentException) {
            Assert.assertEquals(e.message, "Operator is not supported")
        }
    }

    @Test
    fun correctArgumentsTest() {
        val args = arrayOf("1", "+", "2")
        parser.parse(args)
        Assert.assertEquals("", os.toString())
    }
}