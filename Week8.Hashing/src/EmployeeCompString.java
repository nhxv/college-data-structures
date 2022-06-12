public class EmployeeCompString implements Comparable<String>
{
    Employee data;
    public EmployeeCompString( Employee e ){ data = e; }
    public String toString() { return data.toString(); }

    // we'll use compareTo() to implement our find on key
    public int compareTo(String key) {
        return data.getName().compareTo(key);
    }

    // let equals() preserve the equals() provided by embedded data
    public boolean equals( EmployeeCompString rhs )
    {
        return data.equals(rhs.data);
    }

    public int hashCode()
    {
        return data.getName().hashCode();
    }
}