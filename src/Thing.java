import java.util.List;
import java.util.Map;

public class Thing {
    public class Entry {
        int id;
    }

    public class Collection
    {
        public Collection()
        {

        }

        private static List<Map> mappings;

        public void insert(Entry insertion) {
            // For each mapping in mappings, add a reference
            // to the insertion by the appropriate key.
        }
    }
}
