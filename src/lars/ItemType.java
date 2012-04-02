package lars;

/**
 * Data class for the object type item type.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version April 1, 2012
 * 
 */
public class ItemType
{
    private String name;
    private String description;
    private int rentalPrice;
    private int buyPrice;
    private int rentalDuration;
    private boolean isRentable;

    public ItemType(String name, String description, int rentalPrice,
            int buyPrice, int rentalDuration, boolean isRentable)
    {
        this.name = name;
        this.description = description;
        this.rentalPrice = rentalPrice;
        this.setBuyPrice(buyPrice);
        this.rentalDuration = rentalDuration;
        this.isRentable = isRentable;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getRentalPrice()
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

    public boolean isRentable()
    {
        return isRentable;
    }

    public void setRentable(boolean isRentable)
    {
        this.isRentable = isRentable;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
