package lars;

/**
 * An item type defines the base prices and rental duration of a product. This
 * may be considered to be a broad product category, such as &ldquo;DVD&rdquo;.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-01
 * 
 */
public class ItemType
{
    /**
     * The expected maximum length of type names; to be used by text fields.
     */
    public static final int NAME_SIZE = 8;

    private int typeId = 0;
    private String name;
    private String description;
    private int purchasePrice;
    private boolean rentable = false;
    private int rentalPrice = 0;
    private int rentalDuration = 0;

    /**
     * Instantiate an item type.
     * 
     * @param typeId
     *            the database ID of the type
     * @param name
     *            the name of the type
     * @param description
     *            the description of the type
     * @param purchasePrice
     *            the base purchase price
     * @param rentable
     *            whether or not items of this type are rentable
     * @param rentalPrice
     *            the base rental price
     * @param rentalDuration
     *            the base rental duration
     */
    public ItemType(int typeId, String name, String description,
            int purchasePrice, boolean rentable, int rentalPrice,
            int rentalDuration)
    {
        this.typeId = typeId;
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.rentable = rentable;
        this.rentalPrice = rentalPrice;
        this.rentalDuration = rentalDuration;
    }

    /**
     * Instantiate a non-rentable item type.
     * 
     * @param name
     *            the name of the type
     * @param description
     *            the description of the type
     * @param purchasePrice
     *            the base purchase price
     */
    public ItemType(String name, String description, int purchasePrice)
    {
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
    }

    /**
     * Instantiate a rentable item type.
     * 
     * @param name
     *            the name of the type
     * @param description
     *            the description of the type
     * @param purchasePrice
     *            the base purchase price
     * @param rentalPrice
     *            the base rental price
     * @param rentalDuration
     *            the base rental duration
     */
    public ItemType(String name, String description, int purchasePrice,
            int rentalPrice, int rentalDuration)
    {
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.rentable = true;
        this.rentalPrice = rentalPrice;
        this.rentalDuration = rentalDuration;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof ItemType)
        {
            ItemType otherItemType = (ItemType) other;
            if (otherItemType.purchasePrice == this.purchasePrice
                    && otherItemType.rentable == this.rentable
                    && otherItemType.rentalPrice == this.rentalPrice
                    && otherItemType.rentalDuration == this.rentalDuration
                    && otherItemType.name.equals(this.name)
                    && otherItemType.description.equals(this.description))
                return true;
        }

        return false;
    }

    /**
     * Get the database ID of the type.
     * 
     * @return the database ID of the type
     */
    public int getTypeId()
    {
        return typeId;
    }

    /**
     * Set the database ID of the type.
     * 
     * @param typeId
     *            the database ID of the type
     */
    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }

    /**
     * Get the name of the type.
     * 
     * @return the name of the type
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the name of the type.
     * 
     * @param name
     *            the name of the type
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Get the description of the type.
     * 
     * @return the description of the type
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Set the description of the type.
     * 
     * @param description
     *            the description of the type
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Get the base purchase price.
     * 
     * @return the base purchase price
     */
    public int getPurchasePrice()
    {
        return purchasePrice;
    }

    /**
     * Set the base purchase price.
     * 
     * @param purchasePrice
     *            the base purchase price
     */
    public void setPurchasePrice(int purchasePrice)
    {
        this.purchasePrice = purchasePrice;
    }

    /**
     * Get whether or not items of this type are rentable.
     * 
     * @return whether or not items of this type are rentable
     */
    public boolean isRentable()
    {
        return rentable;
    }

    /**
     * Set whether or not items of this type are rentable.
     * 
     * @param rentable
     *            whether or not items of this type are rentable
     */
    public void setRentable(boolean rentable)
    {
        this.rentable = rentable;
    }

    /**
     * Get the base rental price.
     * 
     * @return the base rental price
     */
    public int getRentalPrice()
    {
        return rentalPrice;
    }

    /**
     * Set the base rental price.
     * 
     * @param rentalPrice
     *            the base rental price
     */
    public void setRentalPrice(int rentalPrice)
    {
        this.rentalPrice = rentalPrice;
    }

    /**
     * Get the base rental duration.
     * 
     * @return the base rental duration
     */
    public int getRentalDuration()
    {
        return rentalDuration;
    }

    /**
     * Set the base rental duration.
     * 
     * @param rentalDuration
     *            the base rental duration
     */
    public void setRentalDuration(int rentalDuration)
    {
        this.rentalDuration = rentalDuration;
    }
}
