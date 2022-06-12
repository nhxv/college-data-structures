public class EmployeeCompInt implements Comparable<Integer>
{
    Employee data;
    public EmployeeCompInt(Employee e){ data = e; }
    public String toString() { return data.toString(); }

    // we'll use compareTo() to implement our find on key
    @Override
    public int compareTo(Integer key) {
        return data.getSS() - key;
    }

    // let equals() preserve the equals() provided by embedded data
    public boolean equals( EmployeeCompInt rhs ) {
        return data.equals(rhs.data);
    }

    public int hashCode() {
        return data.getSS();
    }
}
