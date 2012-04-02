package lars.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lars.Transaction;

/**
 * Provide database access to transactions.
 * 
 * @author Jeremy Wheaton
 * @version 2012-04-01
 */
public class TransactionDatabase
{
    public static Transaction getTransactionBySKU(int SKU) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT date, transactionItems FROM Transaction WHERE SKU = ?");
        ps.setInt(1, SKU);

        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return null; // TODO: Make it return a Real Thing (TM).
        else
            throw new SQLException("No matching Transaction.");
    }
}
