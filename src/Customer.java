public class Customer {
    private int id;
    private String ssn;

    private String name;

    private String address;

    private String phone;

    public Customer(int id, String ssn, String name, String address, String phone)
    {
        this.id = id;
        this.ssn = ssn;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getId()
    {
        return this.id;
    }

    public String getSsn()
    {
        return this.ssn;
    }

    public String getName()
    {
        return this.name;
    }

    public String toString()
    {
        return "ID: " + this.id + " \n"
                + "Name: " + this.name + " \n"
                + "SSN: " + this.ssn + " \n"
                + "Address: " + this.address + " \n"
                + "Phone: " + this.phone + " \n";
    }
}
