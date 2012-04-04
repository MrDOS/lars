package lars;

/**
 * Data class for the object type item type.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-01
 * 
 */
public class ItemType
{
    public static final int NAME_SIZE = 8;
    private int typeId = 0;
    private String name;
    private String description;
    private int purchasePrice;
    private boolean rentable = false;
    private int rentalPrice = 0;
    private int rentalDuration = 0;

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

    public ItemType(String name, String description, int purchasePrice)
    {
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
    }

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

    public int getTypeId()
    {
        return typeId;
    }

    public void setTypeId(int typeId)
    {
        this.typeId = typeId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getPurchasePrice()
    {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice)
    {
        this.purchasePrice = purchasePrice;
    }

    public boolean isRentable()
    {
        return rentable;
    }

    public void setRentable(boolean rentable)
    {
        this.rentable = rentable;
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
