package subsetsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        double[] arr = {15, 5, 3, 1, 3};
        ArrayList<Double> list = new ArrayList<>();
        for (double num : arr) {
            list.add(num);
        }
        double target = 14;
        ArrayList<Double> result = SubsetSum.findSubset(list, target);
        for (double fl : result) {
            System.out.println(fl);
        }
    }
}
