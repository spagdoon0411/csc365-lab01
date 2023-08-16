
public class Vendor {
    // id, name, location of main office.

    private int id;
    private static int randomID = 0;
    private String name;
    private String address;

    public Vendor(int id, String name, String address)
    {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Vendor()
    {
        this.id = ++randomID;
        this.name = "Vendor" + randomID;
        this.address = "Address" + randomID;
    }

    public String toCSV()
    {
        CSVTuple tuple = new CSVTuple();
        tuple.append(String.valueOf(this.getId()));
        tuple.append(this.name);
        tuple.append(this.address);
        tuple.close();
        return tuple.toString();
    }

    public int getId()
    {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
