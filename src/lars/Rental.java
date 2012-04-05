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
public class Rental
{
    private int rentalId;
    private Date date;
    private List<RentalItem> rentalItems;

    public Rental()
    {
        rentalItems = new ArrayList<RentalItem>();
        this.rentalId = 0;
        this.date = new Date(System.currentTimeMillis());
    }

    public Rental(List<RentalItem> transactionItems, int rentalId, Date date)
    {
        this.rentalItems = transactionItems;
        this.rentalId = rentalId;
        this.date = date;
    }

    public Rental(int rentalId, Date date)
    {
        rentalItems = new ArrayList<RentalItem>();
        this.rentalId = rentalId;
        this.date = date;
    }

    @Override
    public String toString()
    {
        String rep = "Rental[";

        boolean hasOne = false;
        for (RentalItem item : rentalItems)
        {
            if (hasOne)
                rep += ";";
            rep += item;
            hasOne = true;
        }

        return rep + "]";
    }

    public void setRentalId(int rentalId)
    {
        this.rentalId = rentalId;
    }

    public int getRentalId()
    {
        return rentalId;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Date getDate()
    {
        return date;
    }

    public void addRentalItem(RentalItem item)
    {
        rentalItems.add(item);
    }

    public void removeRentalItem(RentalItem item)
    {
        rentalItems.remove(item);
    }

    public void setRentalItems(List<RentalItem> transactionItems)
    {
        this.rentalItems = transactionItems;
    }

    public List<RentalItem> getRentalItems()
    {
        return rentalItems;
    }

    public int getTotalPrice()
    {
        int totalPrice = 0;

        for (RentalItem i : rentalItems)
            totalPrice += i.getPrice();

        return totalPrice;
    }
}
