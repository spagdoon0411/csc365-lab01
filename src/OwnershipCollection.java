import java.util.*;

public class OwnershipCollection {
    public Map<Customer, List<Ownership>> cardsOwned;
    public Map<CreditCard, List<Ownership>> owners;

    OwnershipCollection()
    {
        cardsOwned = new HashMap<>();
        owners = new HashMap<>();
    }

    public void add(Ownership ownership)
    {
        if(cardsOwned.containsKey(ownership.getCustomer()))
        {
            cardsOwned.get(ownership.getCustomer()).add(ownership);
        }
        else
        {
            cardsOwned.put(ownership.getCustomer(), new ArrayList<>(Collections.singleton(ownership)));
        }

        if(owners.containsKey(ownership.getCard()))
        {
            owners.get(ownership.getCard()).add(ownership);
        }
        else
        {
            owners.put(ownership.getCard(), new ArrayList<>(Collections.singleton(ownership)));
        }
    }
}
