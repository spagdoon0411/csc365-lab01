import java.util.Date;

public class Bank {
    CreditCardCollection cards = new CreditCardCollection();
    static long cardNumber = 1234567;
    CustomerCollection customers = new CustomerCollection();
    static int customerID = 0;
    OwnershipCollection ownerships = new OwnershipCollection();
    TransactionCollection transactions = new TransactionCollection();
    VendorCollection vendors = new VendorCollection();
    static int vendorId = 0;

    PaymentCollection payments = new PaymentCollection();
    static int paymentId = 0;

    public Bank() {

    }

    public void newCustomer(String ssn,
                            String name,
                            String address,
                            String phone) {
        customers.add(new Customer(++customerID, ssn, name, address, phone));
    }

    public void newCard(CreditCard.type type, int limit, int customerId) throws RuntimeException {
        Customer customer = customers.idLookup.get(customerId);

        CreditCard newCard = new CreditCard(++cardNumber, type, limit, 0);

        if (customer != null) {
            cards.add(newCard);
        } else {
            throw new RuntimeException("No such customer exists");
        }

        ownerships.add(new Ownership(customer, newCard));
    }

    public void assignCard(int customerId, long cardNumber)
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

    public void cancelCard(long cardNum) throws RuntimeException {
        CreditCard card = cards.numLookup.get(cardNum);

        if (card != null) {
            card.setActive(false);
            ownerships.owners.get(card).forEach(ownership -> ownership.setUpdated(false));
        } else {
            throw new RuntimeException("No such card exists");
        }

    }

    public void activateCard(long cardNumber) throws RuntimeException {
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

    public void newTransaction(long cardNumber,
                               int customerID,
                               int vendorId,
                               double deltaCustomerBalance) throws RuntimeException {
        newTransactionOn(new Date(), cardNumber, customerID, vendorId, deltaCustomerBalance);
    }

    public void newTransactionOn(Date date,
                               long cardNumber,
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
                    .anyMatch(o -> o.customer == customer && o.updated);

            if (!activelyOwnsCard)
                throw new RuntimeException("Customer doesn't actively own this card");

            Transaction t = new Transaction(
                    date,
                    customer,
                    card,
                    vendor,
                    deltaCustomerBalance
            );
            transactions.add(t);
        }
    }

    public void newPayment(long cardNumber, int customerID, double deltaBalance) throws RuntimeException {
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
                    .anyMatch(o -> o.customer == customer && o.updated);

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

    public void printCardByNum(long num)
    {
        CreditCard card = this.cards.numLookup.get(num);
        System.out.println(card.toString());
        System.out.println("Owners:");
        ownerships.owners.get(card)
                .forEach(o -> System.out.println("\t" + o.getCustomer().getName()
                + ", ID " + o.getCustomer().getId()));
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

    public void printTransactions(long num, Date from, Date to) {
        CreditCard card = cards.numLookup.get(num);
        transactions.cardLookup.get(card)
                .subMap(from, to)
                .values()
                .forEach(c -> c.forEach(System.out::println));
    }

}
