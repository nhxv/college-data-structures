package queues;

import cs1c.SongEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;

/**
 * Manage three objects of type Queue
 * Add songs to the three playlists "favorites", "lounge", and "road trip" based on user requested file
 * @author Foothill College, Vinh Ngo
 */
public class Jukebox {
    private Queue<SongEntry> favoritePL;
    private Queue<SongEntry> roadTripPL;
    private Queue<SongEntry> loungePL;

    public Jukebox(String favorites, String roadTrip, String lounge) {
        this.favoritePL = new Queue<>(favorites);
        this.roadTripPL = new Queue<>(roadTrip);
        this.loungePL = new Queue<>(lounge);
    }

    public Queue<SongEntry> getFavoritePL() {
        return favoritePL;
    }

    public Queue<SongEntry> getRoadTripPL() {
        return roadTripPL;
    }

    public Queue<SongEntry> getLoungePL() {
        return loungePL;
    }

    /**
     * Read text file then adds song to one of three queues
     * @param requestedFile file path
     * @param songs list of songs
     */
    public void fillPlaylists(String requestedFile, SongEntry[] songs) {
        BufferedReader reader = null;
        String line;
        // data format: playlist,title
        try {
            reader = Files.newBufferedReader(Paths.get(requestedFile), StandardCharsets.US_ASCII);
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                SongEntry song = findSongByTitle(data[1], songs);
                switch(data[0]) {
                    case "lounge":
                        loungePL.enqueue(song);
                        break;
                    case "favorites":
                        favoritePL.enqueue(song);
                        break;
                    case "road trip":
                        roadTripPL.enqueue(song);
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch(IOException err) {
                err.printStackTrace();
            }
        }
    }

    /**
     * @param title song title
     * @param songs list of songs
     */
    public SongEntry findSongByTitle(String title, SongEntry[] songs) {
        for (SongEntry song : songs) {
            if (song.getTitle().equals(title)) {
                return song;
            }
        }
        throw new NoSuchElementException();
    }
}
