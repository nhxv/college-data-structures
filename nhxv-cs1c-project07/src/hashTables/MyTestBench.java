package hashTables;

import cs1c.MillionSongDataSubset;
import cs1c.SongEntry;
import cs1c.TimeConverter;

/**
 * A test class that compares the search time of sequential search and hashtable search
 * @author Vinh Ngo
 */
public class MyTestBench {
    public static void main(String[] args) {
        SongEntry[] allSongs = readSongsFromDataFile("resources/test.json");
        int id = 10000;
        TableGenerator g = new TableGenerator();
        FHhashQPwFind<Integer, SongCompInt>  tableOfSongIDs = g.populateIDtable(allSongs);

        long startTime = System.nanoTime();

        // sequential find id
        for (SongEntry song : allSongs) {
            if (song.getID() == id) {
                break;
            }
        }
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Sequential find time: " + TimeConverter.convertTimeToString(estimatedTime));

        // hashtable find id
        startTime = System.nanoTime();
        SongCompInt songCompInt = tableOfSongIDs.find(id);
        estimatedTime = System.nanoTime() - startTime;
        System.out.println("Hashtable find time: " + TimeConverter.convertTimeToString(estimatedTime));
    }

    /**
     * Uses MillionSongDataSubset to read all songs from data file.
     * @param jsonFile		A JSON file to parse
     * @return				The array of SongEntry objects
     */
    private static SongEntry[] readSongsFromDataFile(String jsonFile) {
        // parses the JSON input file
        MillionSongDataSubset msd = new MillionSongDataSubset(jsonFile);
        // retrieves the parsed objects
        SongEntry [] allSongs = msd.getArrayOfSongs();
        // displays the number of songs read from the input file
        System.out.printf("Total number of songs read %d \n", allSongs.length);
        return allSongs;
    }
}
