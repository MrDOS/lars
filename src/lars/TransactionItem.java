package lars;

/**
 * Class describing a single transaction item.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-02
 */
public class TransactionItem
{
    private Item item;
    private boolean rented = false;

    public TransactionItem(Item item, boolean rented)
    {
        this.item = item;
        if (this.item.getType().isRentable())
            this.rented = rented;
    }

    @Override
    public String toString()
    {
        return "TransactionItem[item=" + this.item + ";isRented="
                + this.rented + "]";
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof TransactionItem)
        {
            TransactionItem otherItem = (TransactionItem) other;
            if (otherItem.rented == this.rented
                    && otherItem.item.equals(this.item))
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

    public int getPrice()
    {
        if (this.rented)
            return this.item.getRentalPrice();
        else
            return this.item.getPurchasePrice();
    }
}
