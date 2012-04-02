package lars.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lars.Account;

/**
 * Provide database access to accounts.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-01
 */
public class AccountDatabase
{
    public static Account getAccountById(int id) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT accountId, name, address, manager FROM Account WHERE accountId = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next())
            return new Account(rs.getInt(1), rs.getString(2), rs.getString(3),
                    rs.getBoolean(4));
        else
            throw new SQLException("No matching account.");
    }

    public static Account insertAccount(Account account) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "INSERT INTO Account(name, address, manager) VALUES(?, ?, ?)",
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
