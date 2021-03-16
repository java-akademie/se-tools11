
package ch.jmildner.tools11;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class MyBigDecimalToolsTest {

    boolean test = false;

    private void out(Object bd) {
        out(bd, test);
    }

    private void out(Object bd, boolean test) {
        if (test) System.out.println(bd);
    }

    @Test
    public void makeWithoutDecimals() {
        String valueAsString = "4711.12";
        BigDecimal bd = MyBigDecimalTools.make(valueAsString);
        Assert.assertEquals(valueAsString, bd.toString());
        out(bd);
    }

    @Test
    public void makeWithDecimals() {
        String valueAsString = "4711.1234";
        BigDecimal bd = MyBigDecimalTools.make(4, valueAsString);
        Assert.assertEquals(bd.toString(), valueAsString);
        out(bd);
    }

    @Test
    public void makeRandom1() {

        int numberBeforeDecimals = 31;
        int numberOfDecimals = 2;
        int expectedMin = 1 + 1 + numberOfDecimals;
        int expectedMax = numberBeforeDecimals + 1 + numberOfDecimals;

        for (int i = 1; i < 10; i++) {

            BigDecimal bd = MyBigDecimalTools.makeRandom();
            int actual = bd.toString().length();
            Assert.assertTrue(
                    String.format("Length of BigDecimal should be between %d and %d but is %d",
                            expectedMin, expectedMax, actual),
                    actual >= expectedMin
                            && actual <= expectedMax
            );
            out(MyBigDecimalTools.format(bd,88));
        }
    }

    @Test
    public void makeRandom2() {

        int numberBeforeDecimals = 112;
        int numberOfDecimals = 22;
        int expectedMin = 1 + 1 + numberOfDecimals;
        int expectedMax = numberBeforeDecimals + 1 + numberOfDecimals;

        for (int i = 1; i < 10; i++) {

            BigDecimal bd = MyBigDecimalTools.makeRandom(
                    numberBeforeDecimals, numberOfDecimals, false);
            int actual = bd.toString().length();
            Assert.assertTrue(
                    String.format("Length of BigDecimal should be between %d and %d but is %d",
                            expectedMin, expectedMax, actual),
                    actual >= expectedMin
                            && actual <= expectedMax
            );
            out(bd);
        }
    }

    @Test
    public void add1() {
        String aAsString = "5.25";
        String bAsString = "7.35";
        String ergAsString = "12.60";
        BigDecimal a = MyBigDecimalTools.make(aAsString);
        BigDecimal b = MyBigDecimalTools.make(bAsString);
        BigDecimal expected = MyBigDecimalTools.make(ergAsString);
        Assert.assertEquals(expected, MyBigDecimalTools.add(a, b));
        out(a);
        out(b);
        out(expected);
    }

    @Test
    public void add2() {
        String aAsString = "5.257";
        String bAsString = "7.354";
        String ergAsString = "12.611";
        BigDecimal a = MyBigDecimalTools.make(3, aAsString);
        BigDecimal b = MyBigDecimalTools.make(3, bAsString);
        BigDecimal expected = MyBigDecimalTools.make(3, ergAsString);
        Assert.assertEquals(expected, MyBigDecimalTools.add(3, a, b));
        out(a);
        out(b);
        out(expected);
    }

    @Test
    public void sub1() {
        String aAsString = "5";
        String bAsString = "6";
        String ergAsString = "-1";
        BigDecimal a = MyBigDecimalTools.make( aAsString);
        BigDecimal b = MyBigDecimalTools.make( bAsString);
        BigDecimal expected = MyBigDecimalTools.make( ergAsString);
        Assert.assertEquals(expected, MyBigDecimalTools.subtract( a, b));
        out(a);
        out(b);
        out(expected);
    }

    @Test
    public void sub2() {
        String aAsString = "5";
        String bAsString = "6";
        String ergAsString = "-1";
        BigDecimal a = MyBigDecimalTools.make(3, aAsString);
        BigDecimal b = MyBigDecimalTools.make(3, bAsString);
        BigDecimal expected = MyBigDecimalTools.make(3, ergAsString);
        Assert.assertEquals(expected, MyBigDecimalTools.subtract(3, a, b));
        out(a);
        out(b);
        out(MyBigDecimalTools.format(expected,22));
    }

    @Test
    public void multiply1() {
        String aAsString = "5";
        String ergAsString = "30";
        BigDecimal a = MyBigDecimalTools.make(aAsString);
        BigDecimal expected = MyBigDecimalTools.make(ergAsString);
        Assert.assertEquals(expected, MyBigDecimalTools.multiply( a, "6"));
        out(MyBigDecimalTools.format(expected,22));
    }
    @Test
    public void multiply2() {
        String aAsString = "5";
        String ergAsString = "30";
        BigDecimal a = MyBigDecimalTools.make(3,aAsString);
        BigDecimal expected = MyBigDecimalTools.make(3,ergAsString);
        Assert.assertEquals(expected, MyBigDecimalTools.multiply(3, a, "6"));
        out(MyBigDecimalTools.format(expected,22));
    }

    @Test
    public void multiply3() {
        String aAsString = "5000";
        String bAsString = "60";
        String ergAsString = "300000";
        BigDecimal a = MyBigDecimalTools.make(3, aAsString);
        BigDecimal b = MyBigDecimalTools.make(3, bAsString);
        BigDecimal expected = MyBigDecimalTools.make(3, ergAsString);
        Assert.assertEquals(expected, MyBigDecimalTools.multiply(3, a, b));
        out(expected);
        out(MyBigDecimalTools.format(expected,22,"#,##0.00000"));
    }

    @Test
    public void multiply4() {
        String aAsString = "5000";
        String bAsString = "2";
        String ergAsString = "10000";
        BigDecimal a = MyBigDecimalTools.make( aAsString);
        BigDecimal b = MyBigDecimalTools.make( bAsString);
        BigDecimal expected = MyBigDecimalTools.make( ergAsString);
        Assert.assertEquals(expected, MyBigDecimalTools.multiply( a, b));
        out(expected);
        out(MyBigDecimalTools.format(expected,22,"#,##0.00000"));
    }

    @Test
    public void multiply5() {
        String aAsString = "5000";
        String ergAsString = "25000";
        BigDecimal a = MyBigDecimalTools.make(3, aAsString);
        BigDecimal expected = MyBigDecimalTools.make(3, ergAsString);
        Assert.assertEquals(expected, MyBigDecimalTools.multiply(3, a, "5"));
        out(expected);
        out(MyBigDecimalTools.format(expected,22,"#,##0.00000"));
    }

    @Test
    public void divide1() {
        String aAsString = "144";
        String ergAsString = "12";
        BigDecimal a = MyBigDecimalTools.make( aAsString);
        BigDecimal expected = MyBigDecimalTools.make( ergAsString);
        Assert.assertEquals(expected, MyBigDecimalTools.divide( a, "12"));
        out(MyBigDecimalTools.format(expected,22,"#,##0.00000"));
    }

    @Test
    public void divide2() {
        String aAsString = "144";
        String ergAsString = "12";
        BigDecimal a = MyBigDecimalTools.make(3, aAsString);
        BigDecimal expected = MyBigDecimalTools.make(3, ergAsString);
        Assert.assertEquals(expected, MyBigDecimalTools.divide(3, a, "12"));
        out(MyBigDecimalTools.format(expected,22,"#,##0.00000"));
    }

    @Test
    public void divide3() {
        String aAsString = "1697625";
        String bAsString = "13500";
        String ergAsString = "125.75";
        BigDecimal a = MyBigDecimalTools.make( aAsString);
        BigDecimal b = MyBigDecimalTools.make( bAsString);
        BigDecimal expected = MyBigDecimalTools.make( ergAsString);
        Assert.assertEquals(expected, MyBigDecimalTools.divide( a, b));
        out(MyBigDecimalTools.format(expected,22,"#,##0.00000"));
    }

    @Test
    public void divide4() {
        String aAsString = "1697625";
        String bAsString = "13505";
        String ergAsString = "125.70344";
        BigDecimal a = MyBigDecimalTools.make(5, aAsString);
        BigDecimal b = MyBigDecimalTools.make( 5,bAsString);
        BigDecimal expected = MyBigDecimalTools.make( 5,ergAsString);
        Assert.assertEquals(expected, MyBigDecimalTools.divide( 5,a, b));
        out(MyBigDecimalTools.format(expected,22,"#,##0.00000"));
    }
    @Test
    public void format() {
        String aAsString = "1697625.756";
        String pattern = "#,##0.00";
        BigDecimal a = MyBigDecimalTools.make(3, aAsString);
        String expected = String.format("%,.2f",1_697_625.76);
        out(expected);
        Assert.assertEquals(expected,
                MyBigDecimalTools.format( a,expected.length(),pattern));
    }
}
