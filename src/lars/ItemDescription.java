package lars;

/**
 * Data class for item description.
 * 
 * @author Jeremy Wheaton, 100105823
 * @version April 1, 2012
 */
public class ItemDescription
{
    private int SKU;
    private String description;
    private int quantity;

    public ItemDescription(int newSKU, String description, int quantity)
    {
        SKU = newSKU;
        this.description = description;
        this.quantity = quantity;
    }

    public int getSKU()
    {
        return SKU;
    }

    public void setSKU(int sKU)
    {
        SKU = sKU;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

}
