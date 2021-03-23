
package ch.jmildner.tools11x;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

/**
 * The final class <code>MyPrimeTools</code> has some useful
 * <code>static</code> methods for the use of primes.
 *
 * @author Johann Mildner, Basel
 */
public final class MyPrimeTools {

    private static List<Integer> primesBuffer;
    private static long maxNumberAllowed;
    private static Long nextPrime;

    /**
     * the constructor is private so that
     * <code>MyPrimeTools</code> can't be instantiated
     */
    private MyPrimeTools() {
    }

    /**
     * Fills the buffer with primes from 1 to 10 to the power of.
     */
    public static void initPrimes() {
        if (primesBuffer != null)
            return;

        initPrimes(14);
    }

    /**
     * Fills the buffer with primes from 1 to 10 to the power or exponent.
     *
     * @param exponent 10 to the power of
     */
    public static void initPrimes(int exponent) {

        if (primesBuffer != null)
            return;

        maxNumberAllowed = (long) Math.pow(10, exponent);

        long startTime = System.nanoTime();
        System.out.println("init primes runs ...");
        primesBuffer = new ArrayList<>();
        primesBuffer.add(2);
        exponent = (int) Math.sqrt(maxNumberAllowed);
        for (int i = 3; i <= exponent; i += 2) {
            if (isPrime3(i)) primesBuffer.add(i);
        }
        long stopTime = System.nanoTime();

        long lastPrime = primesBuffer.get(primesBuffer.size() - 1);
        long maxNumberToCheck = lastPrime * lastPrime;

        System.out.printf("number of primes in the range of (1 to %,d) is %,d "
                        + " - runtime %f seconds %n" +
                        "last prime is %,d  max number to check %,d %n",
                exponent,
                primesBuffer.size(), (stopTime - startTime) / 1_000_000_000.0,
                lastPrime, maxNumberToCheck);

        maxNumberAllowed = (long) Math.pow(primesBuffer.get(primesBuffer.size() - 1), 2);
    }

    /**
     * Checks whether a number is prime.
     * <p>
     * This variant is faster than <code>isPrime1()</code>.
     * <p>
     * A buffer is required.
     * <p>
     * When the buffer is less than 50'000, this variant is only useful for
     * primes up to 2'000'000'000.
     * <p>
     * The larger the buffer, the greater can be the number to be checked.
     *
     * @param number number to check
     * @return true when number is prime, otherwise false
     */
    public static boolean isPrime3(long number) {

        if (number > maxNumberAllowed) {
            System.out.printf(
                    "number to check %,d " +
                            "is greater than maxNumberAllowed %,d %n" +
                            "don't know whether it's prime or not %n",
                    number, maxNumberAllowed);
            return false;
        }

        if (number < 2) return false;

        long sqrtNumber = (long) Math.sqrt(number);

        if (primesBuffer == null) initPrimes();

        for (long t : primesBuffer) {
            if (t > sqrtNumber) break;
            if (number % t == 0) return false;
        }

        return true;
    }

    /**
     * Checks if <code>number</code> is prime.
     * <p>
     * Faster than <code>isPrime2()</code>.
     * <p>
     * Between 1 and 10'000'000 there are more than 600'000 primes.
     * Time difference isPrime1():isPrime(2) 17:27 seconds.
     * <p>
     * For high numbers this variant ist better.
     *
     * @param number value to check
     * @return true when prime, otherwise false
     */
    public static boolean isPrime1(long number) {
        if (number < 2) return false;

        long sqrtNumber = 1 + (long) Math.sqrt(number);

        for (int t = 2; t < sqrtNumber; t++) {
            if (number % t == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if <code>number</code> is prime.
     * <p>
     * Probably the fastest variant.
     * <p>
     * Between 1 and 10'000'000 there are more than 600'000 primes.
     * Time difference isPrime1():isPrime(2) 17:27 seconds.
     * <p>
     * For high numbers this variant ist better.
     *
     * @param number value to check
     * @return true when prime, otherwise false
     */
    public static boolean isPrime4(long number) {
        return new BigInteger(number + "").isProbablePrime(64);
    }

    /**
     * Checks if <code>number</code> is prime.
     * <p>
     * Much slower than <code>isPrime1()</code>.
     * <p>
     * Between 1 and 10'000'000 there are more than 600'000 primes.
     * Time difference isPrime1():isPrime(2) 17:27 seconds.
     *
     * @param number value to check
     * @return true when prime, otherwise false
     */
    public static boolean isPrime2(long number) {
        if (number < 2) return false;

        long stop = 1 + (long) Math.sqrt(number);
        return LongStream.range(2, stop).noneMatch(t -> number % t == 0);
    }

    /**
     * Sets nextPrime to 1L.
     */
    public static void startPrime() {
        startPrime(1L);
    }

    /**
     * Sets nextPrime to 1L or start - 1.
     *
     * @param start value to start with
     */
    public static void startPrime(long start) {
        if (start < 2) {
            nextPrime = 1L;
        } else {
            nextPrime = start - 1L;
        }
    }

    /**
     * Calculates the next prime and returns it.
     *
     * @return the next prime
     */
    public static long nextPrime() {
        if (nextPrime == null) {
            startPrime(1);
        }

        nextPrime++;

        while (!isPrime3(nextPrime)) {
            nextPrime++;
        }
        return nextPrime;
    }

    /**
     * Calculates the prime that follows numberToStartPrime and returns it.
     *
     * @param numberToStartPrime value where to startPrime
     * @return the next prime
     */
    public static long nextPrime(final long numberToStartPrime) {
        startPrime(numberToStartPrime);

        nextPrime++;

        while (!isPrime3(nextPrime)) {
            nextPrime++;
        }

        return nextPrime;
    }

}
