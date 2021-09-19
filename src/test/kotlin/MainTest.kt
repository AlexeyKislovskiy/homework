import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class MainTest {
    private val os = ByteArrayOutputStream()

    @Before
    fun setUpStreams() {
        System.setOut(PrintStream(os))
    }

    @Test
    fun mainTest() {
        Main().main(arrayOf("5", "-", "2"))
        Assert.assertEquals("Result is: 3", os.toString())
    }
}