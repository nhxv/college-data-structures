package subsetsum;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Read text files from resources
 *
 * @author Foothill College, Vinh Ngo
 */
public class GroceriesFileReader {
    protected ArrayList<Double> readFile(String filePath) {
        BufferedReader reader = null;
        ArrayList<Double> price = new ArrayList<>();
        String line;
        // data format: item,price
        try {
            reader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.US_ASCII);
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                price.add(Double.parseDouble(data[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
        return price;
    }
}
