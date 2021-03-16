
package ch.jmildner.tools11;


/**
 * The final class <code>MyValidateTools</code> has some useful
 * <code>static</code> methods for, well validating numbers.
 *
 * @author Johann Mildner, Basel
 */
public final class MyValidateTools
{
    /**
     * the constructor is private so that
     * <code>MyValidateTools</code> can't be instantiated
     */
    private MyValidateTools()
    {
    }

    /**
     * @param string the string to be checked.
     * @return true=ok, false=nok
     */
    public static boolean isEmpty(String string)
    {
        if (string == null)
        {
            return true;
        }

        return string.trim().equals("");
    }

    /**
     * @param string the string to be checked.
     * @return true=ok, false=nok
     */
    public static boolean isInteger(String string)
    {
        return isNumeric(string, "int");
    }

    /**
     * @param string the string to be checked.
     * @return true=ok, false=nok
     */
    public static boolean isDouble(String string)
    {
        return isNumeric(string, "double");
    }

    /**
     * @param string the string to be checked.
     * @return true=ok, false=nok
     */
    public static boolean isLong(String string)
    {
        return isNumeric(string, "long");
    }

    /**
     * @param string the string to be checked.
     * @return true=ok, false=nok
     */
    public static boolean isFloat(String string)
    {
        return isNumeric(string, "float");
    }

    private static boolean isNumeric(String string, String type)
    {
        if (isEmpty(string))
        {
            return false;
        }

        try
        {
            switch (type)
            {
                case "long":
                    Long.parseLong(string);
                    break;
                case "double":
                    double d = Double.parseDouble(string);
                    if (Double.isInfinite(d))
                    {
                        return false;
                    }
                    break;
                case "float":
                    float f = Float.parseFloat(string);
                    if (Float.isInfinite(f))
                    {
                        return false;
                    }
                    break;
                case "int":
                default:
                    Integer.parseInt(string);
                    break;
            }
        }
        catch (NumberFormatException e)
        {
            return false;
        }

        return true;
    }

}
