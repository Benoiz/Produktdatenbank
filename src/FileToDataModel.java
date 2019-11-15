import java.io.*;
import java.net.URL;
import java.util.*;

public class FileToDataModel {

    private static final String PERSONS_INI = "New_Entity: \"person_id\", \"person_name\", \"person_gender\"";
    private static final String PRODUCTS_INI = "New_Entity: \"product_id\",\"product_name\"";
    private static final String COMPANIES_INI = "New_Entity: \"company_id\",\"company_name\"";
    private static final String FRIENDS_INI = "New_Entity: \"person1_id\",\"person2_id\"";
    private static final String BUYERS_INI = "New_Entity: \"person_id\",\"product_id\"";
    private static final String MANUFACTURERS_PRODUCTS = "New_Entity: \"product_id\",\"company_id\"";

    public static ArrayList<Person> PersonsList = new ArrayList<>();
    public static ArrayList<Product> ProductsList = new ArrayList<>();
    public static ArrayList<Company> CompaniesList = new ArrayList<>();
    public static ArrayList<Friends> FriendsList = new ArrayList<>();
    public static ArrayList<Buyers> BuyersList = new ArrayList<>();
    public static ArrayList<ManuProdRelation> ManuProdList = new ArrayList<>();

    String[] divideStr (String inputString) {

        inputString.replaceAll("\"", "");
        inputString.trim();
        String[] result = inputString.split(",");
        return result;
    }

    int getItems() throws IOException {
        BufferedReader read = initiateFileReader();
        String inputLine;
        Person p = new Person();
        Product pr = new Product();
        Company c = new Company();
        Friends f = new Friends();
        Buyers b = new Buyers();
        ManuProdRelation mpr = new ManuProdRelation();

        Set<String> initiators = new HashSet<>();
        initiators.add(PERSONS_INI);
        initiators.add(PRODUCTS_INI);
        initiators.add(COMPANIES_INI);
        initiators.add(FRIENDS_INI);
        initiators.add(BUYERS_INI);
        initiators.add(MANUFACTURERS_PRODUCTS);

        int switchCount = 0;

        //ArrayList<Persons> PersonsList = new ArrayList<>();
        while ((inputLine = read.readLine()) != null) {

            if (initiators.contains(inputLine)) {

                switchCount++;
            }
            String[] tokens = divideStr(inputLine);
            switch (switchCount) {
                case 1: // processing persons
                    p = new Person();
                    p.id = tokens[0];
                    p.name = tokens[1];
                    p.gender = tokens[2];
                    PersonsList.add(p);
                    break;
                case 2:
                    pr = new Product();
                    pr.id = tokens[0];
                    pr.name = tokens[1];
                    ProductsList.add(pr);
                    break;
                case 3:
                    c = new Company();
                    c.id = tokens[0];
                    c.name = tokens[1];
                    CompaniesList.add(c);
                    break;
                case 4:
                    f = new Friends();
                    f.id = tokens[0];
                    f.id2 = tokens[1];
                    FriendsList.add(f);
                    break;
                case 5:
                    b = new Buyers();
                    b.id = tokens[0];
                    b.productId = tokens[1];
                    BuyersList.add(b);
                    break;
                case 6:
                    mpr = new ManuProdRelation();
                    mpr.productId = tokens[0];
                    mpr.companyId = tokens[1];
                    ManuProdList.add(mpr);
                    break;
                }
        }

        if (read != null)
            read.close();
        return 0;
    }

    BufferedReader initiateFileReader() {
        BufferedReader in = null;
        try {

            in = new BufferedReader(new FileReader(Main.FILE_NAME));

            return in;
        } catch (Exception ex) {
            System.out.println("There probably was an I/O problem "); // bIg oOf
            System.out.println(ex.getMessage());
            return in;
        }
    }

}
