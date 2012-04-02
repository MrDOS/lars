package lars;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Class describing a transaction. Used to get total price of transaction for
 * receipt printer.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version 2012-04-01
 */
public class Transaction
{
    private Date date;
    private int transactionId;
    private List<TransactionItem> transactionItems;

    public Transaction()
    {
        transactionItems = new ArrayList<TransactionItem>();
        setDate(new Date(System.currentTimeMillis()));
        this.transactionId = 0;
    }

    public int getTotalPrice()
    {
        int totalPrice = 0;

        for (TransactionItem i : transactionItems)
            totalPrice += i.getPrice();

        return totalPrice;
    }

    public void addTransactionItem(TransactionItem item)
    {
        transactionItems.add(item);
    }

    public void removeTransactionItem(TransactionItem item)
    {
        transactionItems.remove(item);
    }

    public List<TransactionItem> getTransactionItems()
    {
        return transactionItems;
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
}
