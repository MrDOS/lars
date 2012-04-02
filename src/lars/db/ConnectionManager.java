package lars.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager
{
    private static Connection connection = null;

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "lars";
    private static final String DB_USER = "lars";
    private static final String DB_PASS = "lars";

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL + DB_NAME,
                        DB_USER, DB_PASS);
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
