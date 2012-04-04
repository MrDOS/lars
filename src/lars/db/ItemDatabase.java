package lars.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lars.Item;
import lars.ItemModifier;
import lars.ItemType;

/**
 * Provide database access to items.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class ItemDatabase
{
    public static Item getItemBySku(int sku) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT typeId, sku, description, quantity FROM Item WHERE sku = ?");
        ps.setInt(1, sku);

        ResultSet rs = ps.executeQuery();
        if (rs.next())
        {
            Item item = new Item(getItemTypeById(rs.getInt(1)), rs.getInt(2),
                    rs.getString(3), rs.getInt(4));
            item.setModifiers(getItemModifiersByItem(item));
            return item;
        }
        else
            throw new SQLException("No matching item.");
    }

    public static List<Item> getItems() throws SQLException
    {
        List<Item> items = new ArrayList<Item>();
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT typeId, sku, description, quantity FROM Item");

        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            Item item = new Item(getItemTypeById(rs.getInt(1)), rs.getInt(2),
                    rs.getString(3), rs.getInt(4));
            item.setModifiers(getItemModifiersByItem(item));
            items.add(item);
        }

        return items;
    }

    public static void insertItem(Item item) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "INSERT INTO Item(typeId, sku, description, quantity) VALUES(?, ?, ?, ?)");
        ps.setInt(1, item.getType().getTypeId());
        ps.setInt(2, item.getSku());
        ps.setString(3, item.getDescription());
        ps.setInt(4, item.getQuantity());

        ps.executeUpdate();

        applyItemModifiers(item);
    }

    public static void updateItem(Item item, int oldSku) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "UPDATE Item SET typeId = ?, sku = ?, description = ?, quantity = ? WHERE sku = ?");
        ps.setInt(1, item.getType().getTypeId());
        ps.setInt(2, item.getSku());
        ps.setString(3, item.getDescription());
        ps.setInt(4, item.getQuantity());
        ps.setInt(5, oldSku);

        ps.executeUpdate();

        clearItemModifiers(item);
        applyItemModifiers(item);
    }

    public static void updateItem(Item item) throws SQLException
    {
        updateItem(item, item.getSku());
    }

    public static void deleteItem(Item item) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement("DELETE FROM Item WHERE sku = ?");
        ps.setInt(1, item.getSku());

        ps.executeUpdate();

        clearItemModifiers(item);
    }

    private static List<ItemModifier> getItemModifiersByItem(Item item)
            throws SQLException
    {
        List<ItemModifier> modifiers = new ArrayList<ItemModifier>();
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT ItemModifier.modifierId, ItemModifier.name, ItemModifier.purchasePrice, ItemModifier.rentalPrice, ItemModifier.rentalDuration FROM ItemModifiers LEFT JOIN ItemModifier ON ItemModifiers.modifierId = ItemModifier.modifierId WHERE ItemModifiers.sku = ?");
        ps.setInt(1, item.getSku());

        ResultSet rs = ps.executeQuery();
        while (rs.next())
            modifiers.add(new ItemModifier(rs.getInt(1), rs.getString(2), rs
                    .getInt(3), rs.getInt(4), rs.getInt(5)));

        return modifiers;
    }

    private static void clearItemModifiers(Item item) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement("DELETE FROM ItemModifiers WHERE sku = ?");
        ps.setInt(1, item.getSku());

        ps.executeUpdate();
    }

    private static void applyItemModifiers(Item item) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "INSERT INTO ItemModifiers(sku, modifierId) VALUES (?, ?)");
        for (ItemModifier modifier : item.getModifiers())
        {
            ps.setInt(1, item.getSku());
            ps.setInt(2, modifier.getModifierId());
            ps.executeUpdate();
        }
    }

    public static ItemModifier getItemModifierById(int modifierId)
            throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT modifierId, name, purchasePrice, rentalPrice, rentalDuration FROM ItemModifier WHERE modifierId = ?");
        ps.setInt(1, modifierId);

        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return new ItemModifier(rs.getInt(1), rs.getString(2),
                    rs.getInt(3), rs.getInt(4), rs.getInt(5));
        else
            throw new SQLException("No matching item modifier.");
    }

    public static List<ItemModifier> getItemModifiers() throws SQLException
    {
        List<ItemModifier> modifiers = new ArrayList<ItemModifier>();
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT modifierId, name, purchasePrice, rentalPrice, rentalDuration FROM ItemModifier");

        ResultSet rs = ps.executeQuery();
        while (rs.next())
            modifiers.add(new ItemModifier(rs.getInt(1), rs.getString(1), rs
                    .getInt(3), rs.getInt(4), rs.getInt(5)));
        return modifiers;
    }

    public static ItemModifier insertItemModifier(ItemModifier modifier)
            throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "INSERT INTO ItemModifier(name, purchasePrice, rentalPrice, rentalDuration) VALUES(?, ?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, modifier.getName());
        ps.setInt(2, modifier.getPurchasePrice());
        ps.setInt(3, modifier.getRentalPrice());
        ps.setInt(4, modifier.getRentalDuration());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next())
            modifier.setModifierId(rs.getInt(1));
        else
            throw new SQLException("Could not insert modifier!");

        return modifier;
    }

    public static void updateItemModifier(ItemModifier modifier)
            throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "UPDATE ItemModifier SET name = ?, purchasePrice = ?, rentalPrice = ?, rentalDuration = ? WHERE modifierId = ?");
        ps.setString(1, modifier.getName());
        ps.setInt(2, modifier.getPurchasePrice());
        ps.setInt(3, modifier.getRentalPrice());
        ps.setInt(4, modifier.getRentalDuration());
        ps.setInt(5, modifier.getModifierId());

        ps.executeUpdate();
    }

    public static void deleteItemModifier(ItemModifier modifier)
            throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement(
                        "DELETE FROM ItemModifier WHERE modifierId = ?");
        ps.setInt(1, modifier.getModifierId());

        ps.executeUpdate();
    }

    public static ItemType getItemTypeById(int id) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT typeId, name, description, purchasePrice, rentable, rentalPrice, rentalDuration FROM ItemType WHERE typeId = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return new ItemType(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getInt(4), rs.getBoolean(5), rs.getInt(6), rs.getInt(7));
        else
            throw new SQLException("No matching item type!");
    }

    public static List<ItemType> getItemTypes() throws SQLException
    {
        List<ItemType> types = new ArrayList<ItemType>();
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT typeId, name, description, purchasePrice, rentable, rentalPrice, rentalDuration FROM ItemType");

        ResultSet rs = ps.executeQuery();
        while (rs.next())
            types.add(new ItemType(rs.getInt(1), rs.getString(2), rs
                    .getString(3), rs.getInt(4), rs.getBoolean(5),
                    rs.getInt(6), rs.getInt(7)));
        return types;
    }

    public static ItemType insertItemType(ItemType type) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "INSERT INTO ItemType(name, description, purchasePrice, rentable, rentalPrice, rentalDuration) VALUES(?, ?, ?, ?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, type.getName());
        ps.setString(2, type.getDescription());
        ps.setInt(3, type.getPurchasePrice());
        ps.setBoolean(4, type.isRentable());
        ps.setInt(5, type.getRentalPrice());
        ps.setInt(6, type.getRentalDuration());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next())
            type.setTypeId(rs.getInt(1));
        else
            throw new SQLException("Could not insert type!");

        return type;
    }

    public static void updateItemType(ItemType type) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "UPDATE ItemType SET name = ?, description = ?, purchasePrice = ?, rentable = ?, rentalPrice = ?, rentalDuration = ? WHERE typeId = ?");
        ps.setString(1, type.getName());
        ps.setString(2, type.getDescription());
        ps.setInt(3, type.getPurchasePrice());
        ps.setBoolean(4, type.isRentable());
        ps.setInt(5, type.getRentalPrice());
        ps.setInt(6, type.getRentalDuration());
        ps.setInt(7, type.getTypeId());

        ps.executeUpdate();
    }

    public static void deleteItemType(ItemType type) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement("DELETE FROM ItemType WHERE typeId = ?");
        ps.setInt(1, type.getTypeId());

        ps.executeUpdate();
    }
}
