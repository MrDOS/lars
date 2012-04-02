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
    private String name;
    private String description;
    private int purchasePrice;
    private boolean isRentable;
    private int rentalPrice;
    private int rentalDuration;

    public ItemType(String name, String description, int purchasePrice,
            boolean isRentable, int rentalPrice, int rentalDuration)
    {
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.isRentable = isRentable;
        this.rentalPrice = rentalPrice;
        this.rentalDuration = rentalDuration;
    }

    public ItemType(String name, String description, int purchasePrice)
    {
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.isRentable = true;
        this.rentalPrice = 0;
        this.rentalDuration = 0;
    }

    public ItemType(String name, String description, int purchasePrice,
            int rentalPrice, int rentalDuration)
    {
        this.name = name;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.isRentable = true;
        this.rentalPrice = rentalPrice;
        this.rentalDuration = rentalDuration;
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
        return isRentable;
    }

    public void setRentable(boolean isRentable)
    {
        this.isRentable = isRentable;
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
