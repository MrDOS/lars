package lars;

import java.util.List;

/**
 * Class describing Item. Price and RentalDuration of item are calculated based
 * on modifiers that have been added to the Item.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-02
 */
public class Item
{
    private List<ItemModifier> modifiers;
    private ItemType type;
    private int sku;
    private String description;
    private int quantity;

    public Item(ItemType type, int sku, String description, int quantity)
    {
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

    public List<ItemModifier> getModifiers()
    {
        return modifiers;
    }

    public void setModifiers(List<ItemModifier> modifiers)
    {
        this.modifiers = modifiers;
    }

    public void addModifier(ItemModifier modifier)
    {
        this.modifiers.add(modifier);
    }

    public ItemType getType()
    {
        return type;
    }

    public void setType(ItemType type)
    {
        this.type = type;
    }

    public int getSku()
    {
        return sku;
    }

    public void setSku(int sku)
    {
        this.sku = sku;
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

    public int getPurchasePrice()
    {
        int totalPrice = this.type.getPurchasePrice();

        for (ItemModifier modifier : modifiers)
            totalPrice += modifier.getPurchasePrice();

        return totalPrice;
    }

    public int getRentalPrice()
    {
        int totalPrice = this.type.getRentalPrice();

        for (ItemModifier modifier : modifiers)
            totalPrice += modifier.getRentalPrice();

        return totalPrice;
    }

    public int getRentalDuration()
    {
        int totalDuration = this.type.getRentalDuration();

        for (ItemModifier modifier : modifiers)
            totalDuration += modifier.getRentalDuration();

        return totalDuration;
    }
}
