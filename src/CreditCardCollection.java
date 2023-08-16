import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreditCardCollection {

    Map<String, CreditCard> numLookup;

    /**
     * Creates a new, empty CreditCardCollection
     */
    public CreditCardCollection()
    {
        this.numLookup = new HashMap<>();
    }

    /**
     * Adds a credit card and enables O(1) searching by number
     * @param c
     */
    public void add(CreditCard c)
    {
        this.numLookup.put(c.getNum().toString(), c);
    }

}
