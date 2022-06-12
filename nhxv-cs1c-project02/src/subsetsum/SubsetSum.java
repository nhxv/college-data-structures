package subsetsum;

import cs1c.SongEntry;

import java.util.*;

/**
 * Find subset sum within given target
 *
 * @author Foothill College, Vinh Ngo
 */
public class SubsetSum {
    protected static ArrayList<SongEntry> findSubsetOfSongs(ArrayList<SongEntry> songList, double duration) {
        ArrayList<Double> durationList = new ArrayList<>();
        ArrayList<SongEntry> playlist = new ArrayList<>();
        for (SongEntry song : songList) {
            durationList.add((double) song.getDuration());
        }
        ArrayList<Double> timeList = findSubset(durationList, duration*60);
        for (double time : timeList) {
            for (SongEntry song: songList) {
                if (song.getDuration() == time && !playlist.contains(song)) {
                    playlist.add(song);
                    break;
                }
            }
        }
        return playlist;
    }

    protected static ArrayList<Double> findSubset(ArrayList<Double> numList, double target) {
        if (sum(numList) <= target) {
            return numList;
        }
        // setup collection with empty subset
        ArrayList<ArrayList<Double>> collection = new ArrayList<>();
        collection.add(new ArrayList<>());
        // find subset
        ArrayList<Double> largestList = new ArrayList<>();
        double largestSum = 0;
        for (double number : numList) {
            if (number == target) {
                ArrayList<Double> list = new ArrayList<>();
                list.add(number);
                return list;
            }
            if (number < target) {
                ArrayList<ArrayList<Double>> extraCollection = new ArrayList<>();
                ArrayList<ArrayList<Double>> deletedCollection = new ArrayList<>();
                for (ArrayList<Double> list : collection) {
                    if (sum(list) + number == target) {
                        list.add(number);
                        return list;
                    }
                    if (sum(list) + number < target) {
                        // optimize for large data set only
                        if (numList.size() > 1000) {
                            ArrayList<Double> newList = (ArrayList<Double>) list.clone();
                            newList.add(number);
                            extraCollection.add(newList);
                            if (sum(newList) > largestSum) {
                                largestSum = sum(newList);
                                deletedCollection.add(list);
                                deletedCollection.add(largestList);
                                largestList = newList;
                            }
                        } else {
                            ArrayList<Double> newList = (ArrayList<Double>) list.clone();
                            newList.add(number);
                            extraCollection.add(newList);
                            if (sum(newList) > largestSum) {
                                largestSum = sum(newList);
                                largestList = newList;
                            }
                        }
                    }
                }
                if (collection.size() != 0) {
                    for (ArrayList<Double> deletedList : deletedCollection) {
                        collection.remove(deletedList);
                    }
                }
                collection.addAll(extraCollection);
            }
        }
        return largestList;
    }

    private static double sum(ArrayList<Double> numList) {
        double sum = 0;
        if (numList.isEmpty()) {
            return sum;
        }
        for (double num : numList) {
            sum += num;
        }
        return sum;
    }
}
