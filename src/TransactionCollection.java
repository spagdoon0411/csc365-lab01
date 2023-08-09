import com.sun.source.tree.Tree;

import java.util.*;

public class TransactionCollection {
    Map<CreditCard, TreeMap<Date, List<Transaction>>> cardLookup;

    public TransactionCollection()
    {
        cardLookup = new HashMap<>();
    }

    public void add(Transaction transaction)
    {
        /* Case 1: card already exists */
        if(cardLookup.containsKey(transaction.getCreditCard()))
        {
            CreditCard card = transaction.getCreditCard();

            Transaction transaction =

            cardLookup.get(card).get(transaction.getDate()).add(transaction);
        }
        /* Case 2: card doesn't exist */
        else
        {
            TreeMap<Date, List<Transaction>> newMap = new TreeMap<>();
            ArrayList<Transaction> newList = new ArrayList<>();
            newList.add(transaction);
            newMap.put(transaction.getDate(), newList);
            cardLookup.put(transaction.getCreditCard(), newMap);
        }
    }
}
