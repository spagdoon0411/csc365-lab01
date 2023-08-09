import java.util.HashMap;
import java.util.Map;

public class VendorCollection {
    Map<Integer, Vendor> idLookup;

    public VendorCollection()
    {
        idLookup = new HashMap<>();
    }

    public void add(Vendor v)
    {
        idLookup.put(v.getId(), v);
    }
}
