//Example of an Employee class
class Employee
{
    public static final int MAX_LEN = 50;

    private String name;
    private int ss;

    public Employee( String name , int ss)
    {
        this();
        setName(name);
        setSS(ss);
    }

    public Employee()
    {
        name = "undefined";
        ss = 0;
    }

    String getName() { return name; }
    int getSS() { return ss; }

    boolean setName( String name )
    {
        if (name == null)
            return false;
        if (name.length() > MAX_LEN)
            return false;
        this.name = name;
        return true;
    }

    boolean setSS( int ss )
    {
        if (ss < 0 || ss > 999999999 )
            return false;
        this.ss = ss;
        return true;
    }

    public String toString()
    {
        return name + " (" + ss + ")";
    }
};