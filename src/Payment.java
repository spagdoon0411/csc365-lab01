import java.util.Date;

public class Payment {
    private int id;
    private CreditCard creditCard;
    private double deltaBalance;

    private Date date;

    public Payment(int id, CreditCard creditCard, double deltaBalance, Date date)
    {
        this.id = id;
        this.creditCard = creditCard;
        this.deltaBalance = deltaBalance;
        this.creditCard.incrementBalance(deltaBalance);
        this.date = date;
    }

    public int getId()
    {
        return this.id;
    }

    public String toCSV()
    {
        CSVTuple tuple = new CSVTuple();
        tuple.append(String.valueOf(this.id));
        tuple.append(String.valueOf(this.creditCard.getNum()));
        tuple.append(String.valueOf(this.deltaBalance));
        tuple.append(this.date.toString());
        tuple.close();

        return tuple.toString();
    }
}
