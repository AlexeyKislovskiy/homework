
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {
    private final ByteArrayOutputStream os = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(os));
    }

    @Test
    public void mainTest() {
        new Main().main(new String[]{"5", "-", "2"});
        Assert.assertEquals("Result is: 3", os.toString());
    }
}