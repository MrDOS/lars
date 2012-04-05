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
 * Provide database access to rentals.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class RentalDatabase
{
    public static List<Rental> getRentalsByAccount(Account account)
            throws SQLException
    {
        List<Rental> transactions = new ArrayList<Rental>();
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT rentalId, date FROM Rental WHERE accountId = ?");
        ps.setInt(1, account.getAccountId());

        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            Rental transaction = new Rental(rs.getInt(1), rs.getDate(2));
            transaction.setRentalItems(getRentalItemsByRental(transaction));
            transactions.add(transaction);
        }
        rs.close();
        ps.close();

        return transactions;
    }

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
                Rental transaction = new Rental(rs.getInt(1), rs.getDate(2));
                transaction.setRentalItems(getRentalItemsByRental(transaction));
                return transaction;
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

    public static Rental insertRental(Rental transaction) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement("INSERT INTO Rental(date) VALUES(?)");
        ps.setDate(1, transaction.getDate());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        try
        {
            if (rs.next())
            {
                transaction.setRentalId(rs.getInt(1));
                applyRentalItems(transaction);
            }
            else
                throw new SQLException("Could not insert rental!");
        }
        finally
        {
            rs.close();
            ps.close();
        }

        return transaction;
    }

    public static void updateRental(Rental transaction) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement(
                        "UPDATE Rental SET date = ? WHERE rentalId = ?");
        ps.setDate(1, transaction.getDate());
        ps.setInt(2, transaction.getRentalId());

        ps.executeUpdate();
        ps.close();

        clearRentalItems(transaction);
        applyRentalItems(transaction);
    }

    public static void deleteRental(Rental transaction) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement("DELETE FROM Rental WHERE rentalId = ?");
        ps.setInt(1, transaction.getRentalId());

        ps.executeUpdate();
        ps.close();

        clearRentalItems(transaction);
    }

    private static List<RentalItem> getRentalItemsByRental(Rental transaction)
            throws SQLException
    {
        List<RentalItem> RentalItems = new ArrayList<RentalItem>();
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT RentalItem.sku, RentalItem.rented, RentalItem.due, RentalItem.returned FROM RentalItem WHERE RentalItem.rentalId = ?");
        ps.setInt(1, transaction.getRentalId());

        ResultSet rs = ps.executeQuery();
        while (rs.next())
            RentalItems.add(new RentalItem(ItemDatabase.getItemBySku(rs
                    .getInt(1)), rs.getBoolean(2), rs.getDate(3), rs
                    .getBoolean(4)));
        rs.close();
        ps.close();

        return RentalItems;
    }

    private static void clearRentalItems(Rental transaction)
            throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement("DELETE FROM RentalItem WHERE rentalId = ?");
        ps.setInt(1, transaction.getRentalId());

        ps.executeUpdate();
        ps.close();
    }

    private static void applyRentalItems(Rental transaction)
            throws SQLException
    {
        ConnectionManager.getConnection().createStatement()
                .executeUpdate("BEGIN");
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "INSERT INTO RentalItem(rentalId, sku, rented, due, returned) VALUES (?, ?, ?, ?, ?)");
        for (RentalItem RentalItem : transaction.getRentalItems())
        {
            ps.setInt(1, transaction.getRentalId());
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
