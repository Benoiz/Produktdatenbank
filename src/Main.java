import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final String FILE_NAME = "localDB.db";
    public static final String URL_STRING = "http://wwwlehre.dhbw-stuttgart.de/~unterstein/movieproject2019-2.db";

    public static void main(String[] args) throws IOException {

        //getting commandline args


        //get File
        Path dbFile = Paths.get(FILE_NAME);

        if(Files.notExists(dbFile)) {       //loads file from URL if there's no local file
            GetFile gf = new GetFile();
            gf.getFile();
        }
        //read from local file and import data
        FileToDataModel fdm = new FileToDataModel();
        ArrayList<Persons> PersonsList = fdm.getPersons();
        ArrayList<Products> ProductsList = fdm.getProducts();
        ArrayList<Companies> CompaniesList = fdm.getCompanies();
        ArrayList<Friends> FriendsList = fdm.getFriends();
        ArrayList<Buyers> BuyersList = fdm.getBuyers();
        ArrayList<ManuProdRelation> ManuProdList = fdm.getManufacturers();


        Results res = new Results();
        try {
            String request[] = args[0].split("=");              // processing commandline args
            String requestValue = request[1];

            if(request[0].equals("--personensuche")) { // argument is name
                res.getPersonbyName(PersonsList, requestValue);
            }
            if(request[0].equals("--produktsuche")) { // argument is name
                res.getProductbyName(ProductsList, requestValue);
            }
            if(request[0].equals("--produktnetzwerk")) { // argument is id
                //not yet
                System.out.println("not implemented yet");
            }
            if(request[0].equals("--firmennetzwerk")) { // argument is id
                //not yet
                System.out.println("not implemented yet");
            }
            else {
                System.out.println("could not find " + args[0]);
            }
        } catch (Exception ex) {
            System.out.println("no argument given?");
        }


    }
}
