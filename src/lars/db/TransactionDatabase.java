package lars.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lars.Account;
import lars.Item;
import lars.ItemModifier;
import lars.Transaction;
import lars.TransactionItem;

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
        List<Transaction> transactions = new ArrayList<Transaction>();
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT transactionId, date FROM Transaction WHERE accountId = ?");
        ps.setInt(1, account.getAccountId());

        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            Transaction transaction = new Transaction(rs.getInt(1),
                    rs.getDate(2));
            transaction
                    .setTransactionItems(getTransactionItemsByTransaction(transaction));
            transactions.add(transaction);
        }

        return transactions;
    }

    public static Transaction getTransactionById(int id) throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT transactionId, date FROM Transaction WHERE transactionId = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next())
        {
            Transaction transaction = new Transaction(rs.getInt(1),
                    rs.getDate(2));
            transaction
                    .setTransactionItems(getTransactionItemsByTransaction(transaction));
            return transaction;
        }
        else
            throw new SQLException("No matching item.");
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

    private static List<TransactionItem> getTransactionItemsByTransaction(
            Transaction transaction) throws SQLException
    {
        List<TransactionItem> transactionItems = new ArrayList<TransactionItem>();
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "SELECT TransactionItem.sku, TransactionItem.rented FROM TransactionItem WHERE TransactionItem.transactionId = ?");
        ps.setInt(1, transaction.getTransactionId());

        ResultSet rs = ps.executeQuery();
        while (rs.next())
            transactionItems.add(new TransactionItem(ItemDatabase
                    .getItemBySku(rs.getInt(1)), rs.getBoolean(2)));

        return transactionItems;
    }

    private static void clearTransactionItems(Transaction transaction)
            throws SQLException
    {
        PreparedStatement ps = ConnectionManager.getConnection()
                .prepareStatement(
                        "DELETE FROM TransactionItem WHERE transactionId = ?");
        ps.setInt(1, transaction.getTransactionId());

        ps.executeUpdate();
    }

    private static void applyTransactionItems(Transaction transaction)
            throws SQLException
    {
        PreparedStatement ps = ConnectionManager
                .getConnection()
                .prepareStatement(
                        "INSERT INTO TransactionItem(transactionId, sku, rented) VALUES (?, ?)");
        for (TransactionItem transactionItem : transaction
                .getTransactionItems())
        {
            ps.setInt(1, transaction.getTransactionId());
            ps.setInt(2, transactionItem.getItem().getSku());
            ps.setBoolean(3, transactionItem.isRented());
            ps.executeUpdate();
        }
    }
}
