package lars;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Class describing a transaction. Used to get total price of transaction for
 * receipt printer.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version April 1, 2012
 */
public class Transaction
{
    private Date date;
    private int transactionID;
    private int totalPrice;
    private List<TransactionItem> transactionItems;

    public Transaction()
    {
        transactionItems = new ArrayList<TransactionItem>();
        totalPrice = 0;
        setDate(new Date(System.currentTimeMillis()));
        setTransactionID(0); 
    }

    public int getTotalPrice()
    {
        for (TransactionItem i : transactionItems)
        {
            totalPrice += i.getPrice();
        }

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

    public void setTransactionID(int transactionID)
    {
        this.transactionID = transactionID; 
    }

    public int getTransactionID()
    {
        return transactionID;
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
