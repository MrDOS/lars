package lars;

import java.util.List;

/**
 * Class describing Item. Price and RentalDuration of item are calculated based
 * on modifiers that have been added to the Item.
 * 
 * @author Jeremy Wheaton
 * @version April 1, 2012
 */
public class Item
{
    private List<ItemModifier> modifiers;
    private ItemType type;
    private ItemDescription description;
    private int totalPrice;
    private int totalDuration;

    public Item(ItemType newType, ItemDescription newDescription)
    {
        type = newType;
        description = newDescription;
        totalPrice = 0;
        totalDuration = 0;
    }

    public void addModifier(ItemModifier mod)
    {
        modifiers.add(mod);
    }

    public void removeModifier(ItemModifier mod)
    {
        modifiers.remove(mod);
    }

    public List<ItemModifier> getModifiers()
    {
        return modifiers;
    }

    public int getPrice()
    {
        totalPrice += type.getPrice();

        for (int i = 0; i < modifiers.size(); i++)
        {
            totalPrice += modifiers.get(i).getPrice();
        }

        return totalPrice;
    }

    public int getSKU()
    {
        return description.getSKU();
    }

    public String getDescription()
    {
        return description.getDescription();
    }

    public int getQuantity()
    {
        return description.getQuantity();
    }

    public int getDuration()
    {
        totalDuration += type.getRentalDuration();

        for (int i = 0; i < modifiers.size(); i++)
        {
            totalDuration += modifiers.get(i).getRentalDuration();
        }

        return totalDuration;
    }
}
