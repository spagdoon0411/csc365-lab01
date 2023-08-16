import java.util.*;
import java.util.stream.Collectors;

public class ActivitySimulation extends Bank {

    public ActivitySimulation()
    {
        /* Initialize collections */
        super();

        /* TODO: Populate collections with random data */


    }

    /* Creates the same number of transactions and payments. */
    public void SimulateOn(int iterations, int numPayments)
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
