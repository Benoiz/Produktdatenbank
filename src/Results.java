import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Results {

    void getPersonByName(@NotNull ArrayList<Person> list, String name) {
        AtomicInteger count = new AtomicInteger();
        count.set(0);
        list.forEach(p -> {
            int result = p.name.indexOf(name);
            if (result != -1) {
                System.out.println(p.name);
                count.getAndIncrement();
            }

            /* else System.out.println("could not find " + name); */
        });
        System.out.println(count + " results found");
    }

    void getProductByName(@NotNull ArrayList<Product> list, String name) {
        int count = 0;
        for (Product p : list) {
            int result = p.name.indexOf(name);
            if (result != -1) System.out.println(p.name);
            count++;
            /* else System.out.println("could not find " + name); */
        }
        System.out.println(count + " results found");
    }


    void formatString(@NotNull String arg) {
        arg.replace("\"", "");
        arg.trim();
    }
}

