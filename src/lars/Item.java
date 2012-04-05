package lars;

import java.util.ArrayList;
import java.util.List;

/**
 * Class describing a physical item, be it for rent or sale. Price and rental
 * duration of item are calculated based on the item type and applied modifiers.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-02
 */
public class Item
{
    /**
     * The expected maximum length of SKUs; to be used by text fields.
     */
    public static final int SKU_LENGTH = 8;
    /**
     * The expected maximum length of quantities; to be used by text fields.
     */
    public static final int QUANTITY_LENTH = 2;

    private List<ItemModifier> modifiers;
    private ItemType type;
    private int sku;
    private String description;
    private int quantity;

    /**
     * Instantiate an item.
     * 
     * @param type
     *            the item type
     * @param sku
     *            the item SKU
     * @param description
     *            the item description
     * @param quantity
     *            the total stock count
     */
    public Item(ItemType type, int sku, String description, int quantity)
    {
        this.modifiers = new ArrayList<ItemModifier>();
        this.type = type;
        this.sku = sku;
        this.description = description;
        this.quantity = quantity;
    }

    /**
     * Instantiate an item with modifiers.
     * 
     * @param modifiers
     *            the item modifiers
     * @param type
     *            the item type
     * @param sku
     *            the item SKU
     * @param description
     *            the item description
     * @param quantity
     *            the total stock count
     */
    public Item(List<ItemModifier> modifiers, ItemType type, int sku,
            String description, int quantity)
    {
        this.modifiers = modifiers;
        this.type = type;
        this.sku = sku;
        this.description = description;
        this.quantity = quantity;
    }

    @Override
    public String toString()
    {
        String rep = "Item[type=" + this.type + ";sku=" + this.sku
                + ";description=" + this.description + ";quantity="
                + this.quantity + ";modifiers=[";

        boolean hasOne = false;
        for (ItemModifier modifier : this.modifiers)
        {
            if (hasOne)
                rep += ";";
            rep += modifier;
            hasOne = true;
        }
        rep += "]]";

        return rep;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof Item)
        {
            Item otherItem = (Item) other;
            if (otherItem.sku == this.sku
                    && otherItem.quantity == this.quantity
                    && otherItem.description.equals(this.description)
                    && otherItem.type.equals(this.type)
                    && otherItem.modifiers.equals(this.modifiers))
                return true;
        }

        return false;
    }

    /**
     * Get all applied item modifiers.
     * 
     * @return all applied item modifiers
     */
    public List<ItemModifier> getModifiers()
    {
        return modifiers;
    }

    /**
     * Apply a set of item modifiers.
     * 
     * @param modifiers
     *            a set of item modifiers
     */
    public void setModifiers(List<ItemModifier> modifiers)
    {
        this.modifiers = modifiers;
    }

    /**
     * Apply an individual modifier.
     * 
     * @param modifier
     *            a modifier to apply
     */
    public void addModifier(ItemModifier modifier)
    {
        this.modifiers.add(modifier);
    }

    /**
     * Remove an individual modifier.
     * 
     * @param modifier
     *            a modifier to remove
     */
    public void removeModifier(ItemModifier modifier)
    {
        this.modifiers.remove(modifier);
    }

    /**
     * Get the item type.
     * 
     * @return the item type
     */
    public ItemType getType()
    {
        return type;
    }

    /**
     * Set the item type.
     * 
     * @param type
     *            the item type
     */
    public void setType(ItemType type)
    {
        this.type = type;
    }

    /**
     * Get the item SKU.
     * 
     * @return the item SKU
     */
    public int getSku()
    {
        return sku;
    }

    /**
     * Set the item SKU.
     * 
     * @param sku
     *            the item SKU
     */
    public void setSku(int sku)
    {
        this.sku = sku;
    }

    /**
     * Get the item description.
     * 
     * @return the item description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Set the item description.
     * 
     * @param description
     *            the item description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Get the total stock count.
     * 
     * @return the total stock count
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * Set the total stock count.
     * 
     * @param quantity
     *            the total stock count
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /**
     * Get the purchase price, based on the item type and applied modifiers.
     * 
     * @return the purchase price
     */
    public int getPurchasePrice()
    {
        int totalPrice = this.type.getPurchasePrice();

        for (ItemModifier modifier : modifiers)
            totalPrice += modifier.getPurchasePrice();

        return totalPrice;
    }

    /**
     * Get the rental price, based on the item type and applied modifiers.
     * 
     * @return the rental price
     */
    public int getRentalPrice()
    {
        int totalPrice = this.type.getRentalPrice();

        for (ItemModifier modifier : modifiers)
            totalPrice += modifier.getRentalPrice();

        return totalPrice;
    }

    /**
     * Get the rental duration, based on the item type and applied modifiers.
     * 
     * @return the rental duration
     */
    public int getRentalDuration()
    {
        int totalDuration = this.type.getRentalDuration();

        for (ItemModifier modifier : modifiers)
            totalDuration += modifier.getRentalDuration();

        return totalDuration;
    }
}
