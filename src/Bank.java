import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Bank {
    static BigInteger one = new BigInteger("1");
    CreditCardCollection cards;
    static BigInteger cardNumber = new BigInteger("1234123412341234");
    CustomerCollection customers;
    static int customerID = 0;
    OwnershipCollection ownerships;
    TransactionCollection transactions;
    VendorCollection vendors;
    static int vendorId = 0;

    PaymentCollection payments;
    static int paymentId = 0;

    public Bank() {
        cards = new CreditCardCollection();
        customers = new CustomerCollection();
        ownerships = new OwnershipCollection();
        transactions = new TransactionCollection();
        vendors = new VendorCollection();
        payments = new PaymentCollection();
    }

    public void newCustomer(String ssn,
                            String name,
                            String address,
                            String phone) {
        customers.add(new Customer(++customerID, ssn, name, address, phone));
    }

    public void newCard(CreditCard.type type, int limit, int customerId) throws RuntimeException {
        Customer customer = customers.idLookup.get(customerId);

        cardNumber = cardNumber.add(one);

        CreditCard newCard = new CreditCard(cardNumber, type, limit, 0);

        if (customer != null) {
            cards.add(newCard);
        } else {
            throw new RuntimeException("No such customer exists");
        }

        ownerships.add(new Ownership(customer, newCard));
    }

    public void assignCard(int customerId, String cardNumber)
    {
        Customer customer = customers.idLookup.get(customerId);

        CreditCard card = cards.numLookup.get(cardNumber);

        if(customer == null)
        {
            throw new RuntimeException("No such customer exists");
        }
        else if(card == null)
        {
            throw new RuntimeException("No such card exists");
        }
        else
        {
            ownerships.add(new Ownership (customer, card));
        }
    }

    public void cancelCard(String cardNum) throws RuntimeException {
        CreditCard card = cards.numLookup.get(cardNum);

        if (card != null) {
            card.setActive(false);
            ownerships.owners.get(card).forEach(ownership -> ownership.setUpdated(false));
        } else {
            throw new RuntimeException("No such card exists");
        }

    }

    public void activateCard(String cardNumber) throws RuntimeException {
        CreditCard card = cards.numLookup.get(cardNumber);

        if (card != null) {
            card.setActive(true);
            ownerships.owners.get(card).forEach(ownership -> ownership.setUpdated(true));
        } else {
            throw new RuntimeException("No such card exists");
        }
    }

    public void newVendor(String name, String address) {
        Vendor vendor = new Vendor(++vendorId, name, address);
        vendors.add(vendor);
    }

    public void newTransaction(String cardNumber,
                               int customerID,
                               int vendorId,
                               double deltaCustomerBalance) throws RuntimeException {
        newTransactionOn(new Date(), cardNumber, customerID, vendorId, deltaCustomerBalance);
    }

    public void newTransactionOn(Date date,
                               String cardNumber,
                               int customerID,
                               int vendorId,
                               double deltaCustomerBalance) throws RuntimeException {
        Vendor vendor = vendors.idLookup.get(vendorId);
        CreditCard card = cards.numLookup.get(cardNumber);
        Customer customer = customers.idLookup.get(customerID);

        if (vendor == null) {
            throw new RuntimeException("No such vendor exists");
        } else if (card == null || !card.isActive()) {
            throw new RuntimeException("No such card exists or card is inactive");
        } else if (customer == null) {
            throw new RuntimeException("No such customer exists");
        } else {
            boolean activelyOwnsCard = ownerships.owners.get(card)
                    .stream()
                    .anyMatch(o -> o.getCustomer() == customer && o.getUpdated());

            if (!activelyOwnsCard)
                throw new RuntimeException("Customer doesn't actively own this card");

            Transaction t = new Transaction(
                    date,
                    customer,
                    card,
                    vendor,
                    deltaCustomerBalance
            );

            this.transactions.add(t);
        }
    }

    public void newPayment(String cardNumber, int customerID, double deltaBalance) throws RuntimeException {
        CreditCard card = cards.numLookup.get(cardNumber);
        Customer customer = customers.idLookup.get(customerID);

        Date date = new Date();

        if (card == null) {
            throw new RuntimeException("No such card exists");
        } else if (customer == null) {
            throw new RuntimeException("No such customer exists");
        } else {
            boolean activelyOwnsCard = ownerships.owners.get(card)
                    .stream()
                    .anyMatch(o -> o.getCustomer() == customer && o.getUpdated());

            if (!activelyOwnsCard)
                throw new RuntimeException("Customer doesn't actively own this card");

            Payment p = new Payment(++paymentId, card, deltaBalance, date);
            payments.add(p);
        }
    }

    private Customer findCustomerId(int id) {
        return customers.idLookup.get(id);
    }

    private Customer findCustomerSsn(String ssn) {
        return customers.ssnLookup.get(ssn);
    }

    public void printCustomerById(int id) {
        System.out.println(findCustomerId(id));
    }

    public void printCustomerBySsn(String ssn) {
        System.out.println(findCustomerSsn(ssn));
    }

    public void printOwnersByNum(String num)
    {
        CreditCard card = this.cards.numLookup.get(num);
        System.out.println(card.toString());
        System.out.println("Owners:");

        List<Ownership> owners = ownerships.owners.get(card);
        if(owners != null)
            ownerships.owners.get(card)
                    .forEach(o -> System.out.println("\t" + o.getCustomer().getName()
                    + ", ID " + o.getCustomer().getId()));
        else
            System.out.println("\tNone");
        System.out.println();
    }

    public void printAllCardsId(int id) {
        Customer customer = findCustomerId(id);
        System.out.println(customer.getName() + "'s cards:");
        ownerships.cardsOwned.get(customer)
                .forEach(ownership -> System.out.println(ownership.getCard().toString() + "\n"));
    }

    public void printAllCardsSsn(String ssn) {
        Customer customer = findCustomerSsn(ssn);
        System.out.println(customer.getName() + "'s cards:");
        ownerships.cardsOwned.get(customer)
                .forEach(ownership -> System.out.println(ownership.getCard().toString() + "\n"));
    }

    public void printTransactions(String num, Date from, Date to) {
        CreditCard card = cards.numLookup.get(num);
        transactions.cardLookup.get(card)
                .subMap(from.getTime(), to.getTime())
                .values()
                .forEach(c -> c.forEach(System.out::println));
    }

}
