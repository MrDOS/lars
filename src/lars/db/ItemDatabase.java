package lars.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lars.Item;
import lars.ItemType;

/**
 * Provide database access to items.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-01
 */
public class ItemDatabase
{
    public static Item getIdBySku(int sku) throws SQLException
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
}
