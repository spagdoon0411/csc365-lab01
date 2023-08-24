import java.util.HashMap;
import java.util.List;
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

    public String toCSV()
    {
        StringBuilder csvDump = new StringBuilder();
        List<Vendor> vendors = idLookup.values().stream().toList();
        for(Vendor v : vendors)
        {
            csvDump.append(v.toCSV()).append("\n");
        }

        return csvDump.toString();
    }
}
