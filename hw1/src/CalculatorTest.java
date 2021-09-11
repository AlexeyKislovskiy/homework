import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CalculatorTest {
    private ByteArrayOutputStream os = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(os));
    }

    @Test
    public void calculateTest() {
        Calculator.calculate(17, "*", 2);
        Assert.assertEquals("Result is: 34", os.toString());
    }

    @Test
    public void isIntTest() {
        Assert.assertTrue(Calculator.isInt("34"));
    }

    @Test
    public void parseTest() {
        String[] args = {"1", "+", "2"};
        Parser.parse(args);
        Assert.assertEquals("", os.toString());
    }

    @Test
    public void mainTest(){
        Main.main(new String[]{"5","-","2"});
        Assert.assertEquals("Result is: 3", os.toString());
    }
}