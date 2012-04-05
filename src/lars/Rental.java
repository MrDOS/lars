package lars;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Class describing a rental transaction. Groups all involved items.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class Rental
{
    private int rentalId = 0;
    private Date date;
    private List<RentalItem> rentalItems;

    /**
     * Instantiate a rental at the current time.
     */
    public Rental()
    {
        rentalItems = new ArrayList<RentalItem>();
        this.date = new Date(System.currentTimeMillis());
    }

    /**
     * Instantiate a rental with items from a given in time.
     * 
     * @param rentalItems
     *            the items involved in the rental
     * @param rentalId
     *            the database ID of the rental
     * @param date
     *            the date of the rental
     */
    public Rental(int rentalId, List<RentalItem> rentalItems, Date date)
    {
        this.rentalItems = rentalItems;
        this.rentalId = rentalId;
        this.date = date;
    }

    /**
     * Instantiate a rental from a given in time.
     * 
     * @param rentalId
     *            the database ID of the rental
     * @param date
     *            the date of the rental
     */
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

    /**
     * Set the database ID of the rental.
     * 
     * @param rentalId
     *            the database ID of the rental
     */
    public void setRentalId(int rentalId)
    {
        this.rentalId = rentalId;
    }

    /**
     * Get the database ID of the rental.
     * 
     * @return the database ID of the rental
     */
    public int getRentalId()
    {
        return rentalId;
    }

    /**
     * Set the date of the rental.
     * 
     * @param date
     *            the date of the rental
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * Get the date of the rental.
     * 
     * @return the date of the rental
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Set the items involved in the rental.
     * 
     * @param rentalItems
     *            the items involved in the rental
     */
    public void setRentalItems(List<RentalItem> rentalItems)
    {
        this.rentalItems = rentalItems;
    }

    /**
     * Add an item to the rental.
     * 
     * @param item
     *            the item to add to the rental
     */
    public void addRentalItem(RentalItem item)
    {
        rentalItems.add(item);
    }

    /**
     * Remove an item from the rental.
     * 
     * @param item
     *            the item to remove from the rental
     */
    public void removeRentalItem(RentalItem item)
    {
        rentalItems.remove(item);
    }

    /**
     * Get the items involved in the rental.
     * 
     * @return the items involved in the rental
     */
    public List<RentalItem> getRentalItems()
    {
        return rentalItems;
    }

    /**
     * Determine the total price of the rental based on the sum of the items.
     * 
     * @return the total price of the rental based on the sum of the items
     */
    public int getTotalPrice()
    {
        int totalPrice = 0;

        for (RentalItem i : rentalItems)
            totalPrice += i.getPrice();

        return totalPrice;
    }
}
