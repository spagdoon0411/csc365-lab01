import java.util.*;
import java.util.stream.Collectors;

public class ActivitySimulation extends Bank {

    /* Creates a simulation environment with a given number of customers,
    * cards, and vendors. Each card has at most ownersMax owners and each
    * customer has between cardsMin and cardsMax cards. */
    public ActivitySimulation(int nCustomers, int nCards, int nVendors,
                              int ownersMax, int cardsMin, int cardsMax)
    {
        customers = new CustomerCollection();
        cards = new CreditCardCollection();
        vendors = new VendorCollection();

        for(int i = 0; i < nCustomers; i++)
        {
            customers.add(new Customer());
        }

        for(int i = 0; i < nCards; i++)
        {
            cards.add(new CreditCard());
        }

        for(int i = 0; i < nVendors; i++)
        {
            vendors.add(new Vendor());
        }

        ownerships = new OwnershipCollection(customers.idLookup.values(),
                cards.numLookup.values(),
                ownersMax,
                cardsMin,
                cardsMax
        );

        transactions = new TransactionCollection();
        payments = new PaymentCollection();
    }

    /* Runs a simulation, creating 'iterations' transactions and payments. */
    public void SimulateOn(int iterations)
    {
        ArrayList<Vendor> vendorList = new ArrayList<>(vendors.idLookup.values());
        ArrayList<CreditCard> cardList = new ArrayList<>(cards.numLookup.values());

        for(int i = 0; i < iterations; i++)
        {
            /* Pick a random card, customer (owner), and vendor. */
            CreditCard victim = cardList.get(new Random().nextInt(cardList.size()));
            Vendor assailant = vendorList.get(new Random().nextInt(vendorList.size()));
            List<Ownership> victimOwners = ownerships.owners.get(victim);
            Customer victimOwner = victimOwners.get(new Random().nextInt(0, victimOwners.size())).getCustomer();

            /* Pick random payment (between balance and 0) and transaction (between 0 and balance available) */
            double paymentDelta = -new Random().nextDouble(victim.getBalance(), 0);
            double transactionDelta = -new Random().nextDouble(0.0, victim.getLimit() + victim.getBalance());

            newPayment(victim.getNum().toString(),
                    victimOwner.getId(),
                    paymentDelta
            );

            newTransaction(
                    victim.getNum().toString(),
                    victimOwner.getId(),
                    assailant.getId(),
                    transactionDelta
            );
        }
    }
}
