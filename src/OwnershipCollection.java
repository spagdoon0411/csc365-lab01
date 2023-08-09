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
        if(cardsOwned.containsKey(ownership.customer))
        {
            cardsOwned.get(ownership.customer).add(ownership);
        }
        else
        {
            cardsOwned.put(ownership.customer, new ArrayList<>(Collections.singleton(ownership)));
        }

        if(owners.containsKey(ownership.card))
        {
            owners.get(ownership.card).add(ownership);
        }
        else
        {
            owners.put(ownership.card, new ArrayList<>(Collections.singleton(ownership)));
        }
    }
}
