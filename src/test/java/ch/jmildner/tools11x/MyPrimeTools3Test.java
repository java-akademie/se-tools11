package ch.jmildner.tools11x;

import ch.jmildner.tools11x.MyPrimeTools;
import org.junit.Before;
import org.junit.Test;

public class MyPrimeTools3Test {

    @Before
    public void init() {
        MyPrimeTools.initPrimes(12);
    }

    @Test
    public void primeMassTest1() {
        primeMassTest(1_000_000, 0);
        primeMassTest(10_000_000, 0);
    }

    @Test
    public void primeMassTest2() {
        primeMassTest(10);
        primeMassTest(100);
        primeMassTest(1_000);
        primeMassTest(10_000);
        primeMassTest(100_000);
        primeMassTest(1_000_000);
        primeMassTest(10_000_000);
        primeMassTest(100_000_000);
        primeMassTest(999_999_000_000L, 0);
    }

    private void primeMassTest(final long stop) {
        primeMassTest(1, stop);
    }

    private void primeMassTest(final long start, long stop) {
        if (stop < start) stop = start + 1000;

        long startZeit = System.nanoTime();

        int counter = 0;

        for (long i = start; i <= stop; i++) {
            if (MyPrimeTools.isPrime3(i)) counter++;
        }

        long stopZeit = System.nanoTime();

        System.out.printf("between %,d and %,d there are %,d primes - runtime: %f seconds %n",
                start, stop, counter, (stopZeit - startZeit) / 1_000_000_000.0);
    }

}
