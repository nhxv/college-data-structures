package hashTables;

import cs1c.SongEntry;

/**
 * Wrapper class for song entry object, use this to compare objects based on the songs int id field
 * @author Foothill College, Vinh Ngo
 */
public class SongCompInt implements Comparable<Integer> {
    private SongEntry songEntry;

    public SongCompInt(SongEntry songEntry) {
        this.songEntry = songEntry;
    }

    public int compareTo(Integer id) {
        return songEntry.getID() - id;
    }

    public boolean equals(SongCompInt o) {
        return songEntry.equals(o);
    }

    /**
     * Hashcode for this object is the song id
     * @return song id
     */
    public int hashCode() {
        return songEntry.getID();
    }

    @Override
    public String toString() {
        return "SongCompInt{" +
                "songEntry=" + songEntry +
                '}';
    }
}
