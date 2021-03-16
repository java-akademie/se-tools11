
package ch.jmildner.tools11;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class MyDateTimeToolsTest {

       @Test
    public void makeTime() throws Exception {
        Time t = MyDateTimeTools.makeTime(12, 25, 30);
        assertNotNull(t);
    }

    @Test
    public void makeRandomTime() throws Exception {
        Time t = MyDateTimeTools.makeRandomTime();
        assertNotNull(t);
    }

    @Test
    public void getCurrentTime() {
        Time t1 = MyDateTimeTools.getCurrentTime();
        Time t2 = MyDateTimeTools.getCurrentTime();
        assertTrue(t1.getTime() <= t2.getTime());
    }

    @Test
    public void makeRandomTimestamp1() {
        Timestamp ts = MyDateTimeTools.makeRandomTimestamp();
        assertNotNull(ts);
    }

    @Test
    public void makeRandomTimestamp2() throws Exception {
        Timestamp ts = MyDateTimeTools.makeRandomTimestamp(2021);
        assertNotNull(ts);
    }

    @Test
    public void makeRandomTimestamp3() throws Exception {
        Timestamp ts = MyDateTimeTools.makeRandomTimestamp(2017, 2020);
        assertNotNull(ts);
    }

    @Test
    public void makeTimestamp() throws Exception {
        Timestamp ts = MyDateTimeTools.makeTimestamp(2017, 11, 22);
        assertNotNull(ts);
    }

    @Test
    public void makeRandomDate2() throws Exception {
        Date d = MyDateTimeTools.makeRandomDate(1972);
        assertNotNull(d);
    }

    @Test
    public void makeRandomDate1() throws Exception {
        Date d = MyDateTimeTools.makeRandomDate();
        assertNotNull(d);
    }

    @Test
    public void makeRandomDateOfBirth() throws Exception {
        Date d = MyDateTimeTools.makeRandomDateOfBirth(72);
        assertNotNull(d);
    }

    @Test
    public void getCurrentTimestamp() {
        Timestamp t1 = MyDateTimeTools.getCurrentTimestamp();
        Timestamp t2 = MyDateTimeTools.getCurrentTimestamp();
        assertTrue(t1.getTime() <= t2.getTime());

    }

    @Test
    public void getRandomAge() {
        boolean ok = true;
        for (int i = 1; i <= 200; i++) {
            if (!getRandomAge(i)) {
                ok = false;
                break;
            }
        }
        assertTrue(ok);
    }

    private boolean getRandomAge(final int peak) {
        final int x = peak / 4 + 1;

        int min = 1000;
        int max = -1000;

        for (int i = 1; i <= 100000; i++) {
            int age = MyDateTimeTools.getRandomAge(peak);
            if (age < min) min = age;
            if (age > max) max = age;

        }

        return (peak - min <= x) && (max - peak <= x);
    }
}
