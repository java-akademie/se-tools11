package ch.jmildner.tools;

import java.io.*;
import java.net.Socket;

public class NetTools
{
    public static BufferedReader getBufferedReader(Socket socket)
            throws Exception
    {
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        return br;
    }


    public static PrintWriter getPrintWriter(Socket socket)
            throws Exception
    {
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        PrintWriter pw = new PrintWriter(osw);
        return pw;
    }
}
