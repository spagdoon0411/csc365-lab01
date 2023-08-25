import com.sun.source.tree.Tree;

import java.util.*;

public class TransactionCollection {
    Map<CreditCard, TreeMap<Long, List<Transaction>>> cardLookup;

    Set<Transaction> transactions;

    public TransactionCollection()
    {
        cardLookup = new HashMap<>();
        transactions = new HashSet<>();
    }

    public void add(Transaction transaction)
    {
        transactions.add(transaction);

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

    public String toCSV(boolean cheat)
    {
        StringBuilder csvDump = new StringBuilder();
        for(Transaction t : transactions)
        {
            csvDump.append(t.toCSV()).append("\n");
        }

        return csvDump.toString();
    }
}
