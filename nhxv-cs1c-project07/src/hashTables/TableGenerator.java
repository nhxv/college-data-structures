package hashTables;

import cs1c.SongEntry;

import java.util.ArrayList;

/**
 * Create and populate two hash tables tableOfSongIDs and tableOfSongGenres
 * @author Foothill College, Vinh Ngo
 */
public class TableGenerator {
    private FHhashQPwFind<Integer, SongCompInt> tableOfSongIDs;
    private FHhashQPwFind<String, SongsCompGenre> tableOfSongGenres;
    private ArrayList<String> genreSet;

    public TableGenerator() {
        genreSet = new ArrayList<>();
    }

    /**
     * Populate hashtable table of song ids
     * @param allSongs
     * @return tableOfSongIDs
     */
    public FHhashQPwFind<Integer, SongCompInt> populateIDtable(SongEntry[] allSongs) {
        tableOfSongIDs = new FHhashQPwFind<>(allSongs.length);
        for (SongEntry song: allSongs) {
            tableOfSongIDs.insert(new SongCompInt(song));
        }
        return tableOfSongIDs;
    }

    /**
     * Populate hashtable table of genre
     * @param allSongs
     * @return tableOfSongGenres
     */
    public FHhashQPwFind<String, SongsCompGenre> populateGenreTable(SongEntry[] allSongs) {
        for (SongEntry song : allSongs) {
            if (!genreSet.contains(song.getGenre())) {
                genreSet.add(song.getGenre());
            }
        }
        tableOfSongGenres = new FHhashQPwFind<>(genreSet.size());
        for (String genreName : genreSet) {
            SongsCompGenre songsCompGenre = new SongsCompGenre();
            songsCompGenre.setGenre(genreName);
            for (SongEntry song : allSongs) {
                songsCompGenre.addSong(song);
            }
            tableOfSongGenres.insert(songsCompGenre);
        }
        return tableOfSongGenres;
    }

    /**
     * Get a set of genre names
     * @return genreSet
     */
    public ArrayList<String> getGenreNames() {
        return genreSet;
    }
}
