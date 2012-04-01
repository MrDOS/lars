package lars;

/**
 * Data class for item modifier.
 * 
 * @author Jeremy Wheaton
 * @version April 1, 2012
 */
public class ItemModifier
{
    private int price;
    private int rentalDuration;

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public int getRentalDuration()
    {
        return rentalDuration;
    }

    public void setRentalDuration(int rentalDuration)
    {
        this.rentalDuration = rentalDuration;
    }

}
