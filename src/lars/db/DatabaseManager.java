package lars.db;

import java.sql.SQLException;

/**
 * Database manager creates and updates database schemas.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class DatabaseManager
{
    /**
     * Initialize the database tables. <strong>Destructive; call only during
     * initial system setup.</strong>
     */
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

        System.out.print("Initializing rentals... ");
        try
        {
            RentalDatabase.createTable();
            System.out.println("done.");
        }
        catch (SQLException e)
        {
            System.err.println("Failed to create rentals table! "
                    + e.getMessage());
        }

        System.out
                .println("Finished database setup. An initial user with ID 1 has been created.");
    }
}
