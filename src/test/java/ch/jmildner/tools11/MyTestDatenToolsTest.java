
package ch.jmildner.tools11;

import org.junit.Test;

public class MyTestDatenToolsTest {

    @Test
    public void testFirstName() {
        MyTools.h1("testFirstName", 2);
        for (int i = 1; i <= 10; i++)
            System.out.println(MyTestDatenTools.getFirstName());
        System.out.println();
        for (int i = 1; i <= 10; i++)
            System.out.println(MyTestDatenTools.getFirstName(0));
        System.out.println();
        for (int i = 1; i <= 10; i++)
            System.out.println(MyTestDatenTools.getFirstName(1));
    }

    @Test
    public void testLastName() {
        MyTools.h1("testLastName", 2);
        for (int i = 1; i <= 10; i++)
            System.out.println(MyTestDatenTools.getLastName());
    }

    @Test
    public void testName() {
        MyTools.h1("testName", 2);
        for (int i = 1; i <= 10; i++)
            System.out.println(MyTestDatenTools.getName());
    }

    @Test
    public void testZipCode() {
        MyTools.h1("testZipCode", 2);
        for (int i = 1; i <= 10; i++)
            System.out.println(MyTestDatenTools.ZipCode());
    }

    @Test
    public void testCity() {
        MyTools.h1("testCity", 2);
        for (int i = 1; i <= 10; i++)
            System.out.println(MyTestDatenTools.getCity());
    }

    @Test
    public void testStreet() {
        MyTools.h1("testStreet", 2);
        for (int i = 1; i <= 10; i++)
            System.out.println(MyTestDatenTools.getStreet());
    }

    @Test
    public void testNumberOfHouse() {
        MyTools.h1("testNumberOfHouse", 2);
        for (int i = 1; i <= 10; i++)
            System.out.println(MyTestDatenTools.getHouseNumber());
    }

    @Test
    public void testAddress() {
        MyTools.h1("testAddress", 2);
        for (int i = 1; i <= 10; i++)
            System.out.println(MyTestDatenTools.getAddress());
    }

    @Test
    public void testEMail() {
        MyTools.h1("testEMail", 2);
        for (int i = 1; i <= 10; i++)
            System.out.println(MyTestDatenTools.getEMail());
    }

    @Test
    public void testPassword() {
        MyTools.h1("testPassword", 2);
        for (int i = 1; i <= 10; i++)
            System.out.println(MyTestDatenTools.getPassword());
    }

    @Test
    public void testRandomDateOfBirth() throws Exception {
        MyTools.h1("testRandomDateOfBirth", 2);
        for (int i = 1; i <= 10; i++)
            System.out.println(MyTestDatenTools.getRandomDateOfBirth(25));
    }

}
