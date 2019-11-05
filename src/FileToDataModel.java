import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileToDataModel {

    private static final String PERSONS_INI = "New_Entity: \"person_id\", \"person_name\", \"person_gender\"";
    private static final String PRODUCTS_INI = "New_Entity: \"product_id\",\"product_name\"";
    private static final String COMPANIES_INI = "New_Entity: \"company_id\",\"company_name\"";
    private static final String FRIENDS_INI = "New_Entity: \"person1_id\",\"person2_id\"";
    private static final String BUYERS_INI = "New_Entity: \"person_id\",\"product_id\"";
    private static final String MANUFACTURERS_PRODUCTS = "New_Entity: \"product_id\",\"company_id\"";


    String[] divideStr (String inputString) {

        inputString.replaceAll("\"", "");
        inputString.trim();
        String[] result = inputString.split(",");
        return result;
    }

    ArrayList<Persons> getPersons() throws IOException {
        BufferedReader read = initiateFileReader();
        String inputLine;
        Persons p = new Persons();
        ArrayList<Persons> PersonsList = new ArrayList<>();
        while ((inputLine = read.readLine()) != null) {
            if (inputLine.equals(PERSONS_INI)) {

                while (!(inputLine = read.readLine()).equals(PRODUCTS_INI)) {
                    String[] tokens = divideStr(inputLine);
                    p.id = tokens[0];
                    p.name = tokens[1];
                    p.gender = tokens[2];
                    PersonsList.add(p);
                }
            }
        }
        if (read != null)
            read.close();
        return PersonsList;
    }

    ArrayList<Products> getProducts() throws IOException {
        BufferedReader read = initiateFileReader();
        String inputLine;
        Products p = new Products();
        ArrayList<Products> ProductsList = new ArrayList<>();
        while ((inputLine = read.readLine()) != null) {
            if (inputLine.equals(PRODUCTS_INI)) {

                while (!(inputLine = read.readLine()).equals(COMPANIES_INI)) {
                    String[] tokens = divideStr(inputLine);
                    p.id = tokens[0];
                    p.name = tokens[1];
                    ProductsList.add(p);
                }
            }
        }
        if (read != null)
            read.close();

        return ProductsList;
    }

    ArrayList<Companies> getCompanies() throws IOException {
        BufferedReader read = initiateFileReader();
        String inputLine;
        Companies c = new Companies();
        ArrayList<Companies> CompaniesList = new ArrayList<>();
        while ((inputLine = read.readLine()) != null) {
            if (inputLine.equals(COMPANIES_INI)) {

                while( !(inputLine = read.readLine()).equals(FRIENDS_INI)) {
                    String[] tokens = divideStr(inputLine);
                    c.id = tokens[0];
                    c.name = tokens[1];
                    CompaniesList.add(c);
                }
            }
        }
        if (read != null)
            read.close();

        return CompaniesList;
    }

    ArrayList<Friends> getFriends() throws IOException {
        BufferedReader read = initiateFileReader();
        String inputLine;
        Friends f = new Friends();
        ArrayList<Friends> Friendslist = new ArrayList<>();
        while ((inputLine = read.readLine()) != null) {
            if (inputLine.equals(FRIENDS_INI)) {

                while (!(inputLine = read.readLine()).equals(BUYERS_INI)) {
                    String[] tokens = divideStr(inputLine);
                    f.id = tokens[0];
                    f.id2 = tokens[1];
                    Friendslist.add(f);
                }
            }
        }
        if (read != null)
            read.close();

        return Friendslist;
    }

    ArrayList<Buyers> getBuyers() throws IOException {
        BufferedReader read = initiateFileReader();
        String inputLine;
        Buyers b = new Buyers();
        ArrayList<Buyers> Buyerslist = new ArrayList<>();
        while ((inputLine = read.readLine()) != null) {
            if (inputLine.equals(BUYERS_INI)) {

                while (!(inputLine = read.readLine()).equals(MANUFACTURERS_PRODUCTS)) {
                    String[] tokens = divideStr(inputLine);
                    b.id = tokens[0];
                    b.productId = tokens[1];
                    Buyerslist.add(b);
                }
            }
        }
        if (read != null)
            read.close();

        return Buyerslist;
    }

    ArrayList<ManuProdRelation> getManufacturers() throws IOException {
        BufferedReader read = initiateFileReader();
        String inputLine;
        ManuProdRelation m = new ManuProdRelation();
        ArrayList<ManuProdRelation> ManuProdList = new ArrayList<>();
        while ((inputLine = read.readLine()) != null) {
            if (inputLine.equals(MANUFACTURERS_PRODUCTS)) {

                String[] tokens = divideStr(inputLine);
                m.companyName = tokens[0];
                m.productId = tokens[1];
                ManuProdList.add(m);
            }
        }
        if (read != null)
            read.close();

        return ManuProdList;
    }
    BufferedReader initiateFileReader() {
        BufferedReader in = null;
        try {
            //URL fileSource = new URL(Main.FILE_NAME);
            in = new BufferedReader(new FileReader(Main.FILE_NAME));

            return in;
        } catch (Exception ex) {
            System.out.println("There probably was an I/O problem "); // bIg oOf
            System.out.println(ex.getMessage());
            return in;
        }
    }

}
