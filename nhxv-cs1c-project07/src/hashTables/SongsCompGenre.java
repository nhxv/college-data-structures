package hashTables;

import cs1c.SongEntry;

import java.util.ArrayList;

/**
 * Wrapper class for SongEntry object, use this class to compare objects based on the Song genre id
 * @author Foothill College, Vinh Ngo
 */
public class SongsCompGenre implements Comparable<String> {
    private String genre;
    private ArrayList<SongEntry> songs;

    public SongsCompGenre() {
        songs = new ArrayList<>();
    }

    /**
     * Add song to the list of songs
     * @param e
     */
    public void addSong(SongEntry e) {
        if (e.getGenre().equals(genre)) {
            songs.add(e);
        }
    }

    public String getGenre() {
        return genre;
    }

    public ArrayList<SongEntry> getSongs() {
        return songs;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSongs(ArrayList<SongEntry> songs) {
        this.songs = songs;
    }

    /**
     * Get genre of the list
     * @return genre name
     */
    public String getName() {
       return genre;
    }

    /**
     * Get a list of songs in the same genre
     * @return list of songs in the same genre
     */
    public ArrayList<SongEntry> getData() {
        return songs;
    }

    public boolean equals(SongsCompGenre o) {
        return songs.equals(o.songs);
    }

    public int hashCode() {
        return genre.hashCode();
    }

    public int compareTo(String o) {
        return this.genre.compareTo(o);
    }

    @Override
    public String toString() {
        return "SongsCompGenre{" +
                "genre='" + genre + '\'' +
                ", songs=" + songs +
                '}';
    }
}
