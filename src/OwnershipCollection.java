import java.util.*;

public class OwnershipCollection {
    public Map<Customer, List<Ownership>> cardsOwned;
    public Map<CreditCard, List<Ownership>> owners;

    public Set<Ownership> ownerships;

    OwnershipCollection()
    {
        cardsOwned = new HashMap<>();
        owners = new HashMap<>();
        ownerships = new HashSet<>();
    }

    public OwnershipCollection(Collection<Customer> customers, Collection<CreditCard> cards, int ownersMax, int cardsMin, int cardsMax) {
        this();

        HashMap<CreditCard, Integer> numOwners = new HashMap<>();

        ArrayList<CreditCard> cardList = new ArrayList<>(cards);

        for (CreditCard card : cards) {
            numOwners.put(card, 0);
        }

        for (Customer customer : customers) {

            /* Randomly decide to pull between 1 and 3 cards from the card set */
            int cardsToPull = new Random().nextInt(cardsMin, cardsMax);

            /* Pull that many cards from the card set */
            for(int i = 0; i < cardsToPull; i++) {
                if(cardList.size() == 0)
                    break;

                /* Pull a random card */
                CreditCard cardAssignment = cardList.get(new Random().nextInt(cardList.size() - 1));

                /* Add it to the ownership collection */
                this.add(new Ownership(customer, cardAssignment));

                /* Increment its number of owners and remove it from the list of available cards
                if it exceeds the maximum. */
                int assignmentOwnerCount = numOwners.get(cardAssignment);
                numOwners.put(cardAssignment, assignmentOwnerCount + 1);
                if(assignmentOwnerCount == ownersMax - 1)
                    cardList.remove(cardAssignment);
            }
        }

        /* It's entirely likely that there are some unowned cards here. That's okay! */
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

        ownerships.add(ownership);
    }

    public String toCSV()
    {
        StringBuilder csvDump = new StringBuilder();
        for(Ownership ownership : ownerships)
        {
            csvDump.append(ownership.toCSV()).append("\n");
        }

        return csvDump.toString();
    }
}
