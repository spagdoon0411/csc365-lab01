import java.util.HashMap;
import java.util.Map;

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
}
