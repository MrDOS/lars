package lars;

import javax.swing.JFrame;
import javax.swing.UIManager;

import lars.db.DatabaseManager;
import lars.gui.admin.AdminFrame;
import lars.gui.kiosk.KioskFrame;

/**
 * Welcome to LARS: it's Like a Rental System.
 * 
 * @author Jeremy Wheaton, 100105823
 * @author Samuel Coleman, 100105709
 * @version 2012-04-04
 */
public class LARS
{
    /**
     * The application name.
     */
    public static final String name = "LARS";
    /**
     * The application version.
     */
    public static final String version = "1.0.0";

    /**
     * The main entry point.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            System.err.println("Unable to set native Look-and-Feel.");
        }

        if (args.length > 0 && args[0].equals("-admin"))
        {
            JFrame frame = AdminFrame.getInstance();
            frame.setVisible(true);
            ((AdminFrame) frame).login();
        }
        else if (args.length > 0 && args[0].equals("-setup"))
        {
            DatabaseManager.createDatabase();
        }
        else
        {
            JFrame frame = KioskFrame.getInstance();
            frame.setVisible(true);
        }
    }
}
