import java.math.BigInteger;

public class Customer {
    private int id;
    private String ssn;

    private String name;

    private String address;

    private String phone;

    private static int randomId;

    private static BigInteger randomPhone = new BigInteger("1231231234");
    private static BigInteger randomSSN = new BigInteger("123121234");

    public Customer(int id, String ssn, String name, String address, String phone)
    {
        this.id = id;
        this.ssn = ssn;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    /* Creates a random customer */
    public Customer()
    {
        this(randomId,
                randomSSN.toString(),
                "Customer" + randomId,
                "Address" + randomId,
                randomPhone.toString()
        );
        randomSSN = randomSSN.add(RandomData.one);
        randomId += 1;
        randomPhone = randomPhone.add(RandomData.one);
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

    public String toCSV()
    {
        CSVTuple tuple = new CSVTuple();

        tuple.append(String.valueOf(this.getId()));
        tuple.append(this.getSsn());
        tuple.append(this.getName());
        tuple.append(this.getAddress());
        tuple.append(this.getPhone());
        tuple.close();

        return tuple.toString();
    }

    private String getAddress() {
        return this.address;
    }

    private String getPhone() {
        return this.phone;
    }

}
