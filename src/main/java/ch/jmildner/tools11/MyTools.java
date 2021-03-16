package ch.jmildner.tools11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * The final class <code>MyTools</code> has some useful
 * <code>static</code> methods.
 *
 * @author Johann Mildner, Basel
 */
public final class MyTools {

    private final static String VERSION = "MyTools.version: 1.11.3";

    public static void main(String[] args) {

        MyTools.sleep(10);
        MyTools.pause();
        MyTools.pause("press enter ");
        MyTools.h1("h1");
        MyTools.h2(MyTools.getVersion());
        MyTools.comment("xxx", 2);
        MyTools.comment("xxx");
        MyTools.pause();
        MyTools.sleep(1000);
        System.out.println(MyTools.getRandomString(30));
        System.out.println(MyTools.getRandom());
        System.out.println(MyTools.getRandom(1, 100));
        System.out.println(MyTools.getRandomLong());
        System.out.println(MyTools.getRandomPrime());
        System.out.println(MyTools.getRandomPrime(10, 12));
        System.out.println(MyTools.getString("string                   > "));
        System.out.println(MyTools.getString("string empty allowed     > ", true));
        System.out.println(MyTools.getString("string empty not allowed > ", false));
        System.out.println(MyTools.getDouble("double   > "));
        System.out.println(MyTools.getDoubleGN("doubleGN > "));
        System.out.println(MyTools.getDoubleNN("doubleNN > "));
        System.out.println(MyTools.getInteger("int        > "));
        System.out.println(MyTools.getInteger("int (1,10) > ", 1, 10));
    }

    /**
     * the constructor is private so that
     * <code>MyTools</code> can't be instantiated
     */
    private MyTools() {
    }

    /**
     * Returns the Version.
     * Sample:
     * <code>String v = MyTools.getVersion();</code>
     *
     * @return the Version
     */
    public static String getVersion() {
        return VERSION;
    }

    /**
     * Reads a <code>String</code> from console, trims and returns it.
     * <p>
     * Sample:
     * <code>String str = MyTools.getString("enter a String &gt; ");</code>
     *
     * @param prompt the Prompt
     * @return stringValue
     */
    public static String getString(String prompt) {
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print(prompt);
        System.out.flush();

        try {
            return br.readLine().trim();
        } catch (IOException ioException) {
            return "IOException in br.readLine() ....";
        }

    }

    /**
     * Reads a <code>String</code> from console, trims and returns it.
     * When emptyAllowed=false, the String mustn't be empty.
     * <p>
     * Sample:
     * <code>String s = MyTools.getString("enter a String &gt; ",false);</code>
     *
     * @param prompt       the Prompt
     * @param emptyAllowed true oder false
     * @return stringValue
     */
    public static String getString(String prompt, boolean emptyAllowed) {
        while (true) {
            String stringValue = getString(prompt);

            if (emptyAllowed) {
                System.out.flush();
                return stringValue;
            } else {
                if (!stringValue.equals("")) {
                    System.out.flush();
                    return stringValue;
                } else {
                    System.out.print("empty String not allowed .... ");
                    System.out.flush();
                }
            }
        }
    }

    /**
     * Reads a <code>double</code> from console and returns it.
     * <p>
     * Sample:
     * <code>int i = MyTools.getDouble("enter a Double &gt; ");</code>
     *
     * @param prompt the Prompt
     * @return doubleValue
     */
    public static double getDouble(String prompt) {
        while (true) {
            try {
                System.out.flush();
                return Double.parseDouble(getString(prompt));
            } catch (NumberFormatException e) {
                System.out.print("not a double .... ");
                System.out.flush();
            }
        }
    }

    /**
     * Reads a <code>double</code> greater Zero from console and returns it.
     * <p>
     * Sample:
     * <code>int i = MyTools.getDoubleGN("enter a Double &gt; ");</code>
     *
     * @param prompt the Prompt
     * @return doubleValue
     */
    public static double getDoubleGN(String prompt) {
        while (true) {
            double doubleValue = getDouble(prompt);

            if (doubleValue > 0.0) {
                System.out.flush();
                return doubleValue;
            } else {
                System.out.print("must be greater 0 ... ");
                System.out.flush();
            }
        }
    }

