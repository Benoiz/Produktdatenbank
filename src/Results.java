import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Results {

    void getPersonbyName(ArrayList<Persons> list, String name) {
        for (Persons p : list) {
            p.name.equals(name);
        }
    }

    void getProductbyName(ArrayList<Products> list, String name) {
        for (Products p : list) {
            p.name.equals(name);
        }
    }


    void formatString(String arg) {
        arg.replace("\"", "");
        arg.trim();
    }
}

