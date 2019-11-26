import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

class Results {

    void getPersonByName(@NotNull ArrayList<Person> list, String personName) {
        for (Person p : list) {
            boolean match = containsIgnoreCase(p.name, personName);
            if (match) System.out.println(p.name);
        }
    }

    void getProductByName(@NotNull ArrayList<Product> list, String productName) {
        for (Product p : list) {
            boolean match = containsIgnoreCase(p.name, productName);
            if (match) System.out.println(p.name);
        }
    }

    void printProductNetwork(@NotNull Collection<Product> list, @NotNull TreeSet<String> products) {
        for (String s : products) {
            for (Product p : list) {
                boolean match = containsIgnoreCase(p.id, s);
                if (match) System.out.println(p.name);
            }
        }
    }

    @NotNull
    private ArrayList<String> getFriendsById(String personId) {
        ArrayList<String> list = new ArrayList<>();
        for (Friends f : FileToDataModel.FriendsList) {
            boolean match = containsIgnoreCase(f.id, personId);
            if (match) {
                list.add(f.id2);
            }
        }

        return list;
    }

    @NotNull
    private ArrayList<String> getBoughtProductsById(String personId) { // a persons id
        ArrayList<String> list = new ArrayList<>();
        for (Buyers b : FileToDataModel.BuyersList) {
            boolean match = containsIgnoreCase(b.id, personId);
            if (match) {
                list.add(b.productId);
            }
        }
        return list;
    }

    private void getCompanyById(String companyId) {
        for (Company c : FileToDataModel.CompaniesList) {
            boolean match = containsIgnoreCase(c.id, companyId);
            if (match) {
                System.out.println(c.name);
            }
        }
    }

    private String getCompanyIdByProductId(String productId) {
        String result = null;
        for (ManuProdRelation mpr : FileToDataModel.ManuProdList) {
            boolean match = containsIgnoreCase(mpr.productId, productId);
            if (match) {
                result = mpr.companyId;
            }
        }
        return result;
    }

    TreeSet<String> getProductNetwork(String personId) {
        TreeSet<String> ts = new TreeSet<>(); // TreeSet because no duplicates allowed and needed order
        ArrayList<String> ownerProducts = getBoughtProductsById(personId); // get Products of our first personId
        ArrayList<String> friendsOfId = getFriendsById(personId); //get friends of that person
        for (String s : friendsOfId) {     //iterate through each friend and search for their products in possession
            ArrayList<String> friendsProducts = getBoughtProductsById(s);
            ts.addAll(friendsProducts);
        }
        ts.removeAll(ownerProducts);
        /*for (String prods : ts) {
            getProductById(FileToDataModel.ProductsList, prods);
        } */
        return ts;
    }

    TreeSet<String> getCompanyNetwork(String id) {
        ArrayList<String> tempList = new ArrayList<>();
        TreeSet<String> resultSet = new TreeSet<>();
        TreeSet<String> tempSet = getProductNetwork(id);
        for (String s : tempSet) {
            tempList.add(getCompanyIdByProductId(s));
        }
        resultSet.addAll(tempList);

        return resultSet;
    }

    void printCompanyNetwork(@NotNull TreeSet<String> companySet) {
        for (String elem : companySet) {
            getCompanyById(elem);
        }
    }
    // generic function which hast to be implemented yet
    /* <T, E> ArrayList<T> sampleGetSomething(@NotNull ArrayList<E> list, Object member, Object searchVar) {
        ArrayList<T> resultList = new ArrayList<>();
        for (E elem : list) {
            boolean result = containsIgnoreCase(member.toString(), searchVar.toString());
            if (result) {
                resultList.add((T)member);
            }
        }
        return resultList;
    } */

    private boolean containsIgnoreCase(@NotNull String str, @NotNull String subString) {
        return str.toLowerCase().contains(subString.toLowerCase());
    }
}

