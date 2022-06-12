import java.util.*;

public class Main {
    public static void main(String[] args) {
        double[] array = {15, 5, 3, 1, 3};
        double target = 14;
        ArrayList<Double> list = new ArrayList<>();
        for (double num : array) {
            list.add(num);
        }
        findSubset(list, target);
    }

    private static void findSubset(ArrayList<Double> numList, double target) {
        // setup collection with empty subset
        ArrayList<ArrayList<Double>> collection = new ArrayList<>();
        collection.add(new ArrayList<>());
        for (double number : numList) {
            ArrayList<ArrayList<Double>> extraCollection = new ArrayList<>();
            for (ArrayList<Double> list : collection) {
                ArrayList<Double> newList = (ArrayList<Double>) list.clone();
                newList.add(number);
                extraCollection.add(newList);
            }
            collection.addAll(extraCollection);
        }
        System.out.println(collection);
    }
}
