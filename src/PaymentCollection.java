import java.util.*;

public class PaymentCollection {
    Map<Integer, Payment> idLookup;

    public PaymentCollection()
    {
        idLookup = new HashMap<>();
    }

    public void add(Payment p)
    {
        idLookup.put(p.getId(), p);
    }

    public String toCSV()
    {
        StringBuilder csvDump = new StringBuilder();
        List<Payment> payments = idLookup.values().stream().toList();
        for(Payment p : payments)
        {
            csvDump.append(p.toCSV()).append("\n");
        }

        return csvDump.toString();
    }
}
