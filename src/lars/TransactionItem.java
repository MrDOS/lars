package lars;

/**
 * Class describing a single transaction item.
 * 
 * @author Jeremy Wheaton
 * @version April 1, 2012
 */
public class TransactionItem
{
    private int quantity;
    private int price;

    public void getItem(int SKUScanned)
    {
        // TODO: Need to find item based on SKU
    }

    public TransactionItem(Item item, int quantity, boolean isRented)
    {
        if (isRented)
            this.price = item.getRentalPrice();
        else
            this.price = item.getBuyPrice();
        this.quantity = quantity;
    }

    public int getPrice()
    {
        return this.price;
    }

    public void changeQuantity(int newQuantity)
    {
        this.quantity = newQuantity;
    }

    public int getQuantity()
    {
        return this.quantity;
    }

}
