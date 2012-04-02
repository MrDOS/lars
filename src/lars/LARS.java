package lars;

import javax.swing.JFrame;

import lars.gui.kiosk.KioskFrame;

/**
 * Main entry point.
 * 
 * @author Samuel Coleman, 100105709
 * @version 2012-04-01
 */
public class LARS
{
    public static final String name = "LARS";
    public static final String version = "1.0.0";

    public static void main(String[] args)
    {
        if (args.length > 0 && args[0].equals("-admin"))
        {
            // TODO: administrative terminal startup.
        }
        else
        {
            JFrame frame = KioskFrame.getInstance();
            frame.setVisible(true);
        }
    }
}
