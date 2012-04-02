package lars.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lars.Transaction;
import lars.TransactionItem;

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
            return new Transaction(rs.getDate(1), rs.getArrayList<TransactionItem>(2)); //TODO: FIX THIS
        else
            throw new SQLException("No matching Transaction.");
    }

    public static Transaction insertTransaction(Transaction transaction) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "INSERT INTO Transaction(date, ) VALUES(?, ?, ?)",
                        PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setString(1, account.getName());
        ps.setString(2, account.getAddress());
        ps.setBoolean(3, account.isManager());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next())
            account.setAccountId(rs.getInt(1));
        else
            throw new SQLException("Could not insert account!");

        return account;
    }

    public static void updateAccount(Account account) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "UPDATE Account SET name = ?, address = ?, manager = ? WHERE accountId = ?");
        ps.setString(1, account.getName());
        ps.setString(2, account.getAddress());
        ps.setBoolean(3, account.isManager());
        ps.setInt(4, account.getAccountId());

        ps.executeUpdate();
    }

    public static void deleteAccount(Account account) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement("DELETE FROM Account WHERE accountId = ?");
        ps.setInt(1, account.getAccountId());

        ps.executeUpdate();
    }
}
