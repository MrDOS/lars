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
    private boolean isRented = false;

    public TransactionItem(Item item, boolean isRented)
    {
        this.item = item;
        if (this.item.getType().isRentable())
            this.isRented = isRented;
    }

    @Override
    public String toString()
    {
        return "TransactionItem[item=" + this.item + ";isRented="
                + this.isRented + "]";
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof TransactionItem)
        {
            TransactionItem otherItem = (TransactionItem) other;
            if (otherItem.isRented == this.isRented
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
        return isRented;
    }

    public void setRented(boolean isRented)
    {
        if (this.item.getType().isRentable())
            this.isRented = isRented;
    }

    public int getPrice()
    {
        if (this.isRented)
            return this.item.getRentalPrice();
        else
            return this.item.getPurchasePrice();
    }
}
