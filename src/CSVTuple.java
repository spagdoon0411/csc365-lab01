public class CSVTuple {
    private String tuple = "";
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
        /* Trim the last unnecessary comma */
        tuple = tuple.substring(0, tuple.length() - 2);
    }
}
