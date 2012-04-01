package lars;

/**
 * Data class for the object type item type.
 * 
 * @author Jeremy Wheaton
 * @version April 1, 2012
 * 
 */
public class ItemType
{
    private String name;
    private String description;
    private int price;
    private int rentalDuration;
    private boolean isRentable;

    public ItemType(String name, String description, int price,
            int rentalDuration, boolean isRentable)
    {
        this.name = name;
        this.description = description;
        this.price = price;
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

    public double getPrice()
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
}
