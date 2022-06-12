package week08_part01;

/**
 * Example employee class.
 */
public class Employee
{
    public static final int MAX_LEN = 50;

    private String name;
    private double salary;
    private int id;

    public Employee(String name , int id)
    {
        this();
        setName(name);
        setID(id);
        salary = 1500.00;
    }

    public Employee()
    {
        name = "undefined";
        id = 0;
    }

    String getName()
    { return name; }

    int getID() { return id; }

    boolean setName( String name )
    {
        if (name == null)
            return false;
        if (name.length() > MAX_LEN)
            return false;
        this.name = name;
        return true;
    }

    boolean setID( int updatedID )
    {
        if (updatedID < 0 || updatedID > 999999999 )
            return false;
        this.id = updatedID;
        return true;
    }

    public String toString()
    {
        return name + " (" + id + ")";
    }

    public boolean equals( Employee rhs )
    {
        return id == rhs.id;
    }

    public int hashCode()
    {
        return id;
    }
}