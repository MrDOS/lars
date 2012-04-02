package lars;

/**
 * Class describing a single transaction item.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-01
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
