import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerCollection {

    public Map<Integer, Customer> idLookup;
    public Map<String, Customer> ssnLookup;

    /**
     * Creates a new, empty CustomerCollection.
     */
    public CustomerCollection() {
        this.idLookup = new HashMap<>();
        this.ssnLookup = new HashMap<>();
    }

    /**
     * Adds a customer and enables O(1) searching by SSN and ID
     * @param c
     */
    public void add(Customer c) {
        idLookup.put(c.getId(), c);
        ssnLookup.put(c.getSsn(), c);
    }

    public String toCSV()
    {
        StringBuilder csvDump = new StringBuilder();
        List<Customer> customers = idLookup.values().stream().toList();
        for(Customer c : customers)
        {
            csvDump.append(c.toCSV()).append("\n");
        }

        return csvDump.toString();
    }
}
