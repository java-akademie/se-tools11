
package ch.jmildner.tools11x;

import ch.jmildner.tools11.MyTools;
import ch.jmildner.tools11x.MyPrimeTools;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MyPrimeTools1Test {

    private final int MAX = 14;

    @Before
    public void init() {
        MyPrimeTools.initPrimes(MAX);
        MyPrimeTools.startPrime(10000);
    }

    @Test
    public void test1() {
        MyPrimeTools.initPrimes();
        MyPrimeTools.startPrime();
        Assert.assertEquals(101, MyPrimeTools.nextPrime(99));
        Assert.assertEquals(103, MyPrimeTools.nextPrime());
    }

    @Test
    public void test2() {
        Assert.assertEquals(7001, MyPrimeTools.nextPrime(7000));
    }

    @Test
    public void test3() {
        MyTools.h1("test3", 2);

        long nextPrime;

        for (int i = 1; i <= 10; i++) {
            nextPrime = MyPrimeTools.nextPrime();
            System.out.printf("nextPrime: %,d %n", nextPrime);
            System.out.printf("nextPrime: %d %n", nextPrime);
        }
    }

    @Test
    public void test4() {
        MyTools.h1("test4", 2);
        long nextPrime = (long) (1 * (Math.pow(10, MAX - 1)));

        for (int i = 1; i <= 50; i++) {
            nextPrime = MyPrimeTools.nextPrime(nextPrime);
            Assert.assertTrue(MyPrimeTools.isPrime1(nextPrime));
            nextPrime++;
        }
    }

    @Test
    public void test6() {
        MyTools.h1("test6", 2);

        MyPrimeTools.startPrime((long) Math.pow(10, MAX - 1));

        for (int i = 1; i <= 12; i++) {
            long nextPrime = MyPrimeTools.nextPrime();
            System.out.printf("nextPrime: %,d%n", nextPrime);
        }
    }
}
