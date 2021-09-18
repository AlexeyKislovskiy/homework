
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CalculatorTest {
    private final ByteArrayOutputStream os = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(os));
    }

    @Test
    public void multiplicationTest() {
        Calculator.calculate(17, "*", 2);
        Assert.assertEquals("Result is: 34", os.toString());
    }

    @Test
    public void divisionTest() {
        Calculator.calculate(8, "/", 2);
        Assert.assertEquals("Result is: 4", os.toString());
    }

    @Test
    public void zeroDivisionTest(){
        Calculator.calculate(8, "/", 0);
        Assert.assertEquals("Сan't divide by 0", os.toString());
    }

    @Test
    public void additionTest() {
        Calculator.calculate(5, "+", 7);
        Assert.assertEquals("Result is: 12", os.toString());
    }

    @Test
    public void subtractionTest() {
        Calculator.calculate(11, "-", 3);
        Assert.assertEquals("Result is: 8", os.toString());
    }

    @Test
    public void intTest() {
        Assert.assertTrue(Calculator.isInt("34"));
    }

    @Test
    public void notIntTest() {
        Assert.assertFalse(Calculator.isInt("0'="));
    }

}