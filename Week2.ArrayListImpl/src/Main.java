import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // somewhere in main
        ArrayList<String> names = new ArrayList<String>();

        // somehow we ended up inserting valid and invalid names...
        names.add("alice");
        names.add("bob");
        names.add("");
        names.add("");
        names.add("dan");
        names.add("george");
        names.add("");
        names.add("cindy");

        System.out.println(names);

        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equals("")) {
                names.remove(i); // second empty string at index 3 will become index 2 after removal
            }
        }
    }
}
