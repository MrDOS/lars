package lars;

/**
 * Data class for item modifier.
 * 
 * @author Jeremy Wheaton
 * @version April 1, 2012
 */
public class ItemModifier
{
    private int rentalPrice;
    private int buyPrice;
    private int rentalDuration;

    public ItemModifier(int rentalPrice, int buyPrice, int rentalDuration)
    {
        this.rentalPrice = rentalPrice;
        this.setBuyPrice(buyPrice);
        this.rentalDuration = rentalDuration;
    }

    public int getRentalPrice()
    {
        return rentalPrice;
    }

    public void setRentalPrice(int price)
    {
        this.rentalPrice = price;
    }

    public int getRentalDuration()
    {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration)
    {
        this.rentalDuration = rentalDuration;
    }

    public void setBuyPrice(int buyPrice)
    {
        this.buyPrice = buyPrice;
    }

    public int getBuyPrice()
    {
        return buyPrice;
    }

}
