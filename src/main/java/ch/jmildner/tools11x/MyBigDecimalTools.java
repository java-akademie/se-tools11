
package ch.jmildner.tools11x;

import ch.jmildner.tools11.MyTools;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * The final class <code>MyBigDecimalTools</code> has some useful
 * <code>static</code> methods for the use of BigDecimals.
 *
 * @author Johann Mildner, Basel
 */
public final class MyBigDecimalTools {

    /**
     * The constructor is private so that
     * <code>MyBigDecimalTools</code> can't be be instantiated.
     */
    private MyBigDecimalTools() {
    }

    public static BigDecimal make(String valueAsSting) {
        return make(2, valueAsSting);
    }

    public static BigDecimal make(int numberOfDecimals, String valueAsSting) {
        try {
            BigDecimal bd = new BigDecimal(valueAsSting);
            bd = bd.setScale(numberOfDecimals, RoundingMode.HALF_UP);
            return bd;
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    public static BigDecimal makeRandom() {
        return makeRandom(31, 2, false);
    }

    public static BigDecimal makeRandom(
            int numberBeforeDecimals,
            int numberOfDecimals,
            boolean fix) {

        int numberBeforeDecimalPoint = 1;

        if (numberBeforeDecimals > 1) {
            numberBeforeDecimalPoint = fix ?
                    numberBeforeDecimals :
                    MyTools.getRandom(1, numberBeforeDecimals);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= numberBeforeDecimalPoint; i++) {
            sb.append((int) (Math.random() * 10));
        }
        sb.append(".");
        for (int i = 1; i <= numberOfDecimals; i++) {
            sb.append((int) (Math.random() * 10));
        }

        BigDecimal bd = new BigDecimal(sb.toString());

        if (bd.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ONE;
        } else {
            return bd;
        }
    }

    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        return add(2, a, b);
    }

    public static BigDecimal add(int numberOfDecimals, BigDecimal a, BigDecimal b) {
        BigDecimal bd = new BigDecimal("0");
        bd = bd.add(a);
        bd = bd.add(b);
        bd = bd.setScale(numberOfDecimals, RoundingMode.HALF_UP);
        return bd;
    }

    public static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return subtract(2, a, b);
    }

    public static BigDecimal subtract(int numberOfDecimals, BigDecimal a, BigDecimal b) {
        BigDecimal bd = new BigDecimal("0");
        bd = bd.add(a);
        bd = bd.subtract(b);
        bd = bd.setScale(numberOfDecimals, RoundingMode.HALF_UP);
        return bd;
    }

    public static BigDecimal multiply(BigDecimal a, String b) {
        return multiply(2, a, make(2, b));
    }

    public static BigDecimal multiply(int numberOfDecimals, BigDecimal a, String b) {
        return multiply(numberOfDecimals, a, make(numberOfDecimals, b));
    }

    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return multiply(2, a, b);
    }

    public static BigDecimal multiply(int numberOfDecimals, BigDecimal a, BigDecimal b) {
        BigDecimal bd = a.multiply(b);
        bd = bd.setScale(numberOfDecimals, RoundingMode.HALF_UP);
        return bd;
    }

    public static BigDecimal divide(BigDecimal a, String b) {
        return divide(2, a, make(2, b));
    }

    public static BigDecimal divide(int numberOfDecimals, BigDecimal a, String b) {
        return divide(numberOfDecimals, a, make(2, b));
    }

    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        return divide(2, a, b);
    }

    public static BigDecimal divide(int numberOfDecimals, BigDecimal a, BigDecimal b) {
        return a.divide(b, numberOfDecimals,
                RoundingMode.HALF_UP);
    }

    public static String format(BigDecimal n, int length) {
        return format(n, length, "#,##0.00");
    }

    public static String format(BigDecimal n, int length, String pattern) {
        DecimalFormat df = new DecimalFormat(pattern);
        String s = df.format(n);
        s = spaces(length - s.length()) + s;
        return s;
    }

    private static String spaces(int number) {
        return " ".repeat(Math.max(0, number));
    }
}

