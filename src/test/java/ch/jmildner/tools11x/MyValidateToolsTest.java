package ch.jmildner.tools11x;

import ch.jmildner.tools11x.MyValidateTools;
import org.junit.Assert;
import org.junit.Test;

public class MyValidateToolsTest {

    @Test
    public void checkEmpty() {
        Assert.assertTrue(MyValidateTools.isEmpty(""));
        Assert.assertTrue(MyValidateTools.isEmpty(null));
    }

    @Test
    public void checkInteger() {
        Assert.assertTrue(MyValidateTools.isInteger("0"));
        Assert.assertTrue(MyValidateTools.isInteger("125"));
        Assert.assertTrue(MyValidateTools.isInteger("-12"));

        Assert.assertFalse(MyValidateTools.isInteger("123456789123456789"));

        Assert.assertFalse(MyValidateTools.isInteger(""));
        Assert.assertFalse(MyValidateTools.isInteger("four"));
        Assert.assertFalse(MyValidateTools.isInteger(null));
    }

    @Test
    public void checkLong() {
        Assert.assertTrue(MyValidateTools.isLong("0"));
        Assert.assertTrue(MyValidateTools.isLong("125"));
        Assert.assertTrue(MyValidateTools.isLong("-12"));

        Assert.assertTrue(MyValidateTools.isLong("123456789123456789"));

        Assert.assertFalse(MyValidateTools.isLong(""));
        Assert.assertFalse(MyValidateTools.isLong("four"));
        Assert.assertFalse(MyValidateTools.isLong(null));
    }

    @Test
    public void checkFloat() {
        Assert.assertTrue(MyValidateTools.isFloat("0"));
        Assert.assertTrue(MyValidateTools.isFloat("125"));
        Assert.assertTrue(MyValidateTools.isFloat("-12"));

        Assert.assertTrue(MyValidateTools.isFloat("123456789123456789"));

        Assert.assertTrue(MyValidateTools.isFloat("1.12345678912345E38"));
        Assert.assertTrue(MyValidateTools.isFloat("-1.12345678912345E38"));
        Assert.assertTrue(MyValidateTools.isFloat("1.12345678912345E-38"));
        Assert.assertTrue(MyValidateTools.isFloat("-1.12345678912345E-38"));

        Assert.assertTrue(MyValidateTools.isFloat("123456789123456789"));
        Assert.assertTrue(MyValidateTools.isFloat("123456789123456789123456789"));

        Assert.assertFalse(MyValidateTools.isFloat(""));
        Assert.assertFalse(MyValidateTools.isFloat("four"));
        Assert.assertFalse(MyValidateTools.isFloat(null));
    }

    @Test
    public void checkDouble() {
        Assert.assertTrue(MyValidateTools.isDouble("0"));
        Assert.assertTrue(MyValidateTools.isDouble("125"));
        Assert.assertTrue(MyValidateTools.isDouble("-12"));

        Assert.assertTrue(MyValidateTools.isDouble("123456789123456789"));

        Assert.assertTrue(MyValidateTools.isDouble("1.12345678912345E300"));
        Assert.assertTrue(MyValidateTools.isDouble("-1.12345678912345E300"));
        Assert.assertTrue(MyValidateTools.isDouble("1.12345678912345E-300"));
        Assert.assertTrue(MyValidateTools.isDouble("-1.12345678912345E-300"));

        StringBuilder s = new StringBuilder("0.");
        s.append("0".repeat(300));
        s.append("1");
        double d = Double.parseDouble(s.toString());
        System.out.println(d);
        Assert.assertTrue(MyValidateTools.isDouble(s.toString()));

        Assert.assertFalse(MyValidateTools.isDouble(""));
        Assert.assertFalse(MyValidateTools.isDouble("four"));
        Assert.assertFalse(MyValidateTools.isDouble(null));
    }

}
