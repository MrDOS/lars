package lars.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lars.Item;
import lars.ItemModifier;
import lars.ItemType;

/**
 * Data storage of items.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class ItemDatabase
{
    /**
     * Get an account by its SKU.
     * 
     * @param sku
     *            the SKU
     * @return the item associated with the given SKU
     * @throws SQLException
     *             in the event where no such item exists
     */
    public static Item getItemBySku(int sku) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT typeId, sku, description, quantity FROM Item WHERE sku = ?");
        ps.setInt(1, sku);

        ResultSet rs = ps.executeQuery();
        try
        {
            if (rs.next())
            {
                Item item = new Item(getItemTypeById(rs.getInt(1)),
                        rs.getInt(2), rs.getString(3), rs.getInt(4));
                item.setModifiers(getItemModifiersByItem(item));
                return item;
            }
            else
                throw new SQLException("No matching item.");
        }
        finally
        {
            rs.close();
            ps.close();
        }
    }

    /**
     * Get all items.
     * 
     * @return all items
     * @throws SQLException
     */
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
        rs.close();
        ps.close();

        return items;
    }

    /**
     * Insert an item.
     * 
     * @param item
     *            the item
     * @throws SQLException
     */
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
        ps.close();

        applyItemModifiers(item);
    }

    /**
     * Update an item. Applicable for when the SKU has changed.
     * 
     * @param item
     *            the item
     * @param oldSku
     *            the old SKU of the item
     * @throws SQLException
     */
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
        ps.close();

        clearItemModifiers(item);
        applyItemModifiers(item);
    }

    /**
     * Update an item.
     * 
     * @param item
     *            the item
     * @throws SQLException
     */
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
        ps.close();

        clearItemModifiers(item);
    }

    /**
     * Get all modifiers associated with an item.
     * 
     * @param item
     *            the item
     * @return all modifiers associated with the item
     * @throws SQLException
     */
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
        rs.close();
        ps.close();

        return modifiers;
    }

    /**
     * Remove all modifiers associated with an item.
     * 
     * @param item
     *            the item
     * @throws SQLException
     */
    private static void clearItemModifiers(Item item) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement("DELETE FROM ItemModifiers WHERE sku = ?");
        ps.setInt(1, item.getSku());

        ps.executeUpdate();
        ps.close();
    }

    /**
     * Store all modifiers associated with an item.
     * 
     * @param item
     *            the item
     * @throws SQLException
     */
    private static void applyItemModifiers(Item item) throws SQLException
    {
        ConnectionManager.getConnection().createStatement()
                .executeUpdate("BEGIN");
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
        ps.close();
        ConnectionManager.getConnection().createStatement()
                .executeUpdate("COMMIT");
    }

    /**
     * Get an item modifier by its ID.
     * 
     * @param modifierId
     *            the ID
     * @return the item modifier associated with the given ID
     * @throws SQLException
     *             in the event where no such modifier exists
     */
    public static ItemModifier getItemModifierById(int modifierId)
            throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT modifierId, name, purchasePrice, rentalPrice, rentalDuration FROM ItemModifier WHERE modifierId = ?");
        ps.setInt(1, modifierId);

        ResultSet rs = ps.executeQuery();
        try
        {
            if (rs.next())
                return new ItemModifier(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getInt(4), rs.getInt(5));
            else
                throw new SQLException("No matching item modifier.");
        }
        finally
        {
            rs.close();
            ps.close();
        }
    }

    /**
     * Get all item modifiers.
     * 
     * @return all item modifiers
     * @throws SQLException
     */
    public static List<ItemModifier> getItemModifiers() throws SQLException
    {
        List<ItemModifier> modifiers = new ArrayList<ItemModifier>();
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT modifierId, name, purchasePrice, rentalPrice, rentalDuration FROM ItemModifier");

        ResultSet rs = ps.executeQuery();
        while (rs.next())
            modifiers.add(new ItemModifier(rs.getInt(1), rs.getString(2), rs
                    .getInt(3), rs.getInt(4), rs.getInt(5)));
        rs.close();
        ps.close();

        return modifiers;
    }

    /**
     * Insert an item modifier.
     * 
     * @param modifier
     *            the modifier
     * @return the modifier with its new modifier ID
     * @throws SQLException
     */
    public static ItemModifier insertItemModifier(ItemModifier modifier)
            throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "INSERT INTO ItemModifier(name, purchasePrice, rentalPrice, rentalDuration) VALUES(?, ?, ?, ?)");
        ps.setString(1, modifier.getName());
        ps.setInt(2, modifier.getPurchasePrice());
        ps.setInt(3, modifier.getRentalPrice());
        ps.setInt(4, modifier.getRentalDuration());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        try
        {
            if (rs.next())
                modifier.setModifierId(rs.getInt(1));
            else
                throw new SQLException("Could not insert modifier!");
        }
        finally
        {
            rs.close();
            ps.close();
        }

        return modifier;
    }

    /**
     * Update an item modifier.
     * 
     * @param modifier
     *            the item modifier
     * @throws SQLException
     */
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
        ps.close();
    }

    /**
     * Delete an item modifier.
     * 
     * @param modifier
     *            the item modifier
     * @throws SQLException
     */
    public static void deleteItemModifier(ItemModifier modifier)
            throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement(
                        "DELETE FROM ItemModifier WHERE modifierId = ?");
        ps.setInt(1, modifier.getModifierId());

        ps.executeUpdate();
        ps.close();
    }

    /**
     * Get an item type by its ID.
     * 
     * @param id
     *            the ID
     * @return the item type associated with the given ID
     * @throws SQLException
     *             in the event where no such type exists
     */
    public static ItemType getItemTypeById(int id) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT typeId, name, description, purchasePrice, rentable, rentalPrice, rentalDuration FROM ItemType WHERE typeId = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        try
        {
            if (rs.next())
                return new ItemType(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getBoolean(5),
                        rs.getInt(6), rs.getInt(7));
            else
                throw new SQLException("No matching item type!");
        }
        finally
        {
            rs.close();
            ps.close();
        }
    }

    /**
     * Get all item types.
     * 
     * @return all item types
     * @throws SQLException
     */
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
        rs.close();
        ps.close();

        return types;
    }

    /**
     * Insert an item type.
     * 
     * @param type
     *            the item type
     * @return the type with its new type ID
     * @throws SQLException
     */
    public static ItemType insertItemType(ItemType type) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "INSERT INTO ItemType(name, description, purchasePrice, rentable, rentalPrice, rentalDuration) VALUES(?, ?, ?, ?, ?, ?)");
        ps.setString(1, type.getName());
        ps.setString(2, type.getDescription());
        ps.setInt(3, type.getPurchasePrice());
        ps.setBoolean(4, type.isRentable());
        ps.setInt(5, type.getRentalPrice());
        ps.setInt(6, type.getRentalDuration());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        try
        {
            if (rs.next())
                type.setTypeId(rs.getInt(1));
            else
                throw new SQLException("Could not insert type!");
        }
        finally
        {
            rs.close();
            ps.close();
        }

        return type;
    }

    /**
     * Update an item type.
     * 
     * @param type
     *            the item type
     * @throws SQLException
     */
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
        ps.close();
    }

    /**
     * Delete an item type.
     * 
     * @param type
     *            the item type
     * @throws SQLException
     */
    public static void deleteItemType(ItemType type) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement("DELETE FROM ItemType WHERE typeId = ?");
        ps.setInt(1, type.getTypeId());

        ps.executeUpdate();
        ps.close();
    }

    /**
     * Create the database table. <strong>Destructive; call only during initial
     * system setup.</strong>
     * 
     * @throws SQLException
     */
    public static void createTable() throws SQLException
    {
        Statement statement = ConnectionManager.getConnection()
                .createStatement();

        statement.executeUpdate("DROP TABLE IF EXISTS Item");
        statement.executeUpdate("DROP TABLE IF EXISTS ItemModifier");
        statement.executeUpdate("DROP TABLE IF EXISTS ItemModifiers");
        statement.executeUpdate("DROP TABLE IF EXISTS ItemType");
        statement
                .executeUpdate("CREATE TABLE Item(sku INTEGER PRIMARY KEY, typeId, description, quantity)");
        statement
                .executeUpdate("CREATE TABLE ItemModifier(modifierId INTEGER PRIMARY KEY AUTOINCREMENT, name, purchasePrice, rentalPrice, rentalDuration)");
        statement.executeUpdate("CREATE TABLE ItemModifiers(sku, modifierId)");
        statement
                .executeUpdate("CREATE TABLE ItemType(typeId INTEGER PRIMARY KEY AUTOINCREMENT, name, description, purchasePrice, rentable, rentalPrice, rentalDuration)");
        statement.close();
    }
}
