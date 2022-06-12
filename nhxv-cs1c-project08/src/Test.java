import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Testing quick sort runtime by changing recursion limit and array size
 * @author Foothill College, Vinh Ngo
 */
public class Test {
    public static void main(String[] args) {
        long arraySize = 20000;
        ArrayList<Data> dataArrayList = new ArrayList<>(9000);
        for (int i = 0; i < 20; i++) { // 20 array sizes test
            // run each array size 3 times
            for (int j = 0; j < 3; j++) {
                Integer[] numbers = Arrays
                        .stream(new Random().ints(0, 10000001).limit(arraySize).toArray())
                        .boxed()
                        .toArray(Integer[]::new);
                for (int step = 2; step < 301; step += 2) {
                    FHsort.setRecursionLimit(step);
                    long startTime = System.nanoTime();
                    FHsort.quickSort(numbers, 0, numbers.length - 1);
                    long estimatedTime = System.nanoTime() - startTime;
                    dataArrayList.add(new Data(step, arraySize, estimatedTime));
                }
            }
            arraySize += 500000;
        }
        writeFile(dataArrayList);
    }

    /**
     * Write data to csv file, following the format: Array size,Recursion limit,Time
     * @param dataArrayList
     */
    private static void writeFile(ArrayList<Data> dataArrayList) {
        try {
            FileWriter writer = new FileWriter("resources/data.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write("Array size,Recursion limit,Time");
            bufferedWriter.newLine();
            for (Data data : dataArrayList) {
                bufferedWriter.write(data.getArraySizes() + "," + data.getRecursionLimit() + "," + data.getTimes());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
