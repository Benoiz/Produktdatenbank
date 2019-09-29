import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class Main {

    public static final String FILE_NAME = "localDB.db";
    public static final String URL_STRING = "http://wwwlehre.dhbw-stuttgart.de/~unterstein/movieproject2019-2.db";

    public static void main(String[] args) throws IOException {
    //get File
        Path dbFile = Paths.get(FILE_NAME);

        if(Files.notExists(dbFile)) {       //loads file from URL if there's no local file
            GetFile gf = new GetFile();
            gf.getFile();
        }
        else {
            //read from local file
        }
    }
}