    /**
     * Reads a not zero <code>double</code> from console and returns it.
     * <p>
     * Sample:
     * <code>int i = MyTools.getDoubleNN("enter Double &gt; ");</code>
     *
     * @param prompt the Prompt
     * @return doubleValue
     */
    public static double getDoubleNN(String prompt) {
        while (true) {
            double doubleValue = getDouble(prompt);

            if (doubleValue != 0.0) {
                System.out.flush();
                return doubleValue;
            } else {
                System.out.print("must not be 0 ... ");
                System.out.flush();
            }
        }
    }

    /**
     * Reads an <code>int</code> from console and returns it.
     * <p>
     * Sample:
     * <code>int i = MyTools.getInteger("enter an Integer &gt; ");</code>
     *
     * @param prompt the Prompt
     * @return intValue
     */
    public static int getInteger(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(getString(prompt));
            } catch (NumberFormatException e) {
                System.out.print("not an integer .... ");
                System.out.flush();
            }
        }
    }

    /**
     * Reads an <code>int</code> from console and returns it.
     * The <code>int</code> is between <code>min</code> and <code>max</code>.
     * <p>
     * Sample:
     * <code>int i = MyTools.getInteger("enter an Integer (1-5) &gt; ",1,5);</code>
     *
     * @param prompt the Prompt
     * @param min    minValue
     * @param max    maxValue
     * @return intValue
     */
    public static int getInteger(String prompt, int min, int max) {
        while (true) {
            int intValue = getInteger(prompt);

            if (intValue >= min && intValue <= max) {
                System.out.flush();
                return intValue;
            } else {
                System.out.print("out of range .... ");
                System.out.flush();
            }
        }
    }

    /**
     * Returns a random <code>String</code>
     * of the length <code>length</code>
     * from the domain <code>0-9, a-z, A-Z</code>.
     * <p>
     * Sample: <code>String s = MyTools.getRandomString(32);</code>
     *
     * @param length the Length
     * @return randomString
     */
    public static String getRandomString(int length) {
        final char[] chars = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                'i', 'l', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C',
                'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
                'Y', 'Z'};

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= length; i++) {
            sb.append(chars[(int) (Math.random() * chars.length)]);
        }

        return sb.toString();
    }

    /**
     * Returns a random <code>int</code>
     * from the domain <code>1 - 0x7fffffff</code>.
     * <p>
     * Sample: <code>int i = MyTools.getRandom();</code>
     *
     * @return randomInt
     */
    public static int getRandom() {
        return getRandom(1, Integer.MAX_VALUE);
    }

    /**
     * Returns a random <code>long</code>
     * from the domain <code>1L - 0x7fffffffffffffffL</code>.
     * <p>
     * Sample: <code>long i = MyTools.getRandomLong();</code>
     *
     * @return randomLong
     */
    public static long getRandomLong() {
        return getRandom(1L, Long.MAX_VALUE);
    }

    /**
     * Returns a random <code>int</code>
     * from the domain <code>min - max</code>. <br>
     * <p>
     * min must be greater 0. <br>
     * max must be greater min. <br>
     * <p>
     * Sample:
     * <code>int i = MyTools.getRandom(1,46);</code>
     *
     * @param min - minValue
     * @param max - maxValue
     * @return randomInt
     */
    public static int getRandom(int min, int max) {
        if (min < 1 || max <= min) {
            min = 1;
            max = Integer.MAX_VALUE;
        }

        return (int) (min + (Math.random() * (max - min + 1)));
    }

    /**
     * Returns a random <code>long</code>
     * from the domain <code>min - max</code>. <br>
     * <p>
     * min must be greater 0.<br>
     * max must be greater min.<br>
     * <p>
     * Sample:
     * <code>int i = MyTools.getRandom(1,46);</code>
     *
     * @param min - minValue
     * @param max - maxValue
     * @return randomLong
     */
    public static long getRandom(long min, long max) {
        if (min < 1 || max < min) {
            min = 1;
            max = Long.MAX_VALUE;
        }

        return (long) (min + (Math.random() * (max - min + 1)));
    }

    /**
     * Returns a random <code>long</code> which is Prim
     * from the domain <code>1L - 0xfffffffffffffffL</code>.
     * <p>
     * Sample: <code>int i = MyTools.getRandomPrime();</code>
     *
     * @return randomLongPrim
     */
    public static long getRandomPrime() {
        return getRandomPrime(1L, Long.MAX_VALUE);
    }

    /**
     * Returns a random <code>long</code> which is prime
     * from the domain <code>min - max</code>.
     * <p>
     * min must be greater 0.<br>
     * max must be greater min.<br>
     * <p>
     * Sample: <code>int i = MyTools.getRandomPrime(1,100000000);</code>
     * <p>
     * The function starts with a random value between min and max.
     * Then it adds 1 as long as the value isn't prime.
     * It can happen, that between min and max there is no prime.
     * Then it returns the next higher value.
     * <p>
     * <code>MyTools.getRandomPrime(21,28);</code> returns either 23 or 29.
     *
     * @param min - minValue
     * @param max - maxValue
     * @return randomLongPrime
     */
    public static long getRandomPrime(long min, long max) {
        if (min < 1 || max <= min) {
            min = 1L;
            max = Long.MAX_VALUE;
        }

        long randomValue = getRandom(min, max);

        while (true) {
            if (new BigInteger(randomValue + "").isProbablePrime(999999999)) {
                return randomValue;
            }

            randomValue++;
        }
    }

    /**
     * @param text Heading Text
     */
    public static void h1(String text) {
        h1(text, 1);
    }

    /**
     * @param text Heading Text
     */
    public static void h2(String text) {
        h2(text, 1);
    }

    /**
     * @param text     Heading Text
     * @param newLines number of newLines before
     */
    public static void h1(String text, int newLines) {
        text = text.trim();
        String fs = "/";
        String bs = "\\";

        hHeading(text, newLines, fs, bs);
    }


    /**
     * @param text     Heading Text
     * @param newLines number of newLines before
     */
    public static void h2(String text, int newLines) {
        text = text.trim();
        String fs = "+";
        String bs = "+";

        hHeading(text, newLines, fs, bs);
    }

    private static void hHeading(String text, int newLines, String fs, String bs) {
        String nl = "\n";

        StringBuilder sb = new StringBuilder();

        sb.append(nl.repeat(Math.max(0, newLines)));

        StringBuilder dashes = new StringBuilder();

        dashes.append("-".repeat(text.length()));

        sb.append(fs).append("-").append(dashes).append("-").append(bs).append(nl);
        sb.append("! ").append(text).append(" !").append(nl);
        sb.append(bs).append("-").append(dashes).append("-").append(fs).append(nl);

        System.out.print(sb.toString());
    }

    /**
     * Writes <code>***</code> and waits until user hits Enter.
     * <p>
     * Sample: <code>MyTools.pause();</code>
     */
    public static void pause() {
        pause("\n*** ");
    }

    /**
     * Writes a <code>String</code> and waits until user hit Enter.
     * <p>
     * Sample: <code>MyTools.pause("go ahead with Enter ... ");</code>
     *
     * @param str outputString
     */
    public static void pause(String str) {
        getString(str);
        System.out.println();
        System.out.flush();
    }

    /**
     * Let the thread sleep for some milliseconds.
     * <p>
     * Sample:
     * <code>MyTools.sleep(1000); // Thread sleeps a second</code>
     *
     * @param ms number of milliseconds the thread should sleep
     */
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException in Thread.sleep()\n" + e);
            System.out.flush();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * @param text Comment Text
     */
    public static void comment(String text) {
        comment(text, 1);
    }

    /**
     * @param text     Comment Text
     * @param newLines number of newLines before
     */
    public static void comment(String text, int newLines) {
        System.out.println(
                "\n".repeat(Math.max(0, newLines))
                        + text);
    }

}
