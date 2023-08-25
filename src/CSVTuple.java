public class CSVTuple {

    private String tuple = "";

    public CSVTuple()
    {
        tuple += '(';
    }
    public String toString()
    {
        return tuple;
    }

    public void append(String addition)
    {
        tuple += (addition + ",");
    }

    public void close()
    {
        /* Trim the last unnecessary comma and add a final )*/
        tuple = tuple.substring(0, tuple.length() - 1) + ")";
    }
}
