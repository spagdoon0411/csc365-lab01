public class CreditCard {
    long num;
    public enum type {
        Visa,
        MasterCard,
        AmericanExpress,
        Discover
    }

    CreditCard.type cardType;

    int limit;

    double balance;

    boolean active;

    public CreditCard(long num, CreditCard.type type, int limit, double balance)
    {
        this.num = num;
        this.cardType = type;
        this.limit = limit;
        this.balance = balance;
        this.active = false;
    }

    public long getNum() {
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
}
