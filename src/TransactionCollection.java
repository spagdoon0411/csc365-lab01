import com.sun.source.tree.Tree;

import java.util.*;

public class TransactionCollection {
    Map<CreditCard, TreeMap<Long, List<Transaction>>> cardLookup;

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

            TreeMap<Long, List<Transaction>> dateTree = cardLookup.get(card);

            List<Transaction> dateList = dateTree.get(transaction.getDate().getTime());

            if(dateList == null)
            {
                ArrayList<Transaction> newList = new ArrayList<>();
                newList.add(transaction);
                dateTree.put(transaction.getDate().getTime(), newList);
            }
            else
            {
                dateList.add(transaction);
            }
        }
        /* Case 2: card doesn't exist */
        else
        {
            TreeMap<Long, List<Transaction>> newMap = new TreeMap<>();
            ArrayList<Transaction> newList = new ArrayList<>();
            newList.add(transaction);
            newMap.put(transaction.getDate().getTime(), newList);
            cardLookup.put(transaction.getCreditCard(), newMap);
        }
    }
}
