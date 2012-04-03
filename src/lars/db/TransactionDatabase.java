package lars.db;

import java.sql.SQLException;
import java.util.List;

import lars.Account;
import lars.Transaction;

/**
 * Provide database access to transactions.
 * 
 * @author Jeremy Wheaton
 * @version 2012-04-01
 */
public class TransactionDatabase
{
    public static List<Transaction> getTransactionsByAccount(Account account)
            throws SQLException
    {
        // TODO: Stub method.
        return null;
    }

    public static Transaction getTransactionById(int id) throws SQLException
    {
        // TODO: Stub method.
        return null;
    }

    public static Transaction insertTransaction(Transaction transaction)
            throws SQLException
    {
        // TODO: Stub method.
        return null;
    }

    public static Transaction updateTransaction(Transaction transaction)
            throws SQLException
    {
        // TODO: Stub method.
        return null;
    }

    public static Transaction deleteTransaction(Transaction transaction)
            throws SQLException
    {
        // TODO: Stub method.
        return null;
    }
}
