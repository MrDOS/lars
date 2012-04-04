package lars;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Class describing a transaction. Used to get total price of transaction for
 * receipt printer.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class Transaction
{
    private int transactionId;
    private Date date;
    private List<TransactionItem> transactionItems;

    public Transaction()
    {
        transactionItems = new ArrayList<TransactionItem>();
        this.transactionId = 0;
        this.date = new Date(System.currentTimeMillis());
    }

    public Transaction(List<TransactionItem> transactionItems,
            int transactionId, Date date)
    {
        this.transactionItems = transactionItems;
        this.transactionId = transactionId;
        this.date = date;
    }

    public Transaction(int transactionId, Date date)
    {
        transactionItems = new ArrayList<TransactionItem>();
        this.transactionId = transactionId;
        this.date = date;
    }

    @Override
    public String toString()
    {
        String rep = "Transaction[";

        boolean hasOne = false;
        for (TransactionItem item : transactionItems)
        {
            if (hasOne)
                rep += ";";
            rep += item;
            hasOne = true;
        }

        return rep + "]";
    }

    public void setTransactionId(int transactionId)
    {
        this.transactionId = transactionId;
    }

    public int getTransactionId()
    {
        return transactionId;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDate()
    {
        return date;
    }

    public void addTransactionItem(TransactionItem item)
    {
        transactionItems.add(item);
    }

    public void removeTransactionItem(TransactionItem item)
    {
        transactionItems.remove(item);
    }

    public void setTransactionItems(List<TransactionItem> transactionItems)
    {
        this.transactionItems = transactionItems;
    }

    public List<TransactionItem> getTransactionItems()
    {
        return transactionItems;
    }

    public int getTotalPrice()
    {
        int totalPrice = 0;

        for (TransactionItem i : transactionItems)
            totalPrice += i.getPrice();

        return totalPrice;
    }
}
