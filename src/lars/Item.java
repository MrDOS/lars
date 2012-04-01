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
    private int totalRentalPrice;
    private int totalBuyPrice;
    private int totalDuration;

    public Item(ItemType newType, ItemDescription newDescription)
    {
        type = newType;
        description = newDescription;
        totalRentalPrice = 0;
        totalBuyPrice = 0;
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

    public int getRentalPrice()
    {
        totalRentalPrice += type.getRentalPrice();

        for (int i = 0; i < modifiers.size(); i++)
        {
            totalRentalPrice += modifiers.get(i).getRentalPrice();
        }

        return totalRentalPrice;
    }
    
    public int getBuyPrice()
    {
        totalBuyPrice += type.getBuyPrice();

        for (int i = 0; i < modifiers.size(); i++)
        {
            totalBuyPrice += modifiers.get(i).getBuyPrice();
        }

        return totalBuyPrice;
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
