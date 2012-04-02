package lars.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lars.Item;
import lars.ItemModifier;
import lars.ItemType;

/**
 * Provide database access to items.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-02
 */
public class ItemDatabase
{
    public static Item getItemBySku(int sku) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT ItemType.name, ItemType.description, ItemType.purchasePrice, ItemType.rentable, ItemType.rentalPrice, ItemType.rentalDuration, Item.sku, Item.description, Item.quantity FROM Item LEFT JOIN ItemType ON ItemType.itemTypeId = Item.itemType WHERE sku = ?");
        ps.setInt(1, sku);

        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return new Item(
                    new ItemType(rs.getString(1), rs.getString(2),
                            rs.getInt(3), rs.getBoolean(4), rs.getInt(5),
                            rs.getInt(6)), rs.getInt(7), rs.getString(8),
                    rs.getInt(9));
        else
            throw new SQLException("No matching item.");
    }

    public static List<Item> getItems()
    {
        // TODO: Stub method.
        return null;
    }

    public static Item insertItem(Item item) throws SQLException
    {
        // TODO: Stub method.
        return null;
    }

    public static void updateItem(Item item) throws SQLException
    {
        // TODO: Stub method.
        return;
    }

    public static void deleteItem(Item item) throws SQLException
    {
        // TODO: Stub method.
        return;
    }

    public static ItemModifier getItemModifierById(int id)
    {
        // TODO: Stub method.
        return null;
    }

    public static List<ItemModifier> getItemModifiers()
    {
        // TODO: Stub method.
        return null;
    }

    public static ItemModifier insertItemModifier(ItemModifier modifier)
    {
        // TODO: Stub method.
        return null;
    }

    public static void updateItemModifier(ItemModifier modifier)
    {
        // TODO: Stub method.
        return;
    }

    public static void deleteItemModifier(ItemModifier modifier)
    {
        // TODO: Stub method.
        return;
    }

    public static ItemType getItemTypeById(int id)
    {
        // TODO: Stub method.
        return null;
    }

    public static List<ItemType> getItemTypes()
    {
        // TODO: Stub method.
        return null;
    }

    public static ItemType insertItemType(ItemType type)
    {
        // TODO: Stub method.
        return null;
    }

    public static void updateItemType(ItemType type)
    {
        // TODO: Stub method.
        return;
    }

    public static void deleteItemType(ItemType type)
    {
        // TODO: Stub method.
        return;
    }
}
