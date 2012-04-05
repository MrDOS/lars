package lars.db;

import java.sql.SQLException;

import lars.Account;

public class DatabaseManager
{
    public static void createDatabase()
    {
        System.out.println("Initializing database.");

        System.out.print("Initializing accounts... ");
        try
        {
            AccountDatabase.createTable();
            System.out.println("done.");
        }
        catch (SQLException e)
        {
            System.err.println("Failed to create accounts table! "
                    + e.getMessage());
        }

        System.out.print("Initializing items... ");
        try
        {
            ItemDatabase.createTable();
            System.out.println("done.");
        }
        catch (SQLException e)
        {
            System.err.println("Failed to create items table! "
                    + e.getMessage());
        }

        System.out.print("Initializing transactions... ");
        try
        {
            TransactionDatabase.createTable();
            System.out.println("done.");
        }
        catch (SQLException e)
        {
            System.err.println("Failed to create transactions table! "
                    + e.getMessage());
        }

        System.out
                .println("Finished database setup. An initial user with ID 1 has been created.");
    }
}
