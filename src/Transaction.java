import java.util.Date;

public class Transaction {
    // date, customer id, cc number, and vender id.
    private Date date;
    private CreditCard creditCard;

    private Customer customer;

    private Vendor vendor;
    private double deltaCustomerBalance;

    public Transaction(Date date,
                       Customer customer,
                       CreditCard creditCard,
                       Vendor vendor,
                       double deltaCustomerBalance)
    {
        this.customer = customer;
        this.date = date;
        this.creditCard = creditCard;
        this.creditCard.incrementBalance(deltaCustomerBalance);
        this.vendor = vendor;
        this.deltaCustomerBalance = deltaCustomerBalance;
    }

    public Date getDate()
    {
        return this.date;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public String toString() {
        return "Date: " + this.date + " \n"
                + "Customer: " + this.customer.getName() + " \n"
                + "Card: " + this.creditCard.getNum() + " \n"
                + "Vendor " + this.vendor.getName() + " \n"
                + "Price: " + this.deltaCustomerBalance + " \n";

    }

    public String toCSV()
    {
        CSVTuple tuple = new CSVTuple();
        tuple.append(String.valueOf(this.date));
        tuple.append(String.valueOf(this.creditCard.getNum()));
        tuple.append(String.valueOf(this.customer.getId()));
        tuple.append(String.valueOf(this.vendor.getId()));
        tuple.append(String.valueOf(this.deltaCustomerBalance));
        tuple.close();

        return tuple.toString();
    }
}
