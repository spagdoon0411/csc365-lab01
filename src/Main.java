import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Bank b = new Bank();

        b.newCustomer("123-12-1234",
                "Jim Hopper",
                "123 S. Kentucky Dr.",
                "345-123-8197"
        );

        System.out.println("-> New customer and customer printing:");
        b.printCustomerById(1);
        b.printCustomerBySsn("123-12-1234");

        System.out.println("-> New card and card printing (by ID/SSN)" +
                " and by card number:");

        b.newCard(
                CreditCard.type.AmericanExpress,
                700,
                1
        );

        b.printAllCardsId(1);
        b.printAllCardsSsn("123-12-1234");
        b.printCardByNum("1234568");

        System.out.println("-> Assigning one card to multiple people: ");
        b.newCustomer(
                "123-12-1235",
                "Bill Jones",
                "432 S. East Rd.",
                "123-123-1234"
        );
        b.assignCard(2, "1234568");
        b.printCardByNum("1234568");

        System.out.println("-> Activating and cancelling cards: ");
        b.activateCard("1234568");
        b.printCardByNum("1234568");
        b.cancelCard("1234568");
        b.printCardByNum("1234568");

        System.out.println("-> Adding vendors, transactions, and payments:");
        b.newVendor("Best Buy", "3 Best Buy Ln.");
        b.activateCard("1234568");
        b.newTransaction("1234568",
                1,
                1,
                -999.99
        );

        System.out.println("Transaction made--results for the card:");
        b.printAllCardsSsn("123-12-1234");

        b.newPayment("1234568", 1, 900.00);

        System.out.println("Payment made--results for the card:");
        b.printAllCardsSsn("123-12-1234");

        System.out.println("-> Printing transactions in a date range:");

        Date date0 = new Date(114, Calendar.FEBRUARY, 11);
        b.newVendor("Target", "456 E. Highland Blvd."); /* Id 2 */
        b.newVendor("Walmart", "459 E. Highland Blvd."); /* Id 3 */
        b.newTransactionOn(date0, "1234568", 2, 2, -30);
        b.newTransactionOn(date0,"1234568", 1, 3, -23);
        Date date1 = new Date(115, Calendar.FEBRUARY, 11);
        b.newTransactionOn(date1,"1234568", 1, 1, -683);
        b.newTransactionOn(date1,"1234568", 2, 2, -92);
        b.newTransactionOn(date1,"1234568", 1, 3, -2);
        b.newTransactionOn(date1,"1234568", 2, 1, -4);
        Date date2 = new Date(116, Calendar.FEBRUARY, 11);
        b.newTransactionOn(date2,"1234568", 2, 2, -12);
        b.newTransactionOn(date2,"1234568", 1, 2, -38);
        Date date3 = new Date(117, Calendar.FEBRUARY, 11);
        b.newTransactionOn(date3,"1234568", 1, 3, -97);

        b.printTransactions("1234568", date1, date2);

        b.printTransactions("1234568", date1, date3);

        b.printTransactions("1234568", date2, date3);
    }

}