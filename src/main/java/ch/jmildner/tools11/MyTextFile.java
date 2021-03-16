
package ch.jmildner.tools11;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * The class <code>MyTextFile</code> is for reading and writing
 * of text data.
 *
 * <pre>
 *   ---------------------------------------------------------------
 *   Sample:
 *   ---------------------------------------------------------------
 *
 *   try
 *   {
 *     MyTextFile tf = new MyTextFile(&quot;addresses.txt&quot;, 'o');
 *     tf.printLine(&quot;line1&quot;);
 *     tf.printLine(&quot;line2&quot;);
 *     tf.printLine(&quot;line3&quot;);
 *     tf.close();
 *   }
 *   catch (Exception e)
 *   {
 *   }
 *
 *   try
 *   {
 *     MyTextFile tf = new MyTextFile(&quot;addresses.txt&quot;, 'i');
 *     String s = tf.readLine();
 *     while(s!=null)
 *     {
 *       System.out.println(s);
 *       s = tf.readLine();
 *     }
 *   }
 *   catch (Exception e)
 *   {
 *   }
 *
 *   ---------------------------------------------------------------
 * </pre>
 *
 * @author Johann Mildner
 */
public class MyTextFile implements Closeable {
    private String fileName;
    private char mode;
    private BufferedReader br;
    private PrintWriter pw;

    public static void main(String[] args) throws FileNotFoundException {
        MyTextFile out = new MyTextFile("text.txt", 'o');
        out.printLine("test");
        out.close();

        MyTextFile inp = new MyTextFile("text.txt", 'i');
        String s = inp.readLine();
        while (s != null) {
            System.out.println(s);
            s = inp.readLine();
        }
        inp.close();
    }

    /**
     * The Constructor creates the class <code>MyTextFile</code>
     * for reading and writing text files.
     *
     * @param fileName addresses.txt
     * @param mode     i=Input, o=Output
     * @throws FileNotFoundException when input and file not found
     */
    public MyTextFile(String fileName, char mode) throws FileNotFoundException {
        this.fileName = fileName;
        this.mode = mode;

        switch (mode) {
            case 'I':
            case 'i':
                openInput();
                break;
            case 'O':
            case 'o':
                openOutput();
                break;

            default:
                break;
        }
    }

    /**
     * closes the file.
     */
    @Override
    public void close() {
        try {
            switch (mode) {
                case 'I':
                case 'i':
                    br.close();
                    break;

                case 'O':
                case 'o':
                    pw.close();
                    break;

                default:
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        fileName = null;
        br = null;
        pw = null;
        mode = 0;
    }

    /**
     * Creates a BufferedReader.
     *
     * @throws FileNotFoundException File not found
     */
    private void openInput() throws FileNotFoundException {
        InputStream is = new FileInputStream(fileName);
        InputStreamReader isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
    }

    /**
     * Creates a PrintWriter.
     *
     * @throws FileNotFoundException file not found
     */
    private void openOutput() throws FileNotFoundException {
        OutputStream os = new FileOutputStream(fileName);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        pw = new PrintWriter(osw);
    }

    /**
     * Writes a line into the output file.
     *
     * @param line line to write
     */
    public void printLine(String line) {
        if (mode == 'i' || mode == 'I') {
            throw (new RuntimeException("write isn't allowed when mode= 'i'"));
        }

        pw.println(line);
        pw.flush();
    }

    /**
     * Returns a line from the input file.
     *
     * @return aLine
     */
    public String readLine() {
        if (mode == 'o' || mode == 'O') {
            throw (new RuntimeException("read isn't allowed when mode= 'o'"));
        }

        String line = null;

        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }

        return line;
    }
}
