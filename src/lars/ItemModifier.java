package lars;

/**
 * An item modifier. Modifiers alter price and rental duration of items on a
 * per-item basis; this is useful for things like new releases and sales.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class ItemModifier
{
    /**
     * The expected maximum length of item names; to be used by text fields.
     * Purchase price, rental price, and rental duration are expected as signed
     * values; the sum of these values from all modifiers plus the base values
     * from the item type constitute the values applied to the rental.
     */
    public static final int NAME_SIZE = 8;

    private int modifierId = 0;
    private String name;
    private int purchasePrice;
    private int rentalPrice;
    private int rentalDuration;

    /**
     * Instantiate a modifier.
     * 
     * @param modifierId
     *            the database ID of the modifier
     * @param name
     *            the name of the modifier
     * @param purchasePrice
     *            the purchase price delta
     * @param rentalPrice
     *            the rental price delta
     * @param rentalDuration
     *            the rental duration delta
     */
    public ItemModifier(int modifierId, String name, int purchasePrice,
            int rentalPrice, int rentalDuration)
    {
        this.modifierId = modifierId;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.rentalPrice = rentalPrice;
        this.rentalDuration = rentalDuration;
    }

    /**
     * Instantiate a modifier.
     * 
     * @param name
     *            the name of the modifier
     * @param purchasePrice
     *            the purchase price delta
     * @param rentalPrice
     *            the rental price delta
     * @param rentalDuration
     *            the rental duration delta
     */
    public ItemModifier(String name, int purchasePrice, int rentalPrice,
            int rentalDuration)
    {
        this.modifierId = 0;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.rentalPrice = rentalPrice;
        this.rentalDuration = rentalDuration;
    }

    @Override
    public String toString()
    {
        return "ItemModifier[modifierId=" + this.modifierId + ";name="
                + this.name + ";purchasePrice=" + this.purchasePrice
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
                    && otherModifier.rentalDuration == this.rentalDuration
                    && otherModifier.name.equals(this.name))
                return true;
        }

        return false;
    }

    /**
     * Get the database ID of the modifier.
     * 
     * @return the database ID of the modifier
     */
    public int getModifierId()
    {
        return modifierId;
    }

    /**
     * Set the database ID of the modifier.
     * 
     * @param modifierId
     *            the database ID of the modifier
     */
    public void setModifierId(int modifierId)
    {
        this.modifierId = modifierId;
    }

    /**
     * Get the name of the modifier.
     * 
     * @return the name of the modifier
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the name of the modifier.
     * 
     * @param name
     *            the name of the modifier
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Get the purchase price delta.
     * 
     * @return the purchase price delta
     */
    public int getPurchasePrice()
    {
        return purchasePrice;
    }

    /**
     * Set the purchase price delta.
     * 
     * @param purchasePrice
     *            the purchase price delta
     */
    public void setPurchasePrice(int purchasePrice)
    {
        this.purchasePrice = purchasePrice;
    }

    /**
     * Get rental price delta.
     * 
     * @return rental price delta
     */
    public int getRentalPrice()
    {
        return rentalPrice;
    }

    /**
     * Set the rental price delta.
     * 
     * @param rentalPrice
     *            rental price delta
     */
    public void setRentalPrice(int rentalPrice)
    {
        this.rentalPrice = rentalPrice;
    }

    /**
     * Get the rental duration delta.
     * 
     * @return the rental duration delta
     */
    public int getRentalDuration()
    {
        return rentalDuration;
    }

    /**
     * Set the rental duration delta.
     * 
     * @param rentalDuration
     *            the rental duration delta
     */
    public void setRentalDuration(int rentalDuration)
    {
        this.rentalDuration = rentalDuration;
    }
}
