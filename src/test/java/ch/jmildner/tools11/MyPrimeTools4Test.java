package ch.jmildner.tools11;

import org.junit.Before;
import org.junit.Test;

public class MyPrimeTools4Test {

    @Before
    public void init() {
        MyPrimeTools.initPrimes(14);
    }

    @Test
    public void primeMassTest1() {
        for (int i = 9; i <= 11; i++) {
            long stop = (long) (Math.pow(10, i));
            long start = stop - 1_000_000;

            primeMassTest(1, start, stop);
        }
    }

    @Test
    public void primeMassTest3() {
        for (int i = 9; i <= 11; i++) {
            long stop = (long) (Math.pow(10, i));
            long start = stop - 1_000_000;

            primeMassTest(3, start, stop);
        }
    }

    @Test
    public void primeMassTest4() {
        for (int i = 12; i <= 14; i++) {
            long stop = (long) (Math.pow(10, i));
            long start = stop - 1_000_000;
            primeMassTest(4, start, stop);
        }
    }

    private void primeMassTest(final int kz, final long start, long stop) {

        if (stop < start)
            stop = start + 1000;

        long startZeit = System.nanoTime();

        int counter = 0;

        for (long i = start; i <= stop; i++) {
            if (kz == 1) {
                if (MyPrimeTools.isPrime3(i)) counter++;
            } else if (kz == 3) {
                if (MyPrimeTools.isPrime3(i)) counter++;
            } else if (kz == 4) {
                if (MyPrimeTools.isPrime4(i)) counter++;
            }
        }

        long stopZeit = System.nanoTime();

        System.out.printf("test: %d - between %,20d and %,20d there are %,d primes - runtime: %f seconds %n",
                kz, start, stop, counter, (stopZeit - startZeit) / 1_000_000_000.0);
    }

}
