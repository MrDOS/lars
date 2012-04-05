package lars.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lars.Account;
import lars.Rental;
import lars.RentalItem;

/**
 * Data storage of rentals.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class RentalDatabase
{
    /**
     * Get all rentals associated with an account.
     * 
     * @param account
     *            the account
     * @return all rentals associated with the account
     * @throws SQLException
     */
    public static List<Rental> getRentalsByAccount(Account account)
            throws SQLException
    {
        List<Rental> rentals = new ArrayList<Rental>();
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT rentalId, date FROM Rental WHERE accountId = ?");
        ps.setInt(1, account.getAccountId());

        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            Rental rental = new Rental(rs.getInt(1), rs.getDate(2));
            rental.setRentalItems(getRentalItemsByRental(rental));
            rentals.add(rental);
        }
        rs.close();
        ps.close();

        return rentals;
    }

    /**
     * Get a rental by its ID.
     * 
     * @param id
     *            the ID
     * @return the rental associated with the given ID
     * @throws SQLException
     *             in the event where no such rental exists
     */
    public static Rental getRentalById(int id) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement(
                        "SELECT rentalId, date FROM Rental WHERE rentalId = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        try
        {
            if (rs.next())
            {
                Rental rental = new Rental(rs.getInt(1), rs.getDate(2));
                rental.setRentalItems(getRentalItemsByRental(rental));
                return rental;
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
     * Insert a rental.
     * 
     * @param rental
     *            the rental
     * @return the rental with its new rental ID
     * @throws SQLException
     */
    public static Rental insertRental(Rental rental) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement("INSERT INTO Rental(date) VALUES(?)");
        ps.setDate(1, rental.getDate());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        try
        {
            if (rs.next())
            {
                rental.setRentalId(rs.getInt(1));
                rs.close();
                applyRentalItems(rental);
            }
            else
                throw new SQLException("Could not insert rental!");
        }
        finally
        {
            rs.close();
            ps.close();
        }

        return rental;
    }

    /**
     * Update a rental.
     * 
     * @param rental
     *            the rental
     * @throws SQLException
     */
    public static void updateRental(Rental rental) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement(
                        "UPDATE Rental SET date = ? WHERE rentalId = ?");
        ps.setDate(1, rental.getDate());
        ps.setInt(2, rental.getRentalId());

        ps.executeUpdate();
        ps.close();

        clearRentalItems(rental);
        applyRentalItems(rental);
    }

    /**
     * Delete a rental.
     * 
     * @param rental
     *            the rental
     * @throws SQLException
     */
    public static void deleteRental(Rental rental) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement("DELETE FROM Rental WHERE rentalId = ?");
        ps.setInt(1, rental.getRentalId());

        ps.executeUpdate();
        ps.close();

        clearRentalItems(rental);
    }

    /**
     * Get all unreturned rental items.
     * 
     * @return all unreturned rental items
     * @throws SQLException
     */
    public static List<RentalItem> getUnreturnedRentalItems()
            throws SQLException
    {
        List<RentalItem> RentalItems = new ArrayList<RentalItem>();
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT sku, rented, due, returned FROM RentalItem WHERE rented = 1 AND returned = 0");

        ResultSet rs = ps.executeQuery();
        while (rs.next())
            RentalItems.add(new RentalItem(ItemDatabase.getItemBySku(rs
                    .getInt(1)), rs.getBoolean(2), rs.getDate(3), rs
                    .getBoolean(4)));
        rs.close();
        ps.close();

        return RentalItems;
    }

    /**
     * Get all rental items by their associated rental.
     * 
     * @param rental
     *            the rental
     * @return all rental items associated with the given rental
     * @throws SQLException
     *             in the event where no such rental exists
     */
    private static List<RentalItem> getRentalItemsByRental(Rental rental)
            throws SQLException
    {
        List<RentalItem> RentalItems = new ArrayList<RentalItem>();
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT RentalItem.sku, RentalItem.rented, RentalItem.due, RentalItem.returned FROM RentalItem WHERE RentalItem.rentalId = ?");
        ps.setInt(1, rental.getRentalId());

        ResultSet rs = ps.executeQuery();
        while (rs.next())
            RentalItems.add(new RentalItem(ItemDatabase.getItemBySku(rs
                    .getInt(1)), rs.getBoolean(2), rs.getDate(3), rs
                    .getBoolean(4)));
        rs.close();
        ps.close();

        return RentalItems;
    }

    /**
     * Remove all items associated with a rental.
     * 
     * @param rental
     *            the rental
     * @throws SQLException
     */
    private static void clearRentalItems(Rental rental) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement("DELETE FROM RentalItem WHERE rentalId = ?");
        ps.setInt(1, rental.getRentalId());

        ps.executeUpdate();
        ps.close();
    }

    /**
     * Store all items associated with a rental.
     * 
     * @param rental
     *            the rental
     * @throws SQLException
     */
    private static void applyRentalItems(Rental rental) throws SQLException
    {
        ConnectionManager.getConnection().createStatement()
                .executeUpdate("BEGIN");
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "INSERT INTO RentalItem(rentalId, sku, rented, due, returned) VALUES (?, ?, ?, ?, ?)");
        for (RentalItem RentalItem : rental.getRentalItems())
        {
            ps.setInt(1, rental.getRentalId());
            ps.setInt(2, RentalItem.getItem().getSku());
            ps.setBoolean(3, RentalItem.isRented());
            ps.setDate(4, RentalItem.getDueDate());
            ps.setBoolean(5, RentalItem.isReturned());
            ps.executeUpdate();
        }
        ps.close();
        ConnectionManager.getConnection().createStatement()
                .executeUpdate("COMMIT");
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

        statement.executeUpdate("DROP TABLE IF EXISTS Rental");
        statement.executeUpdate("DROP TABLE IF EXISTS RentalItem");
        statement
                .executeUpdate("CREATE TABLE Rental(rentalId INTEGER PRIMARY KEY AUTOINCREMENT, accountId, date)");
        statement
                .executeUpdate("CREATE TABLE RentalItem(rentalId, sku, rented, due, returned)");
        statement.close();
    }
}
