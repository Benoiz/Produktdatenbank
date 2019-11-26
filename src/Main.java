import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


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
        fdm.getItems();

        Results res = new Results();
        try {
            String[] request = args[0].split("=");              // processing commandline args
            String requestValue = request[1];

            switch (request[0]) {
                case "--personensuche":  // argument is name
                    res.getPersonByName(FileToDataModel.PersonsList, requestValue);
                    break;
                case "--produktsuche":  // argument is name
                    res.getProductByName(FileToDataModel.ProductsList, requestValue);
                    break;
                case "--produktnetzwerk":  // argument is id
                    res.printProductNetwork(FileToDataModel.ProductsList, res.getProductNetwork(requestValue));
                    break;
                case "--firmennetzwerk":  // argument is id
                    res.printCompanyNetwork(res.getCompanyNetwork(requestValue));
                    break;
                default:
                    System.out.println("could not find " + args[0]);
                    break;
            }
        } catch (Exception ex) {
            System.out.println("no argument given? " + ex);
        }
    }
}
