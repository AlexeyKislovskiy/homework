
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ParserTest {
    private final ByteArrayOutputStream os = new ByteArrayOutputStream();
    private final Parser parser = new Parser();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(os));
    }

    @Test
    public void incorrectNumberOfArgumentsTest() {
        String[] args = {"1", "+", "2", "3"};
        try {
            parser.parse(args);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Invalid number of arguments");
        }
    }

    @Test
    public void incorrectTypeOfArgumentsTest1() {
        String[] args = {"1", "+", "k"};
        try {
            parser.parse(args);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "First or third argument is not a number");
        }
    }

    @Test
    public void incorrectTypeOfArgumentsTest2() {
        String[] args = {"k", "+", "1"};
        try {
            parser.parse(args);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "First or third argument is not a number");
        }
    }

    @Test
    public void incorrectOperatorTest() {
        String[] args = {"1", "**", "2"};
        try {
            parser.parse(args);
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Operator is not supported");
        }
    }

    @Test
    public void correctArgumentsTest() {
        String[] args = {"1", "+", "2"};
        parser.parse(args);
        Assert.assertEquals("", os.toString());
    }
}