package lars;

import java.sql.Date;

/**
 * An item from a rental, describing its rental state and due date. Due date is
 * expected to be consistent with that given by the item, but doesn't
 * necessarily have to be (perhaps due to modification by a manager).
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-02
 */
public class RentalItem
{
    private static final int MILLIS_PER_DAY = 1000 * 60 * 60 * 24;

    private Item item;
    private boolean rented = false;
    private Date due = null;
    private boolean returned = false;

    /**
     * Instantiate a rental item.
     * 
     * @param item
     *            the item
     * @param rented
     *            whether or not the item is rented
     * @param due
     *            the item due date, if it is rented
     * @param returned
     *            whether or not the item has been returned
     */
    public RentalItem(Item item, boolean rented, Date due, boolean returned)
    {
        this.item = item;
        if (this.item.getType().isRentable())
        {
            this.rented = rented;
            this.due = due;
            this.returned = returned;
        }
    }

    /**
     * Instantiate a rental item.
     * 
     * @param item
     *            the item
     */
    public RentalItem(Item item)
    {
        this.item = item;
    }

    @Override
    public String toString()
    {
        return "RentalItem[item=" + this.item + ";rented=" + this.rented
                + ";due=" + this.due + "returned=" + this.returned + "]";
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof RentalItem)
        {
            RentalItem otherItem = (RentalItem) other;
            if (otherItem.rented == this.rented
                    && otherItem.returned == this.returned
                    && otherItem.item.equals(this.item)
                    && otherItem.due.equals(this.due))
                return true;
        }

        return false;
    }

    /**
     * Get the associated {@link Item}.
     * 
     * @return the associated {@link Item}
     */
    public Item getItem()
    {
        return item;
    }

    /**
     * Get whether or not the item is rentable.
     * 
     * @return whether or not the item is rentable
     */
    public boolean isRentable()
    {
        return item.getType().isRentable();
    }

    /**
     * Get whether or not the item is rented.
     * 
     * @return whether or not the item is rented
     */
    public boolean isRented()
    {
        return rented;
    }

    /**
     * Set whether or not the item is rented.
     * 
     * @param rented
     *            whether or not the item is rented
     */
    public void setRented(boolean rented)
    {
        if (this.item.getType().isRentable())
            this.rented = rented;
    }

    /**
     * Reset the due date of the item based on the item type and modifiers and
     * the current time.
     */
    public void calculateDueDate()
    {
        this.due = new Date(((System.currentTimeMillis() + MILLIS_PER_DAY
                * this.item.getRentalDuration()) / MILLIS_PER_DAY)
                * MILLIS_PER_DAY);
    }

    /**
     * Get the item due date. Returns null when the item is not rented.
     * 
     * @return the item due date
     */
    public Date getDueDate()
    {
        if (!this.rented)
            return null;
        if (due == null)
            this.calculateDueDate();

        return due;
    }

    /**
     * Manually set the item due date. Has no effect if the item is not rented.
     * 
     * @param due
     *            the item due date
     */
    public void setDueDate(Date due)
    {
        if (this.rented)
            this.due = due;
    }

    /**
     * Get whether or not the item has been returned.
     * 
     * @return whether or not the item has been returned
     */
    public boolean isReturned()
    {
        return returned;
    }

    /**
     * Set whether or not the item has been returned. Has no effect if the item
     * is not rented.
     * 
     * @param returned
     *            whether or not the item has been returned
     */
    public void setReturned(boolean returned)
    {
        if (this.rented)
            this.returned = returned;
    }

    /**
     * Get the price of the item, dependent on whether it's rented or not.
     * 
     * @return the price of the item
     */
    public int getPrice()
    {
        if (this.rented)
            return this.item.getRentalPrice();
        else
            return this.item.getPurchasePrice();
    }
}
