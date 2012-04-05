package lars;

import java.sql.Date;

/**
 * Class describing a single transaction item.
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

    public RentalItem(Item item, boolean rented)
    {
        this.item = item;
        if (this.item.getType().isRentable())
            this.rented = rented;
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

    public Item getItem()
    {
        return item;
    }

    public boolean isRentable()
    {
        return item.getType().isRentable();
    }

    public boolean isRented()
    {
        return rented;
    }

    public void setRented(boolean rented)
    {
        if (this.item.getType().isRentable())
            this.rented = rented;
    }

    public void calculateDueDate()
    {
        int totalDuration = this.item.getType().getRentalDuration();
        for (ItemModifier modifier : this.item.getModifiers())
            totalDuration += modifier.getRentalDuration();

        this.due = new Date(((System.currentTimeMillis() + MILLIS_PER_DAY
                * totalDuration) / MILLIS_PER_DAY)
                * MILLIS_PER_DAY);
    }

    public Date getDueDate()
    {
        if (due == null)
            this.calculateDueDate();

        return due;
    }

    public void setDueDate(Date due)
    {
        if (this.item.getType().isRentable())
            this.due = due;
    }

    public boolean isReturned()
    {
        return returned;
    }

    public void setReturned(boolean returned)
    {
        if (this.item.getType().isRentable())
            this.returned = returned;
    }

    public int getPrice()
    {
        if (this.rented)
            return this.item.getRentalPrice();
        else
            return this.item.getPurchasePrice();
    }
}
