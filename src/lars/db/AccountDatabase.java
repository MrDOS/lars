package lars.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lars.Account;

/**
 * Data storage of accounts.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class AccountDatabase
{
    /**
     * Get all accounts.
     * 
     * @return all accounts
     * @throws SQLException
     */
    public static List<Account> getAccounts() throws SQLException
    {
        List<Account> accounts = new ArrayList<Account>();

        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT accountId, name, address, manager FROM Account");

        ResultSet rs = ps.executeQuery();
        while (rs.next())
            accounts.add(new Account(rs.getInt(1), rs.getString(2), rs
                    .getString(3), rs.getBoolean(4)));

        return accounts;
    }

    /**
     * Get an account by its ID.
     * 
     * @param id
     *            the account ID
     * @return the account associated with the given ID
     * @throws SQLException
     *             in the event where no such account exists
     */
    public static Account getAccountById(int id) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT accountId, name, address, manager FROM Account WHERE accountId = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        try
        {
            if (rs.next())
                return new Account(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getBoolean(4));
            else
                throw new SQLException("No matching account.");
        }
        finally
        {
            rs.close();
            ps.close();
        }
    }

    /**
     * Insert an account.
     * 
     * @param account
     *            the account
     * @return the account with its new account ID
     * @throws SQLException
     */
    public static Account insertAccount(Account account) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "INSERT INTO Account(name, address, manager) VALUES(?, ?, ?)");
        ps.setString(1, account.getName());
        ps.setString(2, account.getAddress());
        ps.setBoolean(3, account.isManager());

        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        try
        {
            if (rs.next())
                account.setAccountId(rs.getInt(1));
            else
                throw new SQLException("Could not insert account!");
        }
        finally
        {
            rs.close();
            ps.close();
        }

        return account;
    }

    /**
     * Update an account.
     * 
     * @param account
     *            the account
     * @throws SQLException
     */
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
        ps.close();
    }

    /**
     * Delete an account.
     * 
     * @param account
     *            the account
     * @throws SQLException
     */
    public static void deleteAccount(Account account) throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement("DELETE FROM Account WHERE accountId = ?");
        ps.setInt(1, account.getAccountId());

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

        statement.executeUpdate("DROP TABLE IF EXISTS Account");
        statement
                .executeUpdate("CREATE TABLE Account(accountId INTEGER PRIMARY KEY AUTOINCREMENT, name, address, manager)");

        statement.close();

        insertAccount(new Account(0, "Administrator", "", true));

        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "UPDATE sqlite_sequence SET seq = ? WHERE name = 'Account'");
        ps.setInt(1, Account.LOW_ACCOUNT_ID - 1);
        ps.executeUpdate();
        ps.close();
    }

    /**
     * Insert some demo accounts.
     * 
     * @throws SQLException
     */
    public static void insertDemoData() throws SQLException
    {
        insertAccount(new Account(0, "John Doe", "Marvin Gardens", false));
        insertAccount(new Account(0, "Robert Roe", "Louisiana Avenue", false));
    }
}
