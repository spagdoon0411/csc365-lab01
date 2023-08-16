import java.math.BigInteger;
import java.util.Random;

public class CreditCard {
    private BigInteger num;
    public enum type {
        Visa,
        MasterCard,
        AmericanExpress,
        Discover;

        public static type[] byIndex = new type[] { Visa, MasterCard, AmericanExpress, Discover };
    }

    CreditCard.type cardType;

    private int limit;

    private double balance;

    private boolean active;

    private static BigInteger randomNum = new BigInteger("1234123412341234");

    public CreditCard(BigInteger num, CreditCard.type type, int limit, double balance)
    {
        this.num = num;
        this.cardType = type;
        this.limit = limit;
        this.balance = balance;
        this.active = true;
    }

    public BigInteger getNum() {
        return num;
    }

    public boolean isActive()
    {
        return this.active;
    }

    public void setActive(boolean b)
    {
        this.active = b;
    }

    public void incrementBalance(double deltaBalance)
    {
        this.balance += deltaBalance;
    }

    public String toString()
    {
        return "No: " + this.num + " \n"
                + "Type: " + this.cardType + " \n"
                + "Limit: " + this.limit + " \n"
                + "Balance: " + this.balance + " \n"
                + "Active: " + (this.active ? "Active" : "Inactive");
    }

    public CreditCard() {
        this.num = CreditCard.randomNum;
        this.cardType = type.byIndex[(int)(Math.random() * 5)];
        this.limit = (int)(Math.random() * 1001);
        this.balance = (int)(Math.random() * limit * 100) / 100.0;
        this.active = true;

        CreditCard.randomNum = CreditCard.randomNum.add(RandomData.one);
    }

    public String toCSV()
    {
        CSVTuple tuple = new CSVTuple();
        tuple.append(this.getNum().toString());
        tuple.append(this.getType());
        tuple.append(String.valueOf(this.getLimit()));
        tuple.append(String.valueOf(this.balance));
        tuple.append(this.isActive() ? "1" : "0");
        tuple.close();
        return tuple.toString();
    }

    private int getLimit() {
        return this.limit;
    }

    private String getType() {
        return String.valueOf(this.cardType);
    }
}
