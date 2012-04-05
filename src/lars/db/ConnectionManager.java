package lars.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Juggle a database connection.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class ConnectionManager
{
    private static Connection connection = null;

    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:lars.db";

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL);
            }
            catch (ClassNotFoundException e)
            {
                System.err.println("Could not load database driver!");
                System.exit(1);
            }
            catch (SQLException e)
            {
                System.err.println("Error establishing database connection!");
                System.exit(2);
            }
        }

        return connection;
    }
}
