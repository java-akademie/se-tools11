package ch.jmildner.tools11;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyPrimeTools2Test {
    @Before
    public void init() {
        System.out.println("init start");
        MyPrimeTools.initPrimes(14);
        System.out.println("init stop");
    }

    @Test
    public void aPrime1() {
        int value = 101;
        Assert.assertTrue(String.format("%d should be prime", value),
                MyPrimeTools.isPrime1(value));
        System.out.println(MyPrimeTools.nextPrime());
    }

    @Test
    public void aPrime2() {
        int value = 103;
        Assert.assertTrue(String.format("%d should be prime", value),
                MyPrimeTools.isPrime2(value));
    }

    @Test
    public void aPrime3() {
        int value = 107;
        Assert.assertTrue(String.format("%d should be prime", value),
                MyPrimeTools.isPrime3(value));
    }

    @Test
    public void noPrime1() {
        int value = 102;
        Assert.assertFalse(String.format("%d should not be prime", value),
                MyPrimeTools.isPrime1(value));
    }

    @Test
    public void noPrime2() {
        int value = 102;
        Assert.assertFalse(String.format("%d should not be prime",
                value), MyPrimeTools.isPrime2(value));
    }


    @Test
    public void primeMassTest1() {
        primeMassTest(1, 10);
        primeMassTest(2, 10);
        primeMassTest(3, 10);
        System.out.println();

        primeMassTest(1, 100);
        primeMassTest(2, 100);
        primeMassTest(3, 100);
        System.out.println();

        primeMassTest(1, 1_000);
        primeMassTest(2, 1_000);
        primeMassTest(3, 1_000);
        System.out.println();

        primeMassTest(1, 10_000);
        primeMassTest(2, 10_000);
        primeMassTest(3, 10_000);
        System.out.println();

        primeMassTest(1, 100_000);
        primeMassTest(2, 100_000);
        primeMassTest(3, 100_000);
        System.out.println();

        primeMassTest(1, 1_000_000);
        primeMassTest(2, 1_000_000);
        primeMassTest(3, 1_000_000);
        System.out.println();

        primeMassTest(1, 10_000_000);
//      primeMassTest(2, 10_000_000);
        primeMassTest(3, 10_000_000);
        System.out.println();
    }

    @Test
    public void primeMassTest2() {
        primeMassTest(1, 10_000_000, 10_100_000);
        primeMassTest(2, 10_000_000, 10_100_000);
        primeMassTest(3, 10_000_000, 10_100_000);
        System.out.println();

        primeMassTest(1, 100_000_000, 100_100_000);
        primeMassTest(2, 100_000_000, 100_100_000);
        primeMassTest(3, 100_000_000, 100_100_000);
        System.out.println();

        primeMassTest(1, 1_000_000_000, 1_000_100_000);
        primeMassTest(2, 1_000_000_000, 1_000_100_000);
        primeMassTest(3, 1_000_000_000, 1_000_100_000);
        System.out.println();
    }

    @Test
    public void primeMassTest3() {
        primeMassTest(1, 1_000_000_000, 0);
//    primeMassTest(2, 1_000_000_000, 0);
        primeMassTest(3, 1_000_000_000, 0);
        System.out.println();

        primeMassTest(1, 2_000_000_000, 0);
//    primeMassTest(2, 1_000_000_000, 0);
        primeMassTest(3, 2_000_000_000, 0);
        System.out.println();

        primeMassTest(1, 3_000_000_000L, 0);
//    primeMassTest(2, 3_000_000_000L, 0);
        primeMassTest(3, 3_000_000_000L, 0);
        System.out.println();

        primeMassTest(1, 4_000_000_000L, 0);
//    primeMassTest(2, 4_000_000_000L, 0);
        primeMassTest(3, 4_000_000_000L, 0);
        System.out.println();

        primeMassTest(1, 5_000_000_000L, 0);
//    primeMassTest(2, 5_000_000_000L, 0);
        primeMassTest(3, 5_000_000_000L, 0);
        System.out.println();

        primeMassTest(1, 6_000_000_000L, 0);
//    primeMassTest(2, 6_000_000_000L, 0);
        primeMassTest(3, 6_000_000_000L, 0);
        System.out.println();

        primeMassTest(1, 10_000_000_000L, 0);
//    primeMassTest(2, 10_000_000_000L, 0);
        primeMassTest(3, 10_000_000_000L, 0);
        System.out.println();

        primeMassTest(1, 100_000_000_000L, 0);
//    primeMassTest(2, 100_000_000_000L, 0);
        primeMassTest(3, 100_000_000_000L, 0);
        System.out.println();

        primeMassTest(1, 1_000_000_000_000L, 0);
//    primeMassTest(2, 1_000_000_000_000L, 0);
        primeMassTest(3, 1_000_000_000_000L, 0);
        System.out.println();

        primeMassTest(1, 10_000_000_000_000L, 0);
//    primeMassTest(2, 10_000_000_000_000L, 0);
        primeMassTest(3, 10_000_000_000_000L, 0);
        System.out.println();
    }

    private void primeMassTest(final int kz, final long stop) {
        primeMassTest(kz, 1, stop);
    }

    private void primeMassTest(final int kz, final long start, long stop) {
        if (stop < start) stop = start + 1000;

        long startZeit = System.nanoTime();

        int value = 0;

        for (long i = start; i <= stop; i++) {
            if (kz == 1) {
                if (MyPrimeTools.isPrime1(i)) value++;
            } else if (kz == 2) {
                if (MyPrimeTools.isPrime2(i)) value++;
            } else if (kz == 3) {
                if (MyPrimeTools.isPrime3(i)) value++;
            }
        }

        long stopZeit = System.nanoTime();

        System.out.printf("test: %d - between %,d and %,d there are %,d primes - runtime: %f seconds %n",
                kz, start, stop, value, (stopZeit - startZeit) / 1_000_000_000.0);
    }

}
