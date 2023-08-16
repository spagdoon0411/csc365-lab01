import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Ownership {
    private CreditCard card;
    private Customer customer;

    private boolean updated;

    public Ownership(Customer customer, CreditCard card)
    {
        this.card = card;
        this.customer = customer;
        this.updated = true;
    }

    public boolean getUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated)
    {
        this.updated = updated;
    }

    public CreditCard getCard()
    {
        return this.card;
    }

    public Customer getCustomer() { return this.customer; }

}