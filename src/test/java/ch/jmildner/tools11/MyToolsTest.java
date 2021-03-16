package ch.jmildner.tools11;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import org.junit.Test;

public class MyToolsTest {


    @Test
    public void testTopics() {
        MyTools.h1("topic1", 1);
        MyTools.h2("topic2");
    }

    @Test
    public void testRandoms1() {
        {
            String s = MyTools.getRandomString(5);
            assertEquals(5, s.length());
        }

        {
            long d = MyTools.getRandomPrime(23L, 28L);
            assertTrue(d == 23 || d == 29);
        }

        {
            long d = MyTools.getRandomPrime();
            assertTrue(d > 1);
        }

        {
            long d = MyTools.getRandomLong();
            assertTrue(d > 1);
        }

        {
            long d = MyTools.getRandom(3L, 3L);
            assertEquals(3, d);
        }

        {
            int d = MyTools.getRandom();
            System.out.println(d);
        }
    }
}
