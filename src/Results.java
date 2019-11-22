import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;

class Results {

    ArrayList<String> getPersonByName(@NotNull ArrayList<Person> list, String personName) {
        ArrayList<String> PersonsList = new ArrayList<>();
        for (Person p : list) {
            boolean match = containsIgnoreCase(p.name, personName);
            if (match) PersonsList.add(p.name);
        }

        return PersonsList;
    }

    ArrayList<String> getProductByName(@NotNull ArrayList<Product> list, String productName) {
        ArrayList<String> ProductList = new ArrayList<>();
        for (Product p : list) {
            boolean match = containsIgnoreCase(p.name, productName);
            if (match) ProductList.add(p.name);
        }
        return ProductList;
    }

    private void getProductById(@NotNull Collection<Product> list, String productId) {
        for (Product p : list) {
            boolean match = containsIgnoreCase(p.id, productId);
            if (match) System.out.println(p.name);
        }
    }

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

    ArrayList<String> getCompaniesById(String companyId) {
        ArrayList<String> list = new ArrayList<>();
        for (Company c : FileToDataModel.CompaniesList) {
            boolean match = containsIgnoreCase(c.id, companyId);
            if (match) {
                list.add(c.name);
            }
        }
        return list;
    }

    ArrayList<String> getCompanyIdByProductId(String productId) {
        ArrayList<String> list = new ArrayList<>();
        for (ManuProdRelation mpr : FileToDataModel.ManuProdList) {
            boolean match = containsIgnoreCase(mpr.productId, productId);
            if (match) {
                list.add(mpr.companyId);
            }
        }
        return list;
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

    void printFromCollection(Collection<String> collection) {
        for (String s : collection) {
            getProductById(FileToDataModel.ProductsList, s);
        }
    }

    TreeSet<String> getCompanyNetwork(String id) {
        TreeSet<String> resultSet = new TreeSet<>();
        TreeSet<String> tempSet = getProductNetwork(id);
        for (String s : tempSet) {

        }

        return resultSet;
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

