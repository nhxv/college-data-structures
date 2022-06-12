package lazyTrees;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import cs1c.MillionSongDataSubset;
import cs1c.SongEntry;

/**
 * An object of type FoothillTunesStore creates an object of type MillionSongDataSubset,
 * which in turn parses a JSON data set with a given file path. The parsed data set
 * is stored in the Lazy Search Tree of SongEntry objects.
 * @author Foothill College, Vinh Ngo
 */
public class FoothillTunesStore 
{	
	/**
	 * @param args	Not used.
	 */
	public static void main(String[] args) 
	{
		// NOTE: Make sure to use *relative* path instead of specifying the entire path. 
		//       Otherwise, your program will result in run time errors when the instructor
		//       tests your implementation.
		final String FILEPATH = "resources/music_genre_subset.json";
		final String TESTFILE = "resources/tunes.txt";

		// parses the JSON input file
		MillionSongDataSubset msd = new MillionSongDataSubset(FILEPATH);
		
		// retrieves the parsed objects
		SongEntry [] allSongs = msd.getArrayOfSongs();
		ArrayList<SongEntry> songList = new ArrayList<SongEntry>(Arrays.asList(allSongs));

		// create BST that store songs
		LazySearchTree<SongEntry> tunes = new LazySearchTree<>();
		PrintObject<SongEntry> printObject = new PrintObject<>();
		// displays the number of songs read from the input file
		System.out.printf("Welcome! We have over %d in FoothillTunes store! \n", songList.size());

		// read tunes
		int lineNum = 1;
		BufferedReader reader = null;
		String line;
		// data format: add/remove songTitle
		try {
			reader = Files.newBufferedReader(Paths.get(TESTFILE), StandardCharsets.US_ASCII);
			while ((line = reader.readLine()) != null) {
				System.out.println("\n--- TUNES ---");
				System.out.println("At line " + lineNum + ": " + line);
				String[] data = line.split("#");
				SongEntry song = findSongByTitle(data[1], allSongs);
				switch(data[0]) {
					case "add":
						tunes.insert(song);
						break;
					case "remove":
						tunes.remove(song);
						break;
				}
				if (tunes.empty()) {
					System.out.println("Tunes is empty.");
				} else {
					System.out.println("First song: " + tunes.findMin());
					System.out.println("Last song: " + tunes.findMax());
					System.out.print("List: ");
					tunes.traverseSoft(printObject);
					System.out.println();
				}
				lineNum++;
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
		System.err.flush();
		System.out.println("\nDone with FoothillTunesStore.");
	}

	/**
	 * @param title
	 * @param songs
	 * @return song that matched the searched title
	 */
	private static SongEntry findSongByTitle(String title, SongEntry[] songs) {
		for (SongEntry song : songs) {
			if (song.getTitle().equals(title)) {
				return song;
			}
		}
		throw new NoSuchElementException();
	}
}
