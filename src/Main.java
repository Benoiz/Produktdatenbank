import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {

    public static final String FILE_NAME = "localDB.db";
    public static final String URL_STRING = "http://wwwlehre.dhbw-stuttgart.de/~unterstein/movieproject2019-2.db";

    public static void main(String[] args) throws IOException {

        //get File
        Path dbFile = Paths.get(FILE_NAME);

        if (Files.notExists(dbFile)) {       //loads file from URL if there's no local file
            GetFile gf = new GetFile();
            gf.getFile();
        }
        //read from local file and import data
        FileToDataModel fdm = new FileToDataModel();
        int output = fdm.getItems();

        Results res = new Results();
        try {
            String[] request = args[0].split("=");              // processing commandline args
            String requestValue = request[1];

            if (request[0].equals("--personensuche")) { // argument is name
                ArrayList<String> result = res.getPersonByName(FileToDataModel.PersonsList, requestValue);
            } else if (request[0].equals("--produktsuche")) { // argument is name
                res.getProductByName(FileToDataModel.ProductsList, requestValue);
            } else if (request[0].equals("--produktnetzwerk")) { // argument is id
                res.printFromCollection(res.getProductNetwork(requestValue));
            } else if (request[0].equals("--firmennetzwerk")) { // argument is id
                //not yet
            } else {
                System.out.println("could not find " + args[0]);
            }
        } catch (Exception ex) {
            System.out.println("no argument given? " + ex);
        }
    }
}
