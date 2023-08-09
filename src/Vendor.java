
public class Vendor {
    // id, name, location of main office.

    int id;
    String name;
    String address;

    public Vendor(int id, String name, String address)
    {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId()
    {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
