/**
 * Quick sort data object
 * @author Foothill College, Vinh Ngo
 */
public class Data {
    private int recursionLimit;
    private long arraySizes;
    private long times;

    public Data(int recursionLimit, long arraySizes, long times) {
        this.recursionLimit = recursionLimit;
        this.arraySizes = arraySizes;
        this.times = times;
    }

    public int getRecursionLimit() {
        return recursionLimit;
    }

    public long getArraySizes() {
        return arraySizes;
    }

    public long getTimes() {
        return times;
    }
}
