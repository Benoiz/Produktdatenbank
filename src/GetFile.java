import org.omg.CORBA.Environment;

import java.io.*;
import java.net.URL;

/* This class is responsible for downloading the file from the URL and saving it to the hard drive */

public class GetFile {

    void getFile() throws IOException {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            URL fileSource = new URL(Main.URL_STRING);
            in = new BufferedReader(new InputStreamReader(fileSource.openStream()));
            out = new PrintWriter(new FileWriter(Main.FILE_NAME));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (Exception ex) {
            System.out.println("There probably was a problem when reading or writing a file");
            System.out.println(ex.getMessage());
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}


