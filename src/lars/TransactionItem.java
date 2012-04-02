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
    private int quantity;
    private boolean isRented = false;

    public TransactionItem(Item item, int quantity, boolean isRented)
    {
        this.item = item;
        this.quantity = quantity;
        if (this.item.getType().isRentable())
            this.isRented = isRented;
    }

    @Override
    public String toString()
    {
        return "TransactionItem[item=" + this.item + ";quantity="
                + this.quantity + ";isRented=" + this.isRented + "]";
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof TransactionItem)
        {
            TransactionItem otherItem = (TransactionItem) other;
            if (otherItem.quantity == this.quantity
                    && otherItem.isRented == this.isRented
                    && otherItem.item.equals(this.item))
                return true;
        }

        return false;
    }

    public Item getItem()
    {
        return item;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
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
            return this.quantity * this.item.getRentalPrice();
        else
            return this.quantity * this.item.getPurchasePrice();
    }
}
