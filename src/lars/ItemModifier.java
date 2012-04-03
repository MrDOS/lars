package lars;

/**
 * Data class for item modifier.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-02
 */
public class ItemModifier
{
    private int modifierId = 0;
    private int purchasePrice;
    private int rentalPrice;
    private int rentalDuration;

    public ItemModifier(int modifierId, int purchasePrice, int rentalPrice,
            int rentalDuration)
    {
        this.modifierId = 0;
        this.purchasePrice = purchasePrice;
        this.rentalPrice = rentalPrice;
        this.rentalDuration = rentalDuration;
    }

    public ItemModifier(int purchasePrice, int rentalPrice, int rentalDuration)
    {
        this.purchasePrice = purchasePrice;
        this.rentalPrice = rentalPrice;
        this.rentalDuration = rentalDuration;
    }

    @Override
    public String toString()
    {
        return "ItemModifier[purchasePrice=" + this.purchasePrice
                + ";rentalPrice=" + this.rentalPrice + ";rentalDuration="
                + this.rentalDuration + "]";
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof ItemModifier)
        {
            ItemModifier otherModifier = (ItemModifier) other;
            if (otherModifier.purchasePrice == this.purchasePrice
                    && otherModifier.rentalPrice == this.rentalPrice
                    && otherModifier.rentalDuration == this.rentalDuration)
                return true;
        }

        return false;
    }

    public int getModifierId()
    {
        return modifierId;
    }

    public void setModifierId(int modifierId)
    {
        this.modifierId = modifierId;
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
