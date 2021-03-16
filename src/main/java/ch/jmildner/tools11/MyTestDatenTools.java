package ch.jmildner.tools11;

import java.util.Date;

/**
 * The final class <code>MyTestDatenTools</code> has some useful
 * <code>static</code> methods.
 *
 * @author Johann Mildner, Basel
 */
public final class MyTestDatenTools {
    private static final String[] firstNameFemale =
            {"brigita", "frieda", "adele", "beate", "elisabeth", "linda", "karin",
                    "heidi", "maria", "ute", "martina", "margarete", "ludowika",
                    "marianne", "susanne", "gerlinde", "gabriele", "eva", "ullrike"};

    private static final String[] firstNameMale =
            {"fritz", "urs", "oli", "albert", "mario", "hans-peter", "heinz", "peter",
                    "max", "oliver", "josef", "johann", "friedrich", "georg", "gerhard",
                    "robert", "johannes", "bruno", "moritz"};

    private static final String[] lastName =
            {"mueller", "schuster", "meier", "allmen", "von arx", "friedrichs", "mauler",
                    "gruber", "bamberger", "holterer", "german", "lugner", "strache",
                    "havlik", "nigl", "meier", "maier", "strobl", "huber", "einstein",
                    "vogel", "fink", "sperling"};

    private static final String[] city =
            {"basel", "genf", "zuerich", "wien", "berlin", "paris", "brugg", "bruck",
                    "wiel", "graz", "klagenfurt", "krems", "muenchen", "koeln", "berlin"};

    private static final String[] street =
            {"hauptstrasse", "hauptplatz", "gablenzgasse", "lehenmattstrasse",
                    "gueterstrasse", "gerlgasse", "rheingasse", "wurmstrasse", "blumenrain",
                    "zuercherstrasse", "im gehoeft", "rosenackerstrasse"};

    private static final String[] email = {"mm@xxx.aa", "nn@yyy.bb"};

    private static final String[] password =
            {"geheim", "soginet", "1234", "tiger", "london",
                    "dulcinea", "rosinante", "password"};

    private static final String[] firstName;

    static {
        firstName = new String[firstNameFemale.length + firstNameMale.length];

        int firstNameIndex = 0;

        for (String n : firstNameFemale) {
            firstName[firstNameIndex++] = n;
        }
        for (String n : firstNameMale) {
            firstName[firstNameIndex++] = n;
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getName());
        System.out.println(getAddress());
        System.out.println(getEMail());
        System.out.println(getPassword());
        System.out.println(getRandomDateOfBirth(35));
    }

    /**
     * the constructor is private so that
     * <code>TestDatenTool</code> can't be be instantiated
     */
    private MyTestDatenTools() {
    }

    /**
     * @return name (firstName + " " + lastName)
     */
    public static String getName() {
        return getFirstName() + " " + getLastName();
    }

    /**
     * @return address
     */
    public static String getAddress() {
        return ZipCode() + " " + getCity() + " " + getStreet() + " " + " " + getHouseNumber();
    }

    /**
     * @param sex - optional 1=female, 2=male
     * @return firstName
     */
    public static String getFirstName(int... sex) {
        if (sex.length == 1) {
            switch (sex[0]) {
                case 1:
                    return firstNameFemale[(int) (Math.random() * firstNameFemale.length)];
                case 2:
                    return firstNameMale[(int) (Math.random() * firstNameMale.length)];
            }
        }
        return firstName[(int) (Math.random() * firstName.length)];
    }

    /**
     * @return lastName
     */
    public static String getLastName() {
        return lastName[(int) (Math.random() * lastName.length)];
    }

    /**
     * @return plz (1000-8000)
     */
    public static int ZipCode() {
        return (int) (1000 + Math.random() * 8000);
    }

    /**
     * @return ort
     */
    public static String getCity() {
        return city[(int) (Math.random() * city.length)];
    }

    /**
     * @return email
     */
    public static String getEMail() {
        return email[(int) (Math.random() * email.length)];
    }

    /**
     * @return password
     */
    public static String getPassword() {
        return password[(int) (Math.random() * password.length)];
    }

    /**
     * @return street
     */
    public static String getStreet() {
        return street[(int) (Math.random() * street.length)];
    }

    /**
     * @return number of house(1 - 64)
     */
    public static int getHouseNumber() {
        return (int) (1 + Math.random() * 64);
    }

    /**
     * Return a date of birth.
     *
     * @param peakAge - peak age for Gaussian bell curve.
     * @return date of birth
     * @throws Exception shouldn't occur
     */
    public static Date getRandomDateOfBirth(int peakAge) throws Exception {
        int age = MyDateTimeTools.getRandomAge(peakAge);
        int year = MyDateTimeTools.getYearOfBirth(age);
        return MyDateTimeTools.makeRandomDate(year);
    }
}
