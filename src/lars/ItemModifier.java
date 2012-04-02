package lars;

/**
 * Data class for item modifier.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-01
 */
public class ItemModifier
{
    private int purchasePrice;
    private int rentalPrice;
    private int rentalDuration;

    public ItemModifier(int purchasePrice, int rentalPrice, int rentalDuration)
    {
        this.purchasePrice = purchasePrice;
        this.rentalPrice = rentalPrice;
        this.rentalDuration = rentalDuration;
    }

    public int getPurchasePrice()
    {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice)
    {
        this.purchasePrice = purchasePrice;
    }

    public int getRentalPrice()
    {
        return rentalPrice;
    }

    public void setRentalPrice(int rentalPrice)
    {
        this.rentalPrice = rentalPrice;
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
