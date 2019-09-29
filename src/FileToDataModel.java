import java.io.*;
import java.net.URL;

public class FileToDataModel {

    private static final String PERSONS_INI = "New_Entity: \"person_id\", \"person_name\", \"person_gender\"";
    private static final String PRODUCTS_INI = "New_Entity: \"product_id\",\"product_name\"";
    private static final String COMPANIES_INI = "New_Entity: \"company_id\",\"company_name\"";
    private static final String FRIENDS_INI = "New_Entity: \"person1_id\",\"person2_id\"";
    private static final String BUYERS_INI = "New_Entity: \"person_id\",\"product_id\"";
    private static final String MANUFACTURERS_PRODUCTS = "New_Entity: \"product_id\",\"company_id\"";

    void fileToModel() throws IOException {

        BufferedReader in = null;
        try {
            URL fileSource = new URL(Main.URL_STRING);
            in = new BufferedReader(new InputStreamReader(fileSource.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {

                /* Import persons data set
                * the loop goes through every line in specified block and assigns value to object property*/
                if (inputLine.equals(PERSONS_INI)) {
                    while ((inputLine = in.readLine()) != PRODUCTS_INI) {
                        String[] tokens = divideStr(inputLine);

                    }
                }
                // Import products data set
                if (inputLine.equals(PRODUCTS_INI)) {
                    while ((inputLine = in.readLine()) != COMPANIES_INI) {

                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("There probably was an I/O problem "); // bIg oOf
            System.out.println(ex.getMessage());
        } finally {
            if (in != null) in.close();
        }
    }

    String[] divideStr (String inputString) {

        inputString.replace("\"", "");
        inputString.trim();
        String[] result = inputString.split(",");
        return result;
    }
}
